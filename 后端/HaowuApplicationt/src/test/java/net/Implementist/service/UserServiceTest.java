package net.Implementist.service;

import net.Implementist.entity.User;
import net.Implementist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    //测试根据用户账号查询用户
    @Test
    void queryUserByAccount() {
        Assert.assertNotNull(userService.queryUserByAccount("xiaohong"));
    }

    //测试注册用户
    @Test
    void signupVerify() {
        Assert.assertNotNull(userService.signupVerify("testaccount","testpwd","testnickname","13812345678",1));
    }

    //测试登录
    @Test
    void loginverify() {
        Assert.assertNotNull(userService.Loginverify("xiaohong","123456"));
    }

    //测试修改用户
    @Test
    void updateUser() {
        User user = new User();
        user.setUid(1);
        user.setAccount("xiaohong1");
        userService.updateUser(user);
        Assert.assertEquals(user, userService.getById(1));
    }

    //测试根据用户账号修改用户
    @Test
    void refreshUserByAccount() throws Exception {
        User user = new User();
        user.setAccount("xiaohong");
        user.setNickname("小红1");
        Assert.assertTrue(userService.refreshUserByAccount(user));
    }

    //测试修改密码
    @Test
    void modifyPwd() {
        Assert.assertNotNull(userService.ModifyPwd("xiaohong", "123456", "1234567"));
    }
}
