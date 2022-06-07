package net.Implementist.controller;


import net.Implementist.entity.User;
import net.Implementist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
@RequestMapping("/conments")
public class ConmentsController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public User index() {
        return userService.getById(1);
    }
}

