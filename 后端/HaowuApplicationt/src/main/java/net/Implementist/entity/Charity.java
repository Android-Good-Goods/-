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
@TableName("charity")
@ApiModel(value="Charity对象", description="")
public class Charity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公益id")
    @TableId(value = "Cid", type = IdType.AUTO)
    private Integer cid;

    @ApiModelProperty(value = "公益名称")
    @TableField("Cname")
    private String cname;

    @ApiModelProperty(value = "发布公益的用户id")
    @TableField("Uid")
    private Integer uid;

    @ApiModelProperty(value = "公益宣传图片")
    @TableField("Cimage")
    private String cimage;

    @ApiModelProperty(value = "公益细节")
    @TableField("Cdetail")
    private String cdetail;

    @ApiModelProperty(value = "公益需求")
    @TableField("Cneed")
    private String cneed;

    @ApiModelProperty(value = "所需人数")
    @TableField("Cnumber")
    private Integer cnumber;

    @ApiModelProperty(value = "发起时间")
    @TableField("Ctime")
    private String ctime;

    @ApiModelProperty(value = "截止时间")
    @TableField("Cdeadline")
    private String cdeadline;

    @ApiModelProperty(value = "报名方式")
    @TableField("Ctype")
    private String ctype;

    @ApiModelProperty(value = "发布地址")
    @TableField("Caddress")
    private String caddress;

    @ApiModelProperty(value = "浏览人数")
    @TableField("Cscannum")
    private Integer cscannum;

    @ApiModelProperty(value = "参加人数")
    @TableField("Cjoinnum")
    private Integer cjoinnum;

    @ApiModelProperty(value = "公益状态，1表示在进行中，2表示失效，3表示用户已删除")
    @TableField("Cstate")
    private Integer cstate;


}
