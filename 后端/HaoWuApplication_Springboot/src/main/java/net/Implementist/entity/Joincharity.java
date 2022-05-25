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
@TableName("joincharity")
@ApiModel(value="Joincharity对象", description="")
public class Joincharity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "参加公益的id")
    @TableId(value = "Jid", type = IdType.AUTO)
    private Integer Jid;

    @ApiModelProperty(value = "相应的公益id")
    @TableField("Cid")
    private Integer Cid;

    @ApiModelProperty(value = "参加用户的id")
    @TableField("Uid")
    private Integer Uid;

    @ApiModelProperty(value = "发起公益人的id")
    @TableField("Cuid")
    private Integer Cuid;

    @ApiModelProperty(value = "参加公益的时间")
    @TableField("Jtime")
    private String Jtime;


}
