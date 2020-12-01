package com.colourful.colourful.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付渠道表
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_channel_info")
public class PayChannelInfo extends Model<PayChannelInfo> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付渠道简称
     */
    private String shortName;

    /**
     * 支付渠道名称
     */
    private String payChannelName;

    /**
     * 是否启用(0：启用,1：失效)
     */
    private String delFlag;

    /**
     * 支付图标
     */
    private String logo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 添加用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
