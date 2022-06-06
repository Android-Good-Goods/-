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
@TableName("account")
@ApiModel(value="Account对象", description="")
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "账单id")
    @TableId(value = "Aid", type = IdType.AUTO)
    private Integer aid;

    @ApiModelProperty(value = "账单号")
    @TableField("Anumber")
    private String anumber;

    @ApiModelProperty(value = "商品id")
    @TableField("Gid")
    private Integer gid;

    @ApiModelProperty(value = "买家id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "卖家id")
    @TableField("Guid")
    private Integer guid;

    @ApiModelProperty(value = "交易金额")
    @TableField("Abill")
    private Double abill;

    @ApiModelProperty(value = "交易时间")
    @TableField("Atime")
    private String atime;

    @ApiModelProperty(value = "订单状态，1表示等待卖家发货，2表示等待买家收货，3表示交易成功，4表示退款中，5表示交易失败（取消订单），6表示订单删除")
    @TableField("Astate")
    private Integer astate;


}
