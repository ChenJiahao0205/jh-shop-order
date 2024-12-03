package pers.jhshop.order.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pers.jhshop.common.entity.BaseVo;

/**
 * <p>
 * 订单支付表VO
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PaymentsVO", description = "订单支付表列表展示VO")
public class PaymentsVO extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付记录ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "支付方式（如支付宝，微信支付等）")
    private String paymentMethod;

    @ApiModelProperty(value = "支付状态（0=未支付，1=已支付）")
    private Boolean paymentStatus;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "描述")
    private String description;

}
