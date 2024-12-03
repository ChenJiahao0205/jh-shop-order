package pers.jhshop.order.model.entity;

import java.math.BigDecimal;
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
 * 订单商品表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_items")
@ApiModel(value = "Items对象", description = "订单商品表")
public class Items extends Model<Items> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单商品ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单ID，外键关联订单表")
    @TableField("ORDER_ID")
    private Integer orderId;

    @ApiModelProperty(value = "商品ID，外键关联商品表")
    @TableField("PRODUCT_ID")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    @TableField("QUANTITY")
    private Integer quantity;

    @ApiModelProperty(value = "商品单价")
    @TableField("PRICE")
    private BigDecimal price;

    @ApiModelProperty(value = "商品总价（数量 * 单价）")
    @TableField("TOTAL_PRICE")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "商品添加到订单的时间")
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
