package net.Implementist.service.impl;

import net.Implementist.entity.Collect;
import net.Implementist.mapper.CollectMapper;
import net.Implementist.service.CollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Override
    public boolean insertCollect(Collect entity) {
        return save(entity);
    }
}
