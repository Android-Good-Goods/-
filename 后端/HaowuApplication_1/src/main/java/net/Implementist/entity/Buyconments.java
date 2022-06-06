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
@TableName("buyconments")
@ApiModel(value="Buyconments对象", description="")
public class Buyconments implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "求购商品的评论id")
    @TableId(value = "Bconid", type = IdType.AUTO)
    private Integer bconid;

    @ApiModelProperty(value = "求购商品id")
    @TableField("Bid")
    private Integer bid;

    @ApiModelProperty(value = "评论人id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "求购发起人id")
    @TableField("Buid")
    private Integer buid;

    @ApiModelProperty(value = "评论时间")
    @TableField("Bcontime")
    private String bcontime;

    @ApiModelProperty(value = "评论内容")
    @TableField("Bconcontent")
    private String bconcontent;

    @ApiModelProperty(value = "评论状态，1表示正常，2表示已删除")
    @TableField("Bconstate")
    private Integer bconstate;


}
