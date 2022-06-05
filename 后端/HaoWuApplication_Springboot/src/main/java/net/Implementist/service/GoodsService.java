package net.Implementist.service;

import net.Implementist.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface GoodsService extends IService<Goods> {
    List<Goods> queryType(String type, String state);

    List<Goods> queryGoodsMo(String Gname,String Gstate);

    List<Goods> queryFree(String state);

    List<Goods> queryEmergent(String emergent,String state);

    List<Goods> queryMyfree(String uid,String state);

    boolean refreshGoods(Goods target);

    boolean insertGoods(Goods goods);
}
