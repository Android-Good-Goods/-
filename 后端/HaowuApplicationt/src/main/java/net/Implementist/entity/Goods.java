package net.Implementist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "Gid", type = IdType.AUTO)
    private Integer gid;

    @ApiModelProperty(value = "商品名")
    @TableField("Gname")
    private String gname;

    @ApiModelProperty(value = "商品所属的用户id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "商品发布时间")
    @TableField("Gtime")
    private String gtime;

    @ApiModelProperty(value = "商品所属类型")
    @TableField("Gtype")
    private String gtype;

    @ApiModelProperty(value = "商品描述")
    @TableField("Gdetail")
    private String gdetail;

    @ApiModelProperty(value = "商品价格")
    @TableField("Gprice")
    private Double gprice;

    @ApiModelProperty(value = "商品原价")
    @TableField("Goprice")
    private Double goprice;

    @ApiModelProperty(value = "商品的图片，存储的是图片的储存地址")
    @TableField("Gimage")
    private String gimage;

    @ApiModelProperty(value = "商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除")
    @TableField("Gstate")
    private Integer gstate;

    @ApiModelProperty(value = "商品是否是急售商品，1表示正常，2表示急售")
    @TableField("Gemergent")
    private Integer gemergent;

    @ApiModelProperty(value = "商品交易是以邮寄的方式还是自提的方式")
    @TableField("Ggetway")
    private String ggetway;

    @ApiModelProperty(value = "新旧程度")
    @TableField("Ghownew")
    private String ghownew;

    @ApiModelProperty(value = "浏览人数")
    @TableField("Gscannum")
    private Integer gscannum;

    @ApiModelProperty(value = "商品发布地址")
    @TableField("Gaddress")
    private String gaddress;


}
