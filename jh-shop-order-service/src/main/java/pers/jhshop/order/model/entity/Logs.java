package pers.jhshop.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单操作记录表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_logs")
@ApiModel(value = "Logs对象", description = "订单操作记录表")
public class Logs extends Model<Logs> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作日志ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    @TableField("ORDER_ID")
    private Integer orderId;

    @ApiModelProperty(value = "变更前的订单状态")
    @TableField("STATUS_BEFORE")
    private Boolean statusBefore;

    @ApiModelProperty(value = "变更后的订单状态")
    @TableField("STATUS_AFTER")
    private Boolean statusAfter;

    @ApiModelProperty(value = "操作描述（如：支付成功，订单发货等）")
    @TableField("OPERATION")
    private String operation;

    @ApiModelProperty(value = "操作人（如：用户、管理员等）")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    @TableField("VALID_FLAG")
    private Boolean validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
