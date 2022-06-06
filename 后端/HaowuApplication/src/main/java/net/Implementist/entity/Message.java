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
@TableName("message")
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "消息id")
    @TableId(value = "Mid", type = IdType.AUTO)
    private Integer mid;

    @ApiModelProperty(value = "接收人id")
    @TableField("Receiveid")
    private Integer receiveid;

    @ApiModelProperty(value = "消息标题")
    @TableField("Mtitle")
    private String mtitle;

    @ApiModelProperty(value = "消息内容")
    @TableField("Mcontent")
    private String mcontent;

    @ApiModelProperty(value = "消息建立时间")
    @TableField("Mtime")
    private String mtime;

    @ApiModelProperty(value = "消息状态，1表示未读，2表示已读，3表示该消息已删除")
    @TableField("Mstate")
    private Integer mstate;


}
