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


}

