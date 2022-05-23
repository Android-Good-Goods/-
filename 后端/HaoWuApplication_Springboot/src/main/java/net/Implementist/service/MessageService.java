package net.Implementist.service;

import cn.hutool.json.JSONArray;
import net.Implementist.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface MessageService extends IService<Message> {
    boolean insertMessage(Message message);

    JSONArray queryMessageData(String receiveid, int way);

    boolean refreshMessage(Message message);

}
