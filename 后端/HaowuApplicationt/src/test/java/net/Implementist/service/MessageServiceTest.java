package net.Implementist.service;

import net.Implementist.entity.Message;
import net.Implementist.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    //测试新增消息
    @Test
    void insertMessage() {
        Message message = new Message();
        message.setMtitle("【售出通知】");
        Assert.assertTrue(messageService.insertMessage(message));
    }

    //测试根据接收人id和消息状态查询消息
    @Test
    void queryMessageData() {
        Assert.assertNotNull(messageService.queryMessageData("1", 1));
    }

    //测试修改消息
    @Test
    void refreshMessage() {
        Message message = new Message();
        message.setMid(1);
        message.setMtitle("【售出通知1】");
        Assert.assertTrue(messageService.refreshMessage(message));
    }
}
