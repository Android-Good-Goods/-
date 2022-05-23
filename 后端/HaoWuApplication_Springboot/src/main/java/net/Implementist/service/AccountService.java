package net.Implementist.service;

import net.Implementist.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface AccountService extends IService<Account> {
    boolean RefreshAccountById(Account account);
}
