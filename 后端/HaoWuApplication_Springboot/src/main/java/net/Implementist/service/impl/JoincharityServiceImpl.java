package net.Implementist.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.Implementist.entity.Joincharity;
import net.Implementist.mapper.JoincharityMapper;
import net.Implementist.service.JoincharityService;
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
public class JoincharityServiceImpl extends ServiceImpl<JoincharityMapper, Joincharity> implements JoincharityService {

    @Override
    public boolean verifyJoin(String uid, String cid) {
        LambdaQueryWrapper<Joincharity> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Joincharity::getUid, uid).eq(Joincharity::getCid, cid);
        List<Joincharity> list = list(lambdaQueryWrapper);
        return CollectionUtil.isNotEmpty(list) ? true : false;
    }

    @Override
    public boolean insertJoin(Joincharity joincharity) {
        return save(joincharity);
    }
}
