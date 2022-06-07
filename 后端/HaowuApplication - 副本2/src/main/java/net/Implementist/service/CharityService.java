package net.Implementist.service;

import net.Implementist.entity.Charity;
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
public interface CharityService extends IService<Charity> {
    List<Charity> queryMypublish(String uid);
    boolean refreshCharity(Charity charity);
    boolean insertCharity(Charity charity);
}
