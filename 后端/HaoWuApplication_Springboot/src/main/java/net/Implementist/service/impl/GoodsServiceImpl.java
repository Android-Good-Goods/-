package net.Implementist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.Implementist.entity.Goods;
import net.Implementist.mapper.GoodsMapper;
import net.Implementist.service.GoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public List<Goods> queryType(String type, String state) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("Gtype", type);
        wrapper.eq("Gstate", state);
        return list(wrapper);
    }

    @Override
    public List<Goods> queryGoodsMo(String name, String state) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("Gname", name);
        wrapper.eq("Gstate", state);
        return list(wrapper);
    }

    @Override
    public List<Goods> queryFree(String state) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("Gprice", 0);
        wrapper.eq("Gstate", state);
        return list(wrapper);
    }

    @Override
    public List<Goods> queryEmergent(String emergent, String state) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("Gemergent", emergent);
        wrapper.eq("Gstate", state);
        return list(wrapper);
    }

    @Override
    public List<Goods> queryMyfree(String uid, String state) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("Gprice", 0);
        wrapper.eq("Uid", uid);
        wrapper.eq("Gstate", state);
        return list(wrapper);
    }

    @Override
    public boolean refreshGoods(Goods target) {
        return updateById(target);
    }

}
