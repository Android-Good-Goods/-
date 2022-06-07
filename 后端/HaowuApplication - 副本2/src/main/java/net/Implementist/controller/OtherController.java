package net.Implementist.controller;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.Implementist.entity.Goods;
import net.Implementist.service.GoodsService;
import net.Implementist.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class OtherController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/F1Fragment_Servlet", produces = "text/html;charset=utf-8")
    public String F1Fragment_Servlet() throws JsonProcessingException {
        //定义变量
        JSONArray goodslist = new JSONArray();
        JSONObject params = new JSONObject();
        JSONObject goodsdata = new JSONObject();
        JSONObject userdata = new JSONObject();
        JSONObject uagdata = new JSONObject();
        JSONArray uaglist = new JSONArray();
        LambdaQueryWrapper<Goods> goodsWrapper = new LambdaQueryWrapper<>();
        List<Goods> goodsList = new ArrayList<>();
        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "goodsinfo":
                    goodsWrapper.eq(Goods::getGemergent, 1).eq(Goods::getGstate, request.getParameter("state").trim());
                    goodsList = goodsService.list(goodsWrapper);
                    if(!CollectionUtil.isEmpty(goodsList)){
                        for (Goods goods : goodsList) {
                            uagdata.put("userdata", userService.getById(goods.getUid()));
                            uagdata.put("goodsdata", goods);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("猜你喜欢请求成功！");
                    }else{
                        params.put("code", "0");             //请求数据为空
                    }
                    break;
                case "localgoods":
                    goodsWrapper.like(Goods::getGaddress, request.getParameter("location").trim()).eq(Goods::getGstate, 1);
                    goodsList = goodsService.list(goodsWrapper);
                    if(!CollectionUtil.isEmpty(goodsList)){
                        for (Goods goods : goodsList) {
                            uagdata.put("userdata", userService.getById(goods.getUid()));
                            uagdata.put("goodsdata", goods);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("附近商品请求成功！");
                    }else {
                        params.put("code", "1");               //请求数据为空
                    }
                    break;
                case "goodsji":
                    goodsWrapper.eq(Goods::getGemergent, 2)
                            .eq(Goods::getGstate, request.getParameter("state").trim())
                            .orderByDesc(Goods::getGtime).last("limit 3");
                    goodsList = goodsService.list(goodsWrapper);
                    if(!CollectionUtil.isEmpty(goodsList)){
                        for (Goods goods : goodsList) {
                            uagdata.put("userdata", userService.getById(goods.getUid()));
                            uagdata.put("goodsdata", goods);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("急售请求成功！");
                    }else {
                        params.put("code", "0");            //请求数据为空
                    }
                    break;
                default:System.out.println("商品信息返回失败！");break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }

    @RequestMapping(path = "/Image_Servlet")
    public void Image_Servlet() throws IOException {
        BufferedImage image_buffer = null;
        ServletOutputStream output = response.getOutputStream();
        // 设置响应内容类型
        response.setContentType("image/jpeg");
        request.setCharacterEncoding("utf-8");
        try {
            //获得请求
            String imagepath = URLDecoder.decode(request.getQueryString().trim(), "utf-8");
            if ((imagepath != null) || (new File(imagepath)).exists()) {
                System.out.println("图片路径为：" + imagepath);
                try {
                    image_buffer = ImageIO.read(new File(imagepath));
                } catch (IOException e) {
                }
            } else {
                image_buffer = null;
            }
            ImageIO.write(image_buffer, "jpg", output);
            output.flush();
            output.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

