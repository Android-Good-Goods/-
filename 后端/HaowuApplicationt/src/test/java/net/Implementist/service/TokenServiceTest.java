package net.Implementist.service;

import net.Implementist.entity.Token;
import net.Implementist.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    //测试修改token
    @Test
    void updateTokenById() {
        Token token = new Token();
        token.setId(1);
        token.setApplication("YWMtoHXLYN2XEeyQ3fFm4LJhr4nO2i6DBDATqMrEDvRapzZ8f0UAxy9Is43bdLxU_7eiAgMAAAGBBKXPXzeeSAAh76R38_eO1iDPFBlAuEYsjlEUyC07HhH8fSa5WOdgZ1");
        tokenService.updateTokenById(token);
        Assert.assertEquals(token, tokenService.getById(1));
    }
}
