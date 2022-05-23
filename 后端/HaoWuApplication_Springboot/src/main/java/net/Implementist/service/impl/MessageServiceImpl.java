package net.Implementist.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.Implementist.entity.Message;
import net.Implementist.mapper.MessageMapper;
import net.Implementist.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public boolean insertMessage(Message message) {
        return save(message);
    }

    @Override
    public JSONArray queryMessageData(String receiveid, int way) {
        JSONArray messages = new JSONArray();
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("Receiveid", receiveid);
        if ( 2 == way) {
            messageQueryWrapper.eq("Mstate", 1);
        } else if(1 == way) {
            messageQueryWrapper.ge("Mstate", 1);
            messageQueryWrapper.le("Mstate", 2);
        }
        List<Message> list = list(messageQueryWrapper);
        for (Message message : list) {
            JSONObject param = new JSONObject();
            param.put("mid", String.valueOf(message.getMid()).trim());
            param.put("receiveid", String.valueOf(message.getReceiveid()).trim());
            param.put("mtitle", message.getMtitle().trim());
            param.put("mcontent", message.getMcontent());
            param.put("mtime", message.getMtime());
            param.put("mstate", message.getMstate().toString());
            messages.add(param);
        }
        return messages;
    }

    @Override
    public boolean refreshMessage(Message message) {
        return updateById(message);
    }
}
