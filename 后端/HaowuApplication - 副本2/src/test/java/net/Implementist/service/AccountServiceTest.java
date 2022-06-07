package net.Implementist.service;

import net.Implementist.entity.Account;
import net.Implementist.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    //测试更新账户
    @Test
    void refreshAccountById() {
        //要更改的账户
        Account account = new Account();
        account.setAid(1);
        account.setAnumber("21420191130204751");
        account.setGid(3);
        account.setUid(1);
        account.setGuid(2);
        account.setAbill(88.0);
        account.setAtime("2022-06-30 20:47:50");
        account.setAstate(1);
        //测试更新账户是否成功
        Assert.assertTrue(accountService.RefreshAccountById(account));
        //根据账户id查询数据库得到更新后的账户
        Account realAccount = accountService.getById(1);
        //将数据库查到的账户与预期的账户对比，测试是否相等
        Assert.assertEquals(account, realAccount);
    }

    //测试新增账户是否成功
    @Test
    void insertAccount() {
        Account account = new Account();
        account.setAid(1);
        account.setAnumber("21420191130204751");
        account.setGid(3);
        account.setUid(1);
        account.setGuid(2);
        account.setAbill(88.0);
        account.setAtime("2022-06-30 20:47:50");
        account.setAstate(1);
        Assert.assertTrue(accountService.insertAccount(account));
    }
}
