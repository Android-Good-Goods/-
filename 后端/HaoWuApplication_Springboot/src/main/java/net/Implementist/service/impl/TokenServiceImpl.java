package net.Implementist.service.impl;

import net.Implementist.entity.Token;
import net.Implementist.mapper.TokenMapper;
import net.Implementist.service.TokenService;
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
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Override
    public void updateTokenById(Token token) {
        updateById(token);
    }
}
