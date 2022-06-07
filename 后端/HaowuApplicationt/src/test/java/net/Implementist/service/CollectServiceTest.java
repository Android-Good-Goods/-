package net.Implementist.service;

import net.Implementist.entity.Collect;
import net.Implementist.service.CollectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class CollectServiceTest {

    @Autowired
    private CollectService collectService;

    //测试新增收藏
    @Test
    void insertCollect() {
        Collect collect = new Collect();
        collect.setColid(1);
        collect.setUid(1);
        Assert.assertTrue(collectService.insertCollect(collect));
    }
}
