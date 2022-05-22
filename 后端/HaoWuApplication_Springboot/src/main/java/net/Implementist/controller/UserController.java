package net.Implementist.controller;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequestMapping(value = "/test")
    public String test(HttpServletResponse response) {
        return "1234567890";
    }
}

