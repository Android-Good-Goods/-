package net.Implementist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.Implementist.entity.User;
import net.Implementist.mapper.UserMapper;
import net.Implementist.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.Implementist.util.HXUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User queryUserByAccount(String account) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getAccount, account);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public JSONObject signupVerify(String account, String pwd, String nickName, String tel, int sex) {
        JSONObject vresult = new JSONObject();
        Map<String, String> params = new HashMap<>();
        User user = queryUserByAccount(account);
        if (null == user) {
            //验证环信账号有没有注册成功
            boolean verifyHx = HXUtil.addUser(account, pwd, nickName);
            if(verifyHx){
                user = new User();
                user.setAccount(account);
                user.setPassword(pwd);
                user.setNickname(nickName);
                user.setTel(tel);
                user.setHxid(account);
                user.setSex(sex);
                boolean insertResult = save(user);  //验证数据是否插入成功
                if (!insertResult) {
                    params.put("code", "0");   //注册失败，数据插入数据库失败
                } else {
                    params.put("code", "1");   //注册成功
                }
            }else{
                params.put("code", "3");   //注册失败，注册环信账号失败
            }
        } else {
            //用户名已存在
            params.put("code", "2");
        }
        vresult.put("params", params);
        return vresult;
    }

    @Override
    public JSONObject Loginverify(String account, String password) {
        JSONObject vresult = new JSONObject();
        //密码验证结果
        User user = queryUserByAccount(account);
        Map<String, String> code = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        if (null != user && password.equals(user.getPassword())) {
            if(user.getState() == 1){
                //将用户的登录状态改为2
                User target = new User();
                target.setUid(user.getUid());
                target.setState(2);
                updateUser(target);
                //拼接返回数据
                code.put("code", "1");
                data.put("account", user.getAccount().trim());
                data.put("password", user.getPassword().trim());
                data.put("hxid", user.getHxid());
                data.put("nickname", user.getNickname());
                data.put("headphoto", user.getHeadphoto().trim());
                data.put("sex", String.valueOf(user.getSex()).trim());
                data.put("balance", String.valueOf(user.getBalance()).trim());
                data.put("address", user.getAddress());
                data.put("school", user.getSchool());
                data.put("reputation", String.valueOf(user.getReputation()).trim());
                data.put("tel", user.getTel());
                System.out.println("登录成功！");
            }else if(user.getState() == 2){
                //用户在其他设备已登录
                code.put("code", "2");
                System.out.println("用户在其他设备登录！");
            }else{
                //登录失败
                code.put("code", "0");
                System.out.println("登录失败！");
            }
        } else {
            //登录失败
            code.put("code", "0");
            System.out.println("登录失败！");
        }
        vresult.put("code", code);
        vresult.put("data", data);
        return vresult;
    }

    @Override
    public void updateUser(User user) {
        updateById(user);
    }

    @Override
    public boolean refreshUserByAccount(User target) throws Exception {
        String account = target.getAccount();
        if (StringUtils.isBlank(account)) {
            throw new Exception("account为空,不能查询");
        }
        User user = queryUserByAccount(account);
        if (null != user) {
            target.setUid(user.getUid());
            return updateById(target);
        }
        return false;
    }

    //修改密码并返回处理结果
    @Override
    public JSONObject ModifyPwd(String account,String password,String newpassword) {
        //定义变量
        JSONObject vresult = new JSONObject();
        Map<String, String> data = new HashMap<>();
        try {
            //密码验证结果
            User verifyResult = queryUserByAccount(account);      //验证用户账号是否已经存在
            if(null != verifyResult)
            {
                if(HXUtil.updatePassword(account, newpassword)){
                    User target = new User();
                    target.setAccount(account).setPassword(newpassword.trim());
                    boolean modify = refreshUserByAccount(target);
                    if (modify) {
                        vresult.put("code", "1");   //密码修改成功
                        data.put("password", newpassword.trim());
                        vresult.put("data", data);
                    } else {
                        vresult.put("code", "0");   //密码修改失败
                    }
                } else {
                    vresult.put("code", "0");   //密码修改失败
                }
            }else{
                vresult.put("code", "0"); //密码修改失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vresult;
    }


}
