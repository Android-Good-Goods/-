package net.Implementist.service;

import net.Implementist.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONArray;

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
