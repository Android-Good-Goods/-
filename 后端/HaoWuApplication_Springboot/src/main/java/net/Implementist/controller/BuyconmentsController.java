package net.Implementist.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Buy;
import net.Implementist.entity.Buyconments;
import net.Implementist.entity.Message;
import net.Implementist.entity.User;
import net.Implementist.service.BuyService;
import net.Implementist.service.BuyconmentsService;
import net.Implementist.service.MessageService;
import net.Implementist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
public class BuyconmentsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private BuyconmentsService buyconmentsService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(path = "/Buydetail_Servlet", produces = "text/html;charset=utf-8")
    public String account() throws JsonProcessingException {
        //定义变量
        User user = new User();
        Message message = new Message();
        JSONArray conmentslist = new JSONArray();    //评论列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject uacdata = new JSONObject();       //用户和评论数据
        JSONArray uaclist = new JSONArray();         //用户和评论数据列表
        JSONObject conmentsdata = new JSONObject();


        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "conments":
                    User account = userService.queryUserByAccount(request.getParameter("account").trim());
                    Buyconments con = new Buyconments();
                    con.setBid(Integer.valueOf(request.getParameter("bid").trim()));
                    con.setUid(account.getUid());
                    con.setBuid(Integer.valueOf(request.getParameter("uid").trim()));
                    con.setBconcontent(request.getParameter("bconcontent").trim());
                    con.setBcontime(request.getParameter("bcontime").trim());
                    con.setBconstate(Integer.valueOf(request.getParameter("bconstate").trim()));
                    boolean conresult = buyconmentsService.insertBuyConments(con);
                    if (!conresult) {
                        params.put("code", "0");   //评论失败
                    } else {
                        params.put("code", "1");   //评论成功
                        if(!account.getUid().equals(Integer.valueOf(request.getParameter("uid").trim()))){
                            message.setMtitle("【评论通知】");
                            message.setMcontent("您的求购商品【" + request.getParameter("bname").trim() + "】有一条新评论！");
                            message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                            message.setMtime(request.getParameter("bcontime").trim());
                            message.setMstate(1);
                            messageService.insertMessage(message);
                        }
                    }
                    break;
                case "getconments":
                    LambdaQueryWrapper<Buyconments> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(Buyconments::getBid, request.getParameter("bid").trim());
                    List<Buyconments> list = buyconmentsService.list(lambdaQueryWrapper);
                    if (CollectionUtil.isEmpty(list)) {
                        params.put("code", "0");   //没有评论
                    } else {
                        params.put("code", "1");   //有评论，并把评论列表写进返回的数据里
                        for (Buyconments buyconments : list) {
                            uacdata.put("userdata", userService.getById(buyconments.getUid()));
                            uacdata.put("conmentsdata", buyconments);
                            uaclist.add(uacdata);
                        }
                        params.put("data", uaclist);
                    }
                    break;
                case "deletecomments":
                    Buyconments entity = new Buyconments();
                    entity.setBconid(Integer.valueOf(request.getParameter("bconid").trim()));
                    entity.setBconstate(2);
                    boolean delresult = buyconmentsService.insertBuyConments(entity);
                    if (!delresult) {
                        params.put("code", "0");   //删除评论失败
                    } else {
                        params.put("code", "1");   //删除评论成功
                    }
                    break;
                case "setscannum":
                    Buy buy = buyService.getById(request.getParameter("bid").trim());
                    Buy target = new Buy();
                    target.setBid(buy.getBid());
                    target.setBscannum(buy.getBscannum() + 1);
                    boolean scanrs = buyService.refreshBuyById(target);
                    if (!scanrs) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(buy.getBscannum()));
                    }
                    break;
                default:System.out.println("信息返回失败！");break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }
}

