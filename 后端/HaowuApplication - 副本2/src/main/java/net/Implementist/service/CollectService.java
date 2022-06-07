package net.Implementist.service;

import net.Implementist.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface CollectService extends IService<Collect> {
    boolean insertCollect(Collect entity);
}
