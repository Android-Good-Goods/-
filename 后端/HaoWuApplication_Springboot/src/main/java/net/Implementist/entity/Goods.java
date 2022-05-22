package net.Implementist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "Gid", type = IdType.AUTO)
    private Integer Gid;

    @ApiModelProperty(value = "商品名")
    @TableField("Gname")
    private String Gname;

    @ApiModelProperty(value = "商品所属的用户id")
    @TableField("Uid")
    private Integer Uid;

    @ApiModelProperty(value = "商品发布时间")
    @TableField("Gtime")
    private String Gtime;

    @ApiModelProperty(value = "商品所属类型")
    @TableField("Gtype")
    private String Gtype;

    @ApiModelProperty(value = "商品描述")
    @TableField("Gdetail")
    private String Gdetail;

    @ApiModelProperty(value = "商品价格")
    @TableField("Gprice")
    private Double Gprice;

    @ApiModelProperty(value = "商品原价")
    @TableField("Goprice")
    private Double Goprice;

    @ApiModelProperty(value = "商品的图片，存储的是图片的储存地址")
    @TableField("Gimage")
    private String Gimage;

    @ApiModelProperty(value = "商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除")
    @TableField("Gstate")
    private Integer Gstate;

    @ApiModelProperty(value = "商品是否是急售商品，1表示正常，2表示急售")
    @TableField("Gemergent")
    private Integer Gemergent;

    @ApiModelProperty(value = "商品交易是以邮寄的方式还是自提的方式")
    @TableField("Ggetway")
    private String Ggetway;

    @ApiModelProperty(value = "新旧程度")
    @TableField("Ghownew")
    private String Ghownew;

    @ApiModelProperty(value = "浏览人数")
    @TableField("Gscannum")
    private Integer Gscannum;

    @ApiModelProperty(value = "商品发布地址")
    @TableField("Gaddress")
    private String Gaddress;


}
