package net.Implementist.controller;


import cn.hutool.core.collection.CollectionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Charity;
import net.Implementist.entity.Joincharity;
import net.Implementist.entity.User;
import net.Implementist.service.CharityService;
import net.Implementist.service.JoincharityService;
import net.Implementist.service.UserService;
import net.Implementist.util.HXUtil;
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
public class CharityController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private CharityService charityService;

    @Autowired
    private JoincharityService joincharityService;


    @RequestMapping(path = "/Charity_Servlet", produces = "text/html;charset=utf-8")
    public String indexCharity() throws JsonProcessingException {
        //定义变量
        String cid;                                 //公益id
        String uid;
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject caudata = new JSONObject();       //用户和公益数据
        JSONArray caulist = new JSONArray();         //用户和公益数据列表
        JSONObject charitydata = new JSONObject();
        User account = new User();
        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "getmypubcharity":
                    account = userService.queryUserByAccount(request.getParameter("account").trim());
                    List<Charity> charities = charityService.queryMypublish(String.valueOf(account.getUid()));
                    if(null != charities && CollectionUtil.isNotEmpty(charities)) {
                        //我的发布不为空
                        params.put("code", "1");
                        for (Charity charity : charities) {
                            caudata.put("charitydata", charity);
                            caudata.put("userdata", userService.getById(charity.getUid()));
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    } else {
                        //没有发布公益活动
                        params.put("code", "0");
                    }
                    break;
                case "getmypartcharity":
                    account = userService.queryUserByAccount(request.getParameter("account").trim());
                    LambdaQueryWrapper<Joincharity> joinCharityLqw = new LambdaQueryWrapper();
                    joinCharityLqw.eq(Joincharity::getUid, account.getUid());
                    List<Joincharity> joincharitys = joincharityService.list(joinCharityLqw);
                    if(CollectionUtil.isNotEmpty(joincharitys)){
                        params.put("code", "1");           //我参加的公益不为空
                        for (Joincharity joincharity : joincharitys) {
                            Charity charity = charityService.getById(joincharity.getCid());
                            caudata.put("charitydata", charity);
                            caudata.put("userdata", userService.getById(joincharity.getUid()));
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    } else {
                        //没有发布公益活动
                        params.put("code", "0");
                    }
                    break;
                case "getcharity":
                    LambdaQueryWrapper<Charity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(Charity::getCstate, request.getParameter("cstate").trim());
                    List<Charity> publishList = charityService.list(lambdaQueryWrapper);
                    if(CollectionUtil.isNotEmpty(publishList)){
                        params.put("code", "1");           //公益列表不为空
                        for (Charity charity : publishList) {
                            caudata.put("charitydata", charity);
                            caudata.put("userdata", userService.getById(charity.getUid()));
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    } else {
                        //没有发布公益活动
                        params.put("code", "0");
                    }
                    break;
                case "joincharity":
                    User account1 = userService.queryUserByAccount(request.getParameter("account").trim());
                    if(!joincharityService.verifyJoin(String.valueOf(account1.getUid()), request.getParameter("cid").trim())){
                        Joincharity joincharity = new Joincharity();
                        joincharity.setCid(Integer.valueOf(request.getParameter("cid").trim()))
                                .setUid(account1.getUid()).setCuid(Integer.valueOf(request.getParameter("cuid")))
                                .setJtime(request.getParameter("jtime"));
                        boolean rs = joincharityService.insertJoin(joincharity);
                        Charity charity = charityService.getById(request.getParameter("cid").trim());
                        Charity target = new Charity();
                        target.setCid(charity.getCid());
                        target.setCjoinnum(charity.getCjoinnum()+1);
                        boolean insertrs = charityService.refreshCharity(target);
                        if(rs && insertrs){
                            params.put("code", "1");           //数据插入成功
                            params.put("joinnum", charity.getCjoinnum() + 1);           //返回参加人数
                        }else {
                            //数据插入失败
                            params.put("code", "0");
                        }
                    }else {
                        params.put("code", "2");       //用户已加入
                    }
                    break;
                case "deletecharity":
                    Charity target = new Charity();
                    target.setCid(Integer.valueOf(request.getParameter("cid").trim()));
                    target.setCstate(3);
                    boolean result = charityService.refreshCharity(target);
                    if(result){
                        params.put("code", "1");           //已删除
                    } else {
                        params.put("code", "0");       //删除失败
                    }
                    break;
                case "send":
                    String message = "#商品售出#您的商品111已被拍下，请及时发货！#";
                    boolean a = HXUtil.sendToUser("admin",request.getParameter("hxid").trim().trim(),message);
                    if(a){
                        params.put("code", "1");
                    }else {
                        params.put("code", "0");
                    }
                    break;
                case "setscannum":
                    Charity charity = charityService.getById(request.getParameter("cid").trim());
                    Charity target1 = new Charity();
                    target1.setCid(charity.getCid());
                    target1.setCscannum(charity.getCscannum() + 1);
                    boolean scanrs = charityService.refreshCharity(target1);
                    if (!scanrs) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(charity.getCscannum()) + 1);
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

