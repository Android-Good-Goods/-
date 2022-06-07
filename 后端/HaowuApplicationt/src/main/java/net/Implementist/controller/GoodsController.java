package net.Implementist.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.Implementist.entity.*;
import net.Implementist.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Slf4j
public class GoodsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private ConmentsService conmentsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/Goodslist_Servlet", produces = "text/html;charset=utf-8")
    public String index() throws JsonProcessingException {
        JSONObject uagdata = new JSONObject();
        JSONArray uaglist = new JSONArray();
        JSONObject params = new JSONObject();
        JSONObject uabdata = new JSONObject();
        JSONArray uablist = new JSONArray();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            List<Goods> goods = new ArrayList<>();
            switch (requesttop)
            {
                case "goodstype":
                    goods = goodsService.queryType(request.getParameter("type").trim(), request.getParameter("state").trim());
                    for (Goods good : goods) {
                        uagdata.put("userdata", userService.getById(good.getUid()));
                        uagdata.put("goodsdata", good);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品类型请求成功！");
                    break;
                case "goodsname":
                    goods = goodsService.queryGoodsMo(request.getParameter("name").trim(), request.getParameter("state").trim());
                    for (Goods good : goods) {
                        uagdata.put("userdata", userService.getById(good.getUid()));
                        uagdata.put("goodsdata", good);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    log.info("商品名请求成功！");
                    break;
                case "freegoods":
                    goods = goodsService.queryFree(request.getParameter("state").trim());
                    for (Goods good : goods) {
                        uagdata.put("userdata", userService.getById(good.getUid()));
                        uagdata.put("goodsdata", good);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    log.info("商品名请求成功！");
                    break;
                case "jigoods":
                    goods = goodsService.queryEmergent("2",request.getParameter("state").trim());
                    for (Goods good : goods) {
                        uagdata.put("userdata", userService.getById(good.getUid()));
                        uagdata.put("goodsdata", good);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    break;
                case "myfree":
                    User userdata = userService.queryUserByAccount(request.getParameter("account").trim());
                    goods = goodsService.queryMyfree(String.valueOf(userdata.getUid()),request.getParameter("state").trim());
                    for (Goods good : goods) {
                        uagdata.put("userdata", userService.getById(good.getUid()));
                        uagdata.put("goodsdata", good);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    break;
                case "goodsbuy":
                    LambdaQueryWrapper<Buy> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Buy::getBstate, request.getParameter("state").trim());
                    List<Buy> buys = buyService.list(queryWrapper);
                    for (Buy buy : buys) {
                        userdata = userService.getById(buy.getUid());
                        uabdata.put("userdata", userdata);
                        uabdata.put("buydata", buy);
                        uablist.add(uabdata);
                    }
                    params.put("code", "1");
                    params.put("data", uablist);
                    System.out.println("请求成功！");
                    break;
                default:System.out.println("商品信息返回失败！");break;
            }
        }catch(NumberFormatException e){
            log.error("获取商品失败");
        }
        return objectMapper.writeValueAsString(params);


    }

    @RequestMapping(path = "/Goodsdetail_Servlet", produces = "text/html;charset=utf-8")
    public String Goodsdetail_Servlet() throws JsonProcessingException {
        //定义变量
        String uid;
        //Account account = new Account();
        Message message = new Message();
        User user = new User();
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
                case "getcollect":
                    JSONObject collect = new JSONObject();
                    user = userService.queryUserByAccount(request.getParameter("account").trim());
                    LambdaQueryWrapper<Collect> collectWrapper = new LambdaQueryWrapper<>();
                    collectWrapper.eq(Collect::getGid, request.getParameter("gid").trim());
                    collectWrapper.eq(Collect::getUid, user.getUid());
                    List<Collect> collects = collectService.list(collectWrapper);
                    if (CollectionUtil.isEmpty(collects)) {
                        params.put("code", "0");   //商品未被收藏
                    } else {
                        params.put("code", "1");   //商品已被收藏
                        params.put("colid", collects.get(0).getColid());
                    }
                    break;
                case "collect":

                    user = userService.queryUserByAccount(request.getParameter("account").trim());
                    Collect entity = new Collect();
                    entity.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    entity.setUid(user.getUid());
                    entity.setGuid(Integer.valueOf(request.getParameter("uid").trim()));
                    entity.setColtime(request.getParameter("time").trim());
                    boolean result = collectService.insertCollect(entity);
                    if (!result) {
                        params.put("code", "0");   //收藏失败
                    } else {
                        params.put("code", "1");   //收藏成功
                    }
                    break;
                case "uncollect":
                    boolean unresult = collectService.removeById(request.getParameter("colid").trim());
                    if (!unresult) {
                        params.put("code", "0");   //取消收藏失败
                    } else {
                        params.put("code", "1");   //取消收藏成功
                    }
                    break;
                case "conments":
                    user = userService.queryUserByAccount(request.getParameter("account").trim());
                    Conments con = new Conments();
                    con.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    con.setUid(user.getUid());
                    con.setGuid(Integer.valueOf(request.getParameter("uid").trim()));
                    con.setConcontent(request.getParameter("concontent").trim());
                    con.setContime(request.getParameter("contime").trim());
                    con.setConstate(Integer.valueOf(request.getParameter("constate").trim()));
                    boolean conresult = conmentsService.insertGoodsCon(con);
                    if (!conresult) {
                        params.put("code", "0");   //评论失败
                    } else {
                        params.put("code", "1");   //评论成功
                        if(!user.getUid().equals(Integer.valueOf(request.getParameter("uid").trim()))){
                            message.setMtitle("【评论通知】");
                            message.setMcontent("您的商品【" + request.getParameter("gname").trim() + "】有一条新评论！");
                            message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                            message.setMtime(request.getParameter("contime").trim());
                            message.setMstate(1);
                            messageService.insertMessage(message);
                        }
                    }
                    break;
                case "getconments":
                    LambdaQueryWrapper<Conments> conmentsWrapper = new LambdaQueryWrapper<>();
                    conmentsWrapper.eq(Conments::getGid, request.getParameter("gid").trim());
                    List<Conments> conments = conmentsService.list(conmentsWrapper);

                    if (CollectionUtil.isEmpty(conments)) {
                        params.put("code", "0");   //没有评论
                    } else {
                        params.put("code", "1");   //有评论，并把评论列表写进返回的数据里
                        for (Conments conment : conments) {
                            uacdata.put("userdata", userService.getById(conment.getUid()));
                            uacdata.put("conmentsdata", conment);
                            uaclist.add(uacdata);
                        }
                        params.put("data", uaclist);
                    }
                    break;
                case "deletecomments":
                    int delresult = 0;
                    if (delresult <= 0) {
                        params.put("code", "0");   //删除评论失败
                    } else {
                        params.put("code", "1");   //删除评论成功
                    }
                    break;
                case "buygoods":
                    //获取系统时间
                    final Date d = new Date();
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String useraccount = request.getParameter("account").trim();
                    //查询买家数据
                    User user1 = userService.queryUserByAccount(useraccount);
                    //根据买家id+卖家id+商品id+系统时间的形式形成订单号
                    String anumber = user.getUid() + request.getParameter("guid").trim() + request.getParameter("gid").trim() + sdf.format(d);
                    //设置订单数据
                    Account target = new Account();
                    target.setAnumber(anumber);
                    target.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    target.setUid(user.getUid());
                    target.setGuid(Integer.valueOf(request.getParameter("guid").trim()));
                    target.setAbill(Double.valueOf(request.getParameter("abill").trim()));
                    target.setAtime(request.getParameter("atime").trim());
                    target.setAstate(Integer.valueOf(request.getParameter("astate").trim()));
                    //存储订单数据，并获取返回结果
                    boolean accountrs = accountService.insertAccount(target);
                    //计算买家剩余金额并修改买家余额
                    double balance = user.getBalance() - Double.valueOf(request.getParameter("abill").trim());
                    User userEntity = new User();
                    userEntity.setAccount(user.getAccount()).setBalance(balance);
                    boolean userrs = userService.refreshUserByAccount(userEntity);
                    //修改商品状态，将商品状态改成交易中
                    Goods goodsEntity = new Goods();
                    goodsEntity.setGid(Integer.valueOf(request.getParameter("gid").trim())).setGstate(2);
                    boolean goodsrs = goodsService.refreshGoods(goodsEntity);
                    //根据返回结果判断数据是否修改成功
                    if(accountrs && userrs && goodsrs){
                        params.put("code", "1");
                        params.put("balance", String.valueOf(balance));
                        message.setMtitle("【商品售出】");
                        message.setMcontent("您的商品【" + request.getParameter("gname").trim() + "】已有人拍下，请及时发货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("guid").trim()));
                        message.setMtime(request.getParameter("atime").trim());
                        message.setMstate(1);
                        messageService.insertMessage(message);
                        System.out.println("交易成功！");
                    }else{
                        params.put("code", "0");
                        System.out.println("交易失败！");
                    }
                    break;
                case "setscannum":
                    Goods goods = goodsService.getById(request.getParameter("gid").trim());
                    goods.setGscannum(goods.getGscannum() + 1);
                    Goods goodEntity = new Goods();
                    goodEntity.setGid(goods.getGid()).setGscannum(goods.getGscannum() + 1);
                    boolean scanrs = goodsService.refreshGoods(goodEntity);
                    if (!scanrs) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(goods.getGscannum()));
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

