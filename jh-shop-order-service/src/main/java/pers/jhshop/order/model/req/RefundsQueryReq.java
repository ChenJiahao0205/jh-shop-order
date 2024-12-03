package pers.jhshop.order.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.order.model.entity.Refunds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单退款表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RefundsQueryReq", description = "订单退款表查询Req")
public class RefundsQueryReq extends Page<Refunds> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "退款记录ID，主键")
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    private Integer orderId;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款状态（0=待处理，1=已退款）")
    private Boolean refundStatus;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
