package net.Implementist.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import net.Implementist.entity.Buyconments;
import net.Implementist.mapper.BuyconmentsMapper;
import net.Implementist.service.BuyconmentsService;
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
public class BuyconmentsServiceImpl extends ServiceImpl<BuyconmentsMapper, Buyconments> implements BuyconmentsService {

    @Override
    public boolean insertBuyConments(Buyconments buyconments) {
        return save(buyconments);
    }

    @Override
    public boolean refreshConmentsByBconid(Buyconments entity) {
        LambdaUpdateWrapper<Buyconments> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Buyconments::getBconid, entity.getBconid());
        return update(entity, lambdaUpdateWrapper);
    }
}
