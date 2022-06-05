package net.Implementist.controller;


import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.User;
import net.Implementist.service.UserService;
import net.Implementist.util.ImageDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
public class PersonController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/Person_Servlet", produces = "text/html;charset=utf-8")
    public String person() throws JsonProcessingException {
        //定义变量
        String account,imagestr,imagename,imagepath,nickname,sex,school,tel,balance,address;
        boolean result = false;
        User user = new User();
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //用户数据
        vresult.put("code", "0");   //修改失败
        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            account = request.getParameter("account").trim();
            user.setUaccount(account);
            switch (requesttop)
            {
                case "headPhoto":                    //上传头像响应
                    //新建变量
                    JSONObject photo = new JSONObject();
                    //获取appdata文件夹路径
                    String apppath = request.getRealPath("/")+"appdata";
                    //新建file文件
                    File appfile = new File(apppath);
                    if(!appfile.exists()){
                        appfile.mkdir();
                    }
                    //在appdata文件夹下新建userdata文件夹
                    String userdata = apppath + "/" + "userdata";
                    File userfile = new File(userdata);
                    if(!userfile.exists()){
                        userfile.mkdir();
                    }
                    imagestr = request.getParameter("imagestr").trim();
                    imagename = request.getParameter("imagename").trim();
                    imagepath = userdata + "/" + imagename;
                    if(ImageDeal.string2Image(imagestr, imagepath)){
                        user.setUphoto(imagepath);
                        result = userService.refreshUserByAccount(user);
                        if (result) {
                            vresult.put("code", "1");   //修改成功
                            data.put("path", imagepath);  //将头像的本地储存路径返回到客户端
                            vresult.put("data", data);
                        }
                    }
                    photo.clear();
                    break;
                case "changeNickname":                       //修改昵称
                    nickname = request.getParameter("newname").trim();
                    user.setUnickname(nickname);
                    result = userService.refreshUserByAccount(user);
                    if (result) {
                        vresult.put("code", "1");   //修改成功
                        data.put("name", nickname);  //将头像的本地储存路径返回到客户端
                        vresult.put("data", data);
                    }
                    break;
                case "sex":                    //修改性别
                    sex = request.getParameter("sex").trim();
                    user.setUsex(Integer.valueOf(sex));
                    result = userService.refreshUserByAccount(user);
                    if (result) {
                        vresult.put("code", "1");   //修改成功
                        data.put("sex", sex);
                        vresult.put("data", data);
                    }
                    break;
                case "setschool":              //修改学校

                    school = request.getParameter("school").trim();
                    user.setUschool(school);
                    result = userService.refreshUserByAccount(user);
                    if (result) {
                        vresult.put("code", "1");   //修改成功
                        data.put("sex", school);
                        vresult.put("data", data);
                    }
                    break;
                case "settel":                //修改联系方式
                    tel = request.getParameter("tel").trim();
                    user.setUtel(tel);
                    result = userService.refreshUserByAccount(user);
                    if (result) {
                        vresult.put("code", "1");   //修改成功
                        data.put("tel", tel);
                        vresult.put("data", data);
                    }
                    break;
                case "charge":                          //充值按钮响应
                    //todo
                    break;
                case "tixian":             //提现按钮响应
                    //todo
                    break;
                case "address":              //修改地址
                    address = request.getParameter("address").trim();
                    user.setUaddress(address);
                    result = userService.refreshUserByAccount(user);
                    if (result) {
                        vresult.put("code", "1");   //修改成功
                        data.put("address", address);
                        vresult.put("data", data);
                    }
                    break;
                default:System.out.println("用户验证失败！");break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(vresult);
    }
}

