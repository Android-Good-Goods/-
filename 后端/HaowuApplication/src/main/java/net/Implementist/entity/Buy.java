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
@TableName("buy")
@ApiModel(value="Buy对象", description="")
public class Buy implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "求购商品id")
    @TableId(value = "Bid", type = IdType.AUTO)
    private Integer bid;

    @ApiModelProperty(value = "商品名")
    @TableField("Bname")
    private String bname;

    @ApiModelProperty(value = "用户id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "商品细节")
    @TableField("Bdetail")
    private String bdetail;

    @ApiModelProperty(value = "最低价")
    @TableField("Bsprice")
    private Double bsprice;

    @ApiModelProperty(value = "最高价")
    @TableField("Bbprice")
    private Double bbprice;

    @ApiModelProperty(value = "商品类型")
    @TableField("Btype")
    private String btype;

    @ApiModelProperty(value = "新旧程度")
    @TableField("Bhownew")
    private String bhownew;

    @ApiModelProperty(value = "获取方式")
    @TableField("Bgetway")
    private String bgetway;

    @ApiModelProperty(value = "商品发布地址")
    @TableField("Baddress")
    private String baddress;

    @ApiModelProperty(value = "发布时间")
    @TableField("Btime")
    private String btime;

    @ApiModelProperty(value = "求购图片")
    @TableField("Bimage")
    private String bimage;

    @ApiModelProperty(value = "求购商品的状态，1求购状态，2求购到，3已删除")
    @TableField("bstate")
    private Integer bstate;

    @ApiModelProperty(value = "浏览人数")
    @TableField("Bscannum")
    private Integer bscannum;


}
