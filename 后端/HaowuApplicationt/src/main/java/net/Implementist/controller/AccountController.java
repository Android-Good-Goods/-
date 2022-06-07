package net.Implementist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Account;
import net.Implementist.entity.Goods;
import net.Implementist.entity.Message;
import net.Implementist.entity.User;
import net.Implementist.service.AccountService;
import net.Implementist.service.GoodsService;
import net.Implementist.service.MessageService;
import net.Implementist.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
public class AccountController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(path = "/Account_Servlet", produces = "text/html;charset=utf-8")
    public String account() throws JsonProcessingException {
        //定义变量
        User user = new User();
        Message message = new Message();
        JSONObject params = new JSONObject();        //返回数据

        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            Account account = new Account();
            switch (requesttop)
            {
                case "cancleaccount":
                    user = userService.getById(request.getParameter("uid").trim());
                    account = new Account();
                    account.setAid(Integer.valueOf(request.getParameter("aid").trim())).setAstate(5);
                    boolean canresult = accountService.RefreshAccountById(account);
                    //计算买家剩余金额并修改买家余额
                    double balance = user.getBalance() + Double.valueOf(request.getParameter("abill").trim());
                    User target = new User();
                    target.setAccount(user.getAccount()).setBalance(balance);
                    boolean userrs = userService.refreshUserByAccount(target);
                    //修改商品状态，将商品状态改成在售状态
                    Goods goodTarget = new Goods();
                    goodTarget.setGid(Integer.valueOf(request.getParameter("gid").trim())).setGstate(1);
                    boolean goodsrs = goodsService.refreshGoods(goodTarget);
                    if (canresult && userrs  && goodsrs) {
                        //取消订单成功
                        params.put("code", "1");
                        message.setMtitle("【取消订单】");
                        message.setMcontent("商品【" + request.getParameter("gname").trim() + "】订单已取消，请与对方联系！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        messageService.insertMessage(message);
                    } else {
                        //取消订单失败
                        params.put("code", "0");
                    }
                    break;
                case "affrimsend":
                    account = new Account();
                    account.setAid(Integer.valueOf(request.getParameter("aid").trim())).setAstate(2);
                    boolean sendresult = accountService.RefreshAccountById(account);
                    if(sendresult){
                        params.put("code", "1");   //发货成功
                        message.setMtitle("【发货通知】");
                        message.setMcontent("您购买的商品【" + request.getParameter("gname").trim() + "】已发货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        messageService.insertMessage(message);
                    } else {
                        params.put("code", "0");   //发货失败
                    }
                    break;
                case "affrimget":
                    user = userService.getById(request.getParameter("uid").trim());
                    account = new Account();
                    account.setAid(Integer.valueOf(request.getParameter("aid").trim())).setAstate(3);
                    boolean getresult = accountService.RefreshAccountById(account);
                    //计算卖家剩余金额并修改卖家余额
                    double getbalance = user.getBalance() + Double.valueOf(request.getParameter("abill").trim());
                    User target1 = new User();
                    target1.setAccount(user.getAccount()).setBalance(getbalance);
                    boolean userresult = userService.refreshUserByAccount(target1);

                    //修改商品状态，将商品状态改成在售状态
                    Goods goodTarget1 = new Goods();
                    goodTarget1.setGid(Integer.valueOf(request.getParameter("gid").trim())).setGstate(3);
                    boolean goodsresult = goodsService.refreshGoods(goodTarget1);
                    if (getresult && userresult && goodsresult ) {
                        params.put("code", "1");   //确认收货成功
                        message.setMtitle("【收货通知】");
                        message.setMcontent("您售出的商品【" + request.getParameter("gname").trim() + "】买家已确认收货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("guid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        messageService.insertMessage(message);
                    } else {
                        params.put("code", "0");   //确认收货失败
                    }
                    break;
                default:System.out.println("信息返回失败！");break;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }

}

