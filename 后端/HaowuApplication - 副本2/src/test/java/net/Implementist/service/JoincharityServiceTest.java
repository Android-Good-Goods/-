package net.Implementist.service;

import net.Implementist.entity.Joincharity;
import net.Implementist.service.JoincharityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class JoincharityServiceTest {
    @Autowired
    private JoincharityService joincharityService;

    //测试用户是否参加公益
    @Test
    void verifyJoin() {
        Assert.assertTrue(joincharityService.verifyJoin("1", "1"));
    }

    //测试新增用户参加公益记录
    @Test
    void insertJoin() {
        Joincharity joincharity = new Joincharity();
        joincharity.setUid(1);
        Assert.assertTrue(joincharityService.insertJoin(joincharity));
    }
}
