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
@TableName("collect")
@ApiModel(value="Collect对象", description="")
public class Collect implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "收藏id")
    @TableId(value = "Colid", type = IdType.AUTO)
    private Integer colid;

    @ApiModelProperty(value = "商品id")
    @TableField("gid")
    private Integer Gid;

    @ApiModelProperty(value = "收藏人的id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "商品所属用户的id")
    @TableField("Guid")
    private Integer guid;

    @ApiModelProperty(value = "收藏的时间")
    @TableField("Coltime")
    private String coltime;


}
