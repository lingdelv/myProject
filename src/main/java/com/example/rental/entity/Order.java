package com.example.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2024-06-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("busi_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单编号")
    private String orderNum;

    @ApiModelProperty("关联车辆id")
    private Integer autoId;

    @ApiModelProperty("用户id")
    private Integer customerId;

    @ApiModelProperty("出租时间")
    private LocalDateTime rentalTime;

    @ApiModelProperty("出租类型")
    private Integer typeId;

    @ApiModelProperty("出租金额")
    private Integer rent;

    @ApiModelProperty("押金")
    private Integer deposit;

    @ApiModelProperty("起租里程")
    private Integer mileage;

    @ApiModelProperty("归还时间")
    private LocalDateTime returnTime;

    @ApiModelProperty("归还里程")
    private Integer returnMileage;

    @ApiModelProperty("应付租金")
    private Integer rentPayable;

    @ApiModelProperty("实付租金")
    private Integer rentActual;

    @ApiModelProperty("押金返还状态 0-未返还 1-已返还")
    private Boolean depositReturn;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
