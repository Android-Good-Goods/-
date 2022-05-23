package net.Implementist.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.Implementist.entity.Buy;
import net.Implementist.entity.Goods;
import net.Implementist.entity.User;
import net.Implementist.service.BuyService;
import net.Implementist.service.GoodsService;
import net.Implementist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
                    goods = goodsService.queryType(request.getParameter("name").trim(), request.getParameter("state").trim());
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

}

