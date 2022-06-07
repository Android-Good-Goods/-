package net.Implementist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.Implementist.entity.Message;
import net.Implementist.entity.User;
import net.Implementist.service.MessageService;
import net.Implementist.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
@Slf4j
public class MessageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(path = "/Message_Servlet", produces = "text/html;charset=utf-8")
    public String account() throws JsonProcessingException {
        String mid;
        String state;
        JSONObject params = new JSONObject();
        JSONArray messagelist = new JSONArray();
        JSONArray unreadlist = new JSONArray();
        JSONObject userdata = new JSONObject();      //用户数据
        // 设置响应内容类型

        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "getmessage":
                    User result = userService.queryUserByAccount(request.getParameter("account").trim());
//                    JSONObject user = new JSONObject();
//                    user.put("uid", String.valueOf(result.getUid()));
//                    user.put("account", result.getUaccount());
//                    user.put("password", result.getUpwd());
//                    user.put("hxid", result.getUhxid());
//                    user.put("nickname", result.getUnickname());
//                    user.put("sex", String.valueOf(result.getUsex()));
//                    user.put("reputation", String.valueOf(result.getUreputation()).trim());
//                    user.put("headphoto", result.getUphoto().trim());
//                    user.put("balance", result.getUbalance());
//                    user.put("tel",result.getUtel().trim());
//                    user.put("address",result.getUaddress().trim());
//                    user.put("school",result.getUschool());
//                    user.put("state",String.valueOf(result.getUstate()).trim());
                    messagelist = messageService.queryMessageData(String.valueOf(result.getUid()),1);
                    if(messagelist.size() > 0){        //查询到有消息返回1
                        params.put("code", "1");
                        params.put("data", messagelist);
                    }else if(messagelist.isEmpty()){     //查询到无消息
                        params.put("code", "2");
                    }else {
                        //查询出错
                        params.put("code", "0");
                    }


                    break;
                case "changemessage":                        //修改消息状态
                    mid = request.getParameter("mid").trim();
                    state = request.getParameter("state").trim();
                    Message target = new Message();
                    target.setMid(Integer.valueOf(mid));
                    target.setMstate(Integer.valueOf(state));
                    boolean rs = messageService.refreshMessage(target);
                    if(rs){
                        //修改成功
                        params.put("code", "1");
                    }else {
                        //修改失败
                        params.put("code", "0");
                    }
                    break;
                case "getunread":                        //修改消息状态
                    User result1 = userService.queryUserByAccount(request.getParameter("account").trim());
                    unreadlist = messageService.queryMessageData(String.valueOf(result1.getUid()),2);
                    if(!unreadlist.isEmpty()){
                        //有未读消息
                        params.put("code", "1");
                    }else {
                        //没有未读消息
                        params.put("code", "0");
                    }
                    break;
                default:System.out.println("商品发布失败！");break;
            }
        }catch(Exception e){
            log.info("Message_Servlet有错误发生{}", e.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }

}

