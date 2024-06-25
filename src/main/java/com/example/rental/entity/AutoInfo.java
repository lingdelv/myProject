package com.example.rental.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@TableName("auto_info")
@ApiModel(value = "AutoInfo对象", description = "")
public class AutoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("车辆信息id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("车牌号码")
    private String autoNum;

    @ApiModelProperty("厂商id")
    private Integer makerId;

    @ApiModelProperty("品牌id")
    private Integer brandId;

    @ApiModelProperty("车辆类型 0燃油车 1电动车 2混东车")
    private Integer infoType;

    @ApiModelProperty("车辆颜色")
    private String color;

    @ApiModelProperty("汽车排量")
    private Double displacement;

    @ApiModelProperty("排量计量单位")
    private String unit;

    @ApiModelProperty("行驶里程")
    private Integer mileage;

    @ApiModelProperty("日租金额")
    private Integer rent;

    @ApiModelProperty("上牌日期")
    private LocalDate registrationDate;

    @ApiModelProperty("车辆照片")
    private String pic;

    @ApiModelProperty("押金")
    private Integer deposit;

    @ApiModelProperty("状态 0未租 1已租 2维保 3自用")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("最后一次更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("应保次数")
    private Integer expectedNum;

    @ApiModelProperty("实保次数")
    private Integer actualNum;

    @ApiModelProperty("是否删除")
    private Integer deleted;

    @TableField(exist = false)
    private String brandName;

    @TableField(exist = false)
    private String makerName;

    @TableField(exist = false)
    private Double lowRent;
    @TableField(exist = false)
    private Double highRent;
    @TableField(exist = false)
    private LocalDate registrationDateStart;
    @TableField(exist = false)
    private LocalDate registrationDateEnd;
}
