package net.Implementist.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Collect;
import net.Implementist.entity.User;
import net.Implementist.service.CollectService;
import net.Implementist.service.GoodsService;
import net.Implementist.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
public class CollectController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(path = "/Mycollect_Servlet", produces = "text/html;charset=utf-8")
    public String myCollect() throws JsonProcessingException {
        //定义变量
        JSONObject params = new JSONObject();        //返回数据
        JSONObject gacdata = new JSONObject();       //商品和收藏数据
        JSONArray gaclist = new JSONArray();         //商品和收藏数据列表

        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "getcollect":
                    User user = userService.queryUserByAccount(request.getParameter("account").trim());
                    LambdaQueryWrapper<Collect> collectWrapper = new LambdaQueryWrapper<>();
                    collectWrapper.eq(Collect::getGuid, user.getUid());
                    List<Collect> collects = collectService.list(collectWrapper);
                    if(!CollectionUtil.isEmpty(collects)){
                        params.put("code", "1");           //收藏不为空
                        for (Collect collect : collects) {
                            gacdata.put("collectdata", collect);
                            gacdata.put("goodsdata", goodsService.getById(collect.getGid()));
                            gacdata.put("userdata", userService.getById(collect.getGuid()));
                            gaclist.add(gacdata);
                        }
                        params.put("data", gaclist);
                    } else {
                        params.put("code", "0");       //收藏为空
                    }
                    break;
                default:System.out.println("收藏信息返回失败！");break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }
}

