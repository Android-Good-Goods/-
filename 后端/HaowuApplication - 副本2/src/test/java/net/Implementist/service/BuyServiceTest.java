package net.Implementist.service;

import net.Implementist.entity.Buy;
import net.Implementist.service.BuyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

@SpringBootTest
class BuyServiceTest {

    @Autowired
    private BuyService buyService;

    //测试修改求购商品
    @Test
    void refreshBuyById() {
        Buy buy = new Buy();
        buy.setBid(5);
        buy.setBname("电脑");
        buy.setUid(2);
        buy.setBdetail("求购电脑");
        buy.setBsprice(1000.0);
        buy.setBsprice(5000.0);
        buy.setBtype("数码");
        buy.setBhownew("八五新");
        buy.setBgetway("邮寄");
        buy.setBaddress("浙江省 杭州市");
        buy.setBtime("2022 05-11 23:05:09");
        buy.setBimage("D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/buydata/IMG_20190914_102013.jpg");
        buy.setBstate(1);
        Assert.assertTrue(buyService.refreshBuyById(buy));
    }

    //测试新增求购商品
    @Test
    void insertBuy() {
        Buy buy = new Buy();
        buy.setBid(1);
        buy.setBname("电脑");
        buy.setUid(2);
        buy.setBdetail("求购电脑");
        buy.setBsprice(1000.0);
        buy.setBsprice(5000.0);
        buy.setBtype("数码");
        buy.setBhownew("八五新");
        buy.setBgetway("邮寄");
        buy.setBaddress("浙江省 杭州市");
        buy.setBtime("2022 05-11 23:05:09");
        buy.setBimage("D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/buydata/IMG_20190914_102013.jpg");
        buy.setBstate(1);
        Assert.assertTrue(buyService.insertBuy(buy));
    }
}
