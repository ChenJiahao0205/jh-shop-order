package pers.jhshop.order.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单操作记录表修改Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LogsUpdateReq", description = "订单操作记录表修改Req")
public class LogsUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "操作日志ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "变更前的订单状态")
    private Boolean statusBefore;

    @ApiModelProperty(value = "变更后的订单状态")
    private Boolean statusAfter;

    @ApiModelProperty(value = "操作描述（如：支付成功，订单发货等）")
    private String operation;

    @ApiModelProperty(value = "操作人（如：用户、管理员等）")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;
}
