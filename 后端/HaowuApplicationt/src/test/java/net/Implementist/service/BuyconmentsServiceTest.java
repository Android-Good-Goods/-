package net.Implementist.service;

import net.Implementist.entity.Buyconments;
import net.Implementist.service.BuyconmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class BuyconmentsServiceTest {

    @Autowired
    private BuyconmentsService buyconmentsService;


    //测试新增评论
    @Test
    void insertBuyConments() {
        Buyconments buyconments = new Buyconments();
        buyconments.setBconid(2);
        buyconments.setBid(2);
        buyconments.setUid(2);
        buyconments.setBuid(2);
        buyconments.setBcontime("2022-05-27 23:09:57");
        buyconments.setBconcontent("我有一个，在你们私聊");
        buyconments.setBconstate(1);
        Assert.assertTrue(buyconmentsService.insertBuyConments(buyconments));
    }

    //测试修改评论
    @Test
    void refreshConmentsByBconid() {
        Buyconments buyconments = new Buyconments();
        buyconments.setBconid(1);
        buyconments.setBid(2);
        buyconments.setUid(2);
        buyconments.setBuid(2);
        buyconments.setBcontime("2022-05-27 23:09:57");
        buyconments.setBconcontent("我有一个，在你们私聊");
        buyconments.setBconstate(1);
        Assert.assertTrue(buyconmentsService.refreshConmentsByBconid(buyconments));
    }
}
