package net.Implementist.service;

import cn.hutool.json.JSONObject;
import net.Implementist.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface UserService extends IService<User> {

    User queryUserByAccount(String account);

    JSONObject signupVerify(String account, String pwd, String nickName, String tel, int sex);

    JSONObject Loginverify(String account,String password);

    void updateUserState(User user);
}
