package net.Implementist.controller;


import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Buy;
import net.Implementist.entity.Charity;
import net.Implementist.entity.Goods;
import net.Implementist.entity.User;
import net.Implementist.service.BuyService;
import net.Implementist.service.CharityService;
import net.Implementist.service.GoodsService;
import net.Implementist.service.UserService;
import net.Implementist.util.ImageDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
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
public class PublishController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private CharityService charityService;

    @RequestMapping(path = "/Publish_Servlet", produces = "text/html;charset=utf-8")
    public String publish() throws JsonProcessingException {
        //定义变量
        String Imageurl;
        String Imagestr;
        String Imagename;
        boolean imageResult;
        User user = new User();
        Goods goods = new Goods();
        Buy buy = new Buy();
        Charity charity = new Charity();
        JSONObject params = new JSONObject();
        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "publish":
                    boolean goodsResult;
                    JSONObject emgrgentpublish = new JSONObject();
                    String apppath = request.getRealPath("/")+"appdata";
                    File appfile = new File(apppath);
                    if(!appfile.exists()){
                        appfile.mkdir();
                    }
                    String goodsdata = apppath + "/" + "goodsdata";
                    File goodsfile = new File(goodsdata);
                    if(!goodsfile.exists()){
                        goodsfile.mkdir();
                    }
                    user = userService.queryUserByAccount(request.getParameter("uaccount").trim());
                    goods.setUid(user.getUid());
                    goods.setGname(request.getParameter("gname").trim());
                    goods.setGdetail(request.getParameter("gdetail").trim());
                    goods.setGprice(Double.valueOf(request.getParameter("gprice").trim()));
                    goods.setGoprice(Double.valueOf(request.getParameter("goprice").trim()));
                    goods.setGtype(request.getParameter("gtype").trim());
                    goods.setGhownew(request.getParameter("ghownew").trim());
                    goods.setGgetway(request.getParameter("ggetway").trim());
                    goods.setGemergent(Integer.valueOf(request.getParameter("gemergent").trim()));
                    goods.setGtime(request.getParameter("gtime").trim());
                    goods.setGstate(Integer.valueOf(request.getParameter("gstate").trim()));
                    goods.setGaddress(request.getParameter("gaddress").trim());
                    goods.setGscannum(Integer.valueOf(request.getParameter("gscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = goodsdata + "/" + Imagename;
                    goods.setGimage(Imageurl.trim());
                    imageResult = ImageDeal.string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        goodsResult = goodsService.insertGoods(goods);
                        if(goodsResult){
                            emgrgentpublish.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            emgrgentpublish.put("code","0");
                            System.out.println("发布失败！");
                        }
                    }else{
                        emgrgentpublish.put("code","0");
                        System.out.println("发布失败！");
                    }
                    break;
                case "republish":
                    goods.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    goods.setGname(request.getParameter("gname").trim());
                    goods.setGdetail(request.getParameter("gdetail").trim());
                    goods.setGprice(Double.valueOf(request.getParameter("gprice").trim()));
                    goods.setGoprice(Double.valueOf(request.getParameter("goprice").trim()));
                    goods.setGtype(request.getParameter("gtype").trim());
                    goods.setGhownew(request.getParameter("ghownew").trim());
                    goods.setGgetway(request.getParameter("ggetway").trim());
                    goods.setGemergent(Integer.valueOf(request.getParameter("gemergent").trim()));
                    goods.setGaddress(request.getParameter("gaddress").trim());
                    boolean rs = goodsService.refreshGoods(goods);
                    if(!rs){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功
                    }
                    break;
                case "publishbuy":
                    JSONObject publishbuy = new JSONObject();
                    String buyapppath = request.getRealPath("/")+"appdata";
                    File buyappfile = new File(buyapppath);
                    if(!buyappfile.exists()){
                        buyappfile.mkdir();
                    }
                    String buydata = buyapppath + "/" + "buydata";
                    File buyfile = new File(buydata);
                    if(!buyfile.exists()){
                        buyfile.mkdir();
                    }
                    user = userService.queryUserByAccount(request.getParameter("uaccount").trim());
                    buy.setUid(user.getUid());
                    buy.setBname(request.getParameter("bname").trim());
                    buy.setBdetail(request.getParameter("bdetail").trim());
                    buy.setBsprice(Double.valueOf(request.getParameter("bsprice").trim()));
                    buy.setBbprice(Double.valueOf(request.getParameter("bbprice").trim()));
                    buy.setBtype(request.getParameter("btype").trim());
                    buy.setBhownew(request.getParameter("bhownew").trim());
                    buy.setBgetway(request.getParameter("bgetway").trim());
                    buy.setBtime(request.getParameter("btime").trim());
                    buy.setBstate(Integer.valueOf(request.getParameter("bstate").trim()));
                    buy.setBaddress(request.getParameter("baddress").trim());
                    buy.setBscannum(Integer.valueOf(request.getParameter("bscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = buydata + "/" + Imagename;
                    buy.setBimage(Imageurl.trim());
                    imageResult = ImageDeal.string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        boolean buyResult = buyService.insertBuy(buy);
                        if(buyResult){
                            publishbuy.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            publishbuy.put("code","0");
                            System.out.println("发布失败！");
                        }
                    }else{
                        publishbuy.put("code","0");
                        System.out.println("发布失败！");
                    }
                    break;
                case "republishbuy":
                    buy.setBid(Integer.valueOf(request.getParameter("bid").trim()));
                    buy.setBname(request.getParameter("bname").trim());
                    buy.setBdetail(request.getParameter("bdetail").trim());
                    buy.setBsprice(Double.valueOf(request.getParameter("bsprice").trim()));
                    buy.setBbprice(Double.valueOf(request.getParameter("bbprice").trim()));
                    buy.setBtype(request.getParameter("btype").trim());
                    buy.setBhownew(request.getParameter("bhownew").trim());
                    buy.setBgetway(request.getParameter("bgetway").trim());
                    buy.setBaddress(request.getParameter("baddress").trim());
                    boolean buyrs = buyService.refreshBuyById(buy);
                    if(!buyrs){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功
                    }
                    break;
                case "publishcharity":
                    JSONObject publishcharity = new JSONObject();
                    String charityapppath = request.getRealPath("/")+"appdata";
                    File charityappfile = new File(charityapppath);
                    if(!charityappfile.exists()){
                        charityappfile.mkdir();
                    }
                    String charitydata = charityapppath + "/" + "charitydata";
                    File charityfile = new File(charitydata);
                    if(!charityfile.exists()){
                        charityfile.mkdir();
                    }
                    user = userService.queryUserByAccount(request.getParameter("uaccount").trim());
                    charity.setUid(user.getUid());
                    charity.setCname(request.getParameter("cname").trim());
                    charity.setCdetail(request.getParameter("cdetail").trim());
                    charity.setCneed(request.getParameter("cneed").trim());
                    charity.setCnumber(Integer.valueOf(request.getParameter("cnumber").trim()));
                    charity.setCtime(request.getParameter("ctime").trim());
                    charity.setCdeadline(request.getParameter("cdate").trim());
                    charity.setCtype(request.getParameter("cstyle").trim());
                    charity.setCstate(Integer.valueOf(request.getParameter("cstate").trim()));
                    charity.setCaddress(request.getParameter("caddress").trim());
                    charity.setCscannum(Integer.valueOf(request.getParameter("cscannum").trim()));
                    charity.setCjoinnum(Integer.valueOf(request.getParameter("cscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = charitydata + "/" + Imagename;
                    charity.setCimage(Imageurl.trim());
                    imageResult = ImageDeal.string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        boolean charityResult = charityService.insertCharity(charity);
                        if(charityResult){
                            publishcharity.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            publishcharity.put("code","0");
                            System.out.println("发布失败！");
                        }
                    }else{
                        publishcharity.put("code","0");
                        System.out.println("发布失败！");
                    }
                    break;
                case "republishcharity":
                    charity.setCid(Integer.valueOf(request.getParameter("cid").trim()));
                    charity.setCname(request.getParameter("cname").trim());
                    charity.setCdetail(request.getParameter("cdetail").trim());
                    charity.setCneed(request.getParameter("cneed").trim());
                    charity.setCnumber(Integer.valueOf(request.getParameter("cnumber").trim()));
                    charity.setCdeadline(request.getParameter("cdate").trim());
                    charity.setCtype(request.getParameter("cstyle").trim());
                    charity.setCaddress(request.getParameter("caddress").trim());
                    boolean charityrs = charityService.refreshCharity(charity);
                    if(!charityrs){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功
                    }
                    break;
                default:System.out.println("修改发布失败！");break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }
}

