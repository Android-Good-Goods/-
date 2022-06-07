package net.Implementist.service;

import net.Implementist.entity.Conments;
import net.Implementist.service.ConmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class ConmentsServiceTest {

    @Autowired
    private ConmentsService conmentsService;

    //测试新增评论
    @Test
    void insertGoodsCon() {
        Conments conments = new Conments();
        conments.setConid(1);
        conments.setUid(1);
        Assert.assertTrue(conmentsService.insertGoodsCon(conments));
    }
}
