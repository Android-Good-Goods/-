package net.Implementist.controller;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Account;
import net.Implementist.entity.User;
import net.Implementist.service.AccountService;
import net.Implementist.service.GoodsService;
import net.Implementist.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
public class BuyController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(path = "/Mybuy_Servlet", produces = "text/html;charset=utf-8")
    public String myPublish() throws JsonProcessingException {
        //定义变量
        String gid;                                 //商品id
        JSONArray accountlist = new JSONArray();    //发布列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject gaudata = new JSONObject();       //用户和商品数据
        JSONArray gaulist = new JSONArray();         //用户和商品数据列表
        JSONObject goodsdata = new JSONObject();


        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "getmybuy":               //我买到的
                    User user = userService.queryUserByAccount(request.getParameter("account").trim());
                    LambdaQueryWrapper<Account> accoutWrapper = new LambdaQueryWrapper<>();
                    accoutWrapper.eq(Account::getUid, user.getUid());
                    List<Account> accountList = accountService.list(accoutWrapper);
                    if(!CollectionUtil.isEmpty(accountList)){
                        params.put("code", "1");           //我买到的不为空
                        userdata = null;                   //将userdata中的数据清空
                        for (Account account : accountList) {
                            gaudata.put("accountdata", account);
                            gaudata.put("goodsdata", goodsService.getById(account.getGid()));
                            gaudata.put("userdata", userService.getById(account.getGuid()));
                            gaulist.add(gaudata);
                        }
                        params.put("data", gaulist);
                    }else {
                        params.put("code", "0");       //没有发布商品
                    }
                    break;
                case "getmyout":              //我卖出的
                    User account1 = userService.queryUserByAccount(request.getParameter("account").trim());
                    LambdaQueryWrapper<Account> accout1Wrapper = new LambdaQueryWrapper<>();
                    accout1Wrapper.eq(Account::getGuid, account1.getUid());
                    List<Account> accountlist1 = accountService.list(accout1Wrapper);
                    if(!CollectionUtil.isEmpty(accountlist1)){
                        params.put("code", "1");           //我卖出的不为空
                        userdata = null;                   //将userdata中的数据清空
                        for (Account account : accountlist1) {
                            gaudata.put("accountdata", account);
                            gaudata.put("goodsdata", goodsService.getById(account.getGid()));
                            gaudata.put("userdata", userService.getById(account.getGuid()));
                            gaulist.add(gaudata);
                        }
                        params.put("data", gaulist);
                    } else {
                        params.put("code", "0");       //没有发布商品
                    }
                    break;
                case "deleteaccount":
                    Account target = new Account();
                    target.setAid(Integer.valueOf(request.getParameter("aid").trim()));
                    target.setAstate(6);
                    boolean delresult = accountService.RefreshAccountById(target);
                    if (!delresult) {
                        params.put("code", "0");   //删除订单失败
                    } else {
                        params.put("code", "1");   //删除订单成功
                    }
                    break;
                default:System.out.println("信息返回失败！");break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }
}

