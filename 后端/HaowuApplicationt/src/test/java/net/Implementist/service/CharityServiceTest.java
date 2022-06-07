package net.Implementist.service;

import net.Implementist.entity.Charity;
import net.Implementist.service.CharityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class CharityServiceTest {
    @Autowired
    private CharityService charityService;

    //测试查询用户的公益信息
    @Test
    void queryMypublish() {
        Assert.assertNotNull(charityService.queryMypublish("1"));
    }

    //测试修改公益信息
    @Test
    void refreshCharity() {
        Charity charity = new Charity();
        charity.setCid(1);
        charity.setCname("爱心公益");
        charityService.refreshCharity(charity);
    }

    //测试新增公益信息
    @Test
    void insertCharity() {
        Charity charity = new Charity();
        charity.setCid(4);
        charity.setCname("爱心公益");
        charityService.refreshCharity(charity);
    }
}
