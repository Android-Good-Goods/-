package net.Implementist.service;

import net.Implementist.entity.Goods;
import net.Implementist.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;

    //测试根据商品所属类型和商品状态查询商品
    @Test
    void queryType() {
        Assert.assertNotNull(goodsService.queryType("手机数码", "1"));
    }

    //测试根据商品名和商品状态查询商品
    @Test
    void queryGoodsMo() {
        Assert.assertNotNull(goodsService.queryGoodsMo("手机充电器", "1"));
    }

    //测试查询免费在售商品
    @Test
    void queryFree() {
        Assert.assertNotNull(goodsService.queryFree("1"));
    }

    //测试查询急售商品
    @Test
    void queryEmergent() {
        Assert.assertNotNull(goodsService.queryEmergent("2","1"));
    }

    //测试查询我的免费商品
    @Test
    void queryMyfree() {
        Assert.assertNotNull(goodsService.queryMyfree("2","1"));
    }

    //测试修改商品
    @Test
    void refreshGoods() {
        Goods goods = new Goods();
        goods.setGid(1);
        goods.setGname("手机");
        Assert.assertTrue(goodsService.refreshGoods(goods));
    }

    //测试新增商品
    @Test
    void insertGoods() {
        Goods goods = new Goods();
        goods.setGname("手机");
        Assert.assertTrue(goodsService.insertGoods(goods));
    }
}
