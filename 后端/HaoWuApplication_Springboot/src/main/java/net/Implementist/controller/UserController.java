package net.Implementist.controller;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.User;
import net.Implementist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  user前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/Login_Servlet", produces = "text/html;charset=utf-8")
    public String login() throws JsonProcessingException {
        JSONObject vresult = new JSONObject();
        //获取上传的账户密码
        String account = request.getParameter("account").trim();
        String password = request.getParameter("password").trim();
        //验证用户密码
        vresult = userService.Loginverify(account,password);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(vresult);

    }

    //注册servlet
    @RequestMapping(path = "/Signup_Servlet", produces = "text/html;charset=utf-8")
    public String signup() throws JsonProcessingException {
        String account = request.getParameter("account").trim();
        String password = request.getParameter("password").trim();
        String nickname = request.getParameter("nickname").trim();
        String tel = request.getParameter("tel").trim();
        int sex = Integer.valueOf(request.getParameter("sex").trim());
        JSONObject jsonObject = userService.signupVerify(account, password, nickname, tel, sex);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jsonObject);
    }

    @RequestMapping(value = "/Setting_Servlet", produces = "text/html;charset=utf-8")
    public String test(HttpServletResponse response) throws Exception {
        String account,password,newpassword;
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject resultJsonObject = new JSONObject();
        //获得请求头
        String requesttop = request.getParameter("requesttop").trim();
        switch (requesttop)
        {
            case "logout":
                boolean rs = false;
                account = request.getParameter("account").trim();
                User target = new User();
                target.setUaccount(account).setUstate(1);
                rs = userService.refreshUserByAccount(target);  //把用户状态改成1
                if(rs){
                    resultJsonObject.put("code", "1");
                }else{
                    resultJsonObject.put("code", "0");
                }
                break;
            case "modifypwd":                          //修改密码
                JSONObject pwdResult = new JSONObject();
                User target1 = new User();
                account = request.getParameter("account").trim();
                password = request.getParameter("pwd").trim();
                newpassword = request.getParameter("newpwd").trim();
                resultJsonObject = userService.ModifyPwd(account,password,newpassword);
                break;
        }
        return objectMapper.writeValueAsString(resultJsonObject);
    }
}

