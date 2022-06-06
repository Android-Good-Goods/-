package net.Implementist.service;

import net.Implementist.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface TokenService extends IService<Token> {
    void updateTokenById(Token token);
}
