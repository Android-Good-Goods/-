package net.Implementist.service;

import net.Implementist.entity.Conments;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface ConmentsService extends IService<Conments> {
    boolean insertGoodsCon(Conments conments);
}
