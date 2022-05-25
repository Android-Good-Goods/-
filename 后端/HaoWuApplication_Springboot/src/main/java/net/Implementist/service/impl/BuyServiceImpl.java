package net.Implementist.service.impl;

import net.Implementist.entity.Buy;
import net.Implementist.mapper.BuyMapper;
import net.Implementist.service.BuyService;
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
public class BuyServiceImpl extends ServiceImpl<BuyMapper, Buy> implements BuyService {

    @Override
    public boolean refreshBuyById(Buy buy) {
        return false;
    }
}
