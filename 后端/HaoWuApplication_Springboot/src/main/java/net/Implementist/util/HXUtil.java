package net.Implementist.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import net.Implementist.entity.Token;
import net.Implementist.entity.User;
import net.Implementist.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 *  环信工具类
 */
public class HXUtil {

    @Autowired
    private static TokenService tokenService;

    private static final RestTemplate restTemplate = new RestTemplate();
    
    // 企业的唯一标识，开发者在环信开发者管理后台注册账号时填写的企业 ID
    private static final String ORG_NAME = "1165220506113987";
    // App的client_id
    private static final String CLIENT_ID = "YXA6fH9FAMcvSLON23S8VP-3og";
    // App的client_secret
    private static final String CLIENT_SECRET = "YXA6y87Wwheko_vUtfGGNfb3_Y9UhFQ";
    // 同一“企业”下“APP”唯一标识，开发者在环信开发者管理后台创建应用时填写的“应用名称”
    private static final String APP_NAME = "haowu";
    // 链接前缀
    private static final String URL_PREFIX = "http://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/";

    // token的失效时间
    private static long expiresTime;
    //有效地token字符串
    private static String tokenStr;
    
    public enum HXMessageType {
        txt,// 文本
        img,// 图片
        loc,// 位置
        audio,// 音频
        video,// 视频
        file// 文件
    }

    /**
     * 获取Token
     * 注意：关于有效时间，我在网上找过，说的是7天，但是返回的是5184000，
     * 			但是官网上说是以秒为单位，这么算下来就是60天了，
     * 			觉得不太对，就先将有效时间设为了7天
     * @return token
     */
    public static HxToken getToken() {
        Token token = tokenService.getById("1");
        tokenStr = token.getApptoken();
        if(!"null".equals(token.getExpires().trim())){
            expiresTime = Long.valueOf(token.getExpires().trim());
        }
        // 判断Token是否已经过期，如果过期需要重新获取
        if ("null".equals(tokenStr) || expiresTime < new Date().getTime()) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                JSONObject body = new JSONObject();
                body.put("grant_type", "client_credentials");
                body.put("client_id", CLIENT_ID );
                body.put("client_secret", CLIENT_SECRET );
                HttpEntity httpEntity = new HttpEntity(body.toString(), headers);
                ResponseEntity<String> tokenResponseEntity = restTemplate.postForEntity(URL_PREFIX + "token", httpEntity, String.class);
                if(tokenResponseEntity.getStatusCode().value() == 200){
                    Gson gson = new Gson();
                    Map<String, Object> tokenMap = new HashMap<String, Object>();
                    tokenMap = gson.fromJson(tokenResponseEntity.getBody(), tokenMap.getClass());
                    // 设置7天后过期
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 7);
                    expiresTime = c.getTime().getTime();
                    token.setId(1);
                    token.setApplication((String)tokenMap.get("application"));
                    token.setApptoken((String)tokenMap.get("access_token"));
                    token.setExpires(String.valueOf(expiresTime));
                    tokenService.updateTokenById(token);
                    token = tokenService.getById("1");
                }
                
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }else{
            token = tokenService.getById("1");
        }
        HxToken newToken = new HxToken();
        token.setId(token.getId());
        newToken.setExpires_in(token.getExpires());
        newToken.setApplication(token.getApplication());
        newToken.setAccess_token(token.getApptoken());
        return newToken;
    }
 
    /**
     * 添加用户
     *
     * @param username 用户名（唯一非空）
     * @param password 密码
     * @param nickname 昵称
     * @return 是否成功
     */
    public static boolean addUser(String username, String password, String nickname) {
        try {
            JSONArray body = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("nickname", nickname);
            body.add(jsonObject);
            HttpEntity httpEntity = new HttpEntity(body.toString(), null);
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users", httpEntity, null);
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 修改用户密码
     *
     * @param username    用户名
     * @param newpassword 新密码
     * @return 是否成功
     */
    public static boolean updatePassword(String username, String newpassword) {
        try {
            JSONObject body = new JSONObject();
            body.put("newpassword", newpassword);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + getToken().getAccess_token());
            HttpEntity httpEntity = new HttpEntity(body.toString(), headers);
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users/{username}/password", httpEntity, null, username);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 
     */
    public static boolean deleteUser(String username) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
            ResponseEntity<User> responseEntity = restTemplate.exchange(URL_PREFIX + "users/{username}", HttpMethod.DELETE, httpEntity, User.class, username);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 添加好友
     *
     * @param ownerUsername 用户名
     * @param friendName    好友用户名
     * @return 是否成功
     */
    public static boolean addFriend(String ownerUsername, String friendName) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users/{owner_username}/contacts/users/{friend_username}", httpEntity, User.class, ownerUsername, friendName);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 删除好友
     *
     * @param ownerUsername 用户名
     * @param friendName    好友用户名
     * @return 是否成功
     */
    public static boolean deleteFriend(String ownerUsername, String friendName) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
            ResponseEntity responseEntity = restTemplate.exchange(URL_PREFIX + "users/{owner_username}/contacts/users/{friend_username}", HttpMethod.DELETE, httpEntity, User.class, ownerUsername, friendName);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 发送消息
     *
     * @param sendUser   发送用户
     * @param targetUser 接收用户
     * @param msg        发送消息
     * @return 是否成功
     */
    public static boolean sendToUser(String sendUser, String targetUser, String msg) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            headers.add("Accept", "application/json;charset=UTF-8");
            headers.add("Authorization", "Bearer " + getToken().getAccess_token());
            JSONObject body = new JSONObject();
            body.put("target_type", "users");
            JSONArray targetUserjson = new JSONArray();
            targetUserjson.add(targetUser);
            body.put("target", targetUserjson);
            JSONObject msgJson = new JSONObject();
            msgJson.put("type", HXMessageType.txt.name());
            msgJson.put("msg", msg);
            body.put("msg", msgJson);
            body.put("from", sendUser);
            HttpEntity httpEntity = new HttpEntity(body.toString(), headers);
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "messages", httpEntity, null);
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            return false;
        }
    }

    /**
     * 获取HttpHeaders
     *
     * @param contentType 客户端发送类型
     * @param accept      响应类型
     * @return HttpHeaders
     */
    private static HttpHeaders getHttpHeaders(MediaType contentType, MediaType... accept) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Authorization", "Bearer " + getToken().getAccess_token());
        headers.setContentType(contentType != null ? contentType : MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList((accept != null && accept.length > 0) ? accept : new MediaType[]{MediaType.APPLICATION_JSON}));
        return headers;
    }

}