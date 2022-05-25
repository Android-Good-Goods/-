package net.Implementist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.Implementist.entity.Charity;
import net.Implementist.mapper.CharityMapper;
import net.Implementist.service.CharityService;
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
public class CharityServiceImpl extends ServiceImpl<CharityMapper, Charity> implements CharityService {

    @Override
    public List<Charity> queryMypublish(String uid) {
        LambdaQueryWrapper<Charity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Charity::getUid, uid);
        lambdaQueryWrapper.lt(Charity::getCstate, 2);
        List<Charity> list = list(lambdaQueryWrapper);
        return list;
    }

    @Override
    public boolean refreshCharity(Charity charity) {
        return updateById(charity);
    }
}
