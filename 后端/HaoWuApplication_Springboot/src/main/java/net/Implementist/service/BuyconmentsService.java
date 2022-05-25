package net.Implementist.service;

import net.Implementist.entity.Buyconments;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface BuyconmentsService extends IService<Buyconments> {
    boolean insertBuyConments(Buyconments buyconments);
    boolean refreshConmentsByBconid(Buyconments entity);
}
