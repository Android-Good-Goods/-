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
@TableName("conments")
@ApiModel(value="Conments对象", description="")
public class Conments implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "Conid", type = IdType.AUTO)
    private Integer Conid;

    @ApiModelProperty(value = "商品id")
    @TableField("Gid")
    private Integer Gid;

    @ApiModelProperty(value = "发布评论的用户id")
    @TableField("Uid")
    private Integer Uid;

    @ApiModelProperty(value = "商品的用户id")
    @TableField("Guid")
    private Integer Guid;

    @ApiModelProperty(value = "发布时间")
    @TableField("Contime")
    private String Contime;

    @ApiModelProperty(value = "内容")
    @TableField("Concontent")
    private String Concontent;

    @ApiModelProperty(value = "评论状态，1表示存在，2表示已删除")
    @TableField("Constate")
    private Integer Constate;


}
