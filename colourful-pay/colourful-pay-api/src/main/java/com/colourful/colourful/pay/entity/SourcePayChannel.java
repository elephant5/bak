package com.colourful.colourful.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 终端渠道配置支付方式关系表
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("source_pay_channel")
public class SourcePayChannel extends Model<SourcePayChannel> {

    private static final long serialVersionUID=1L;

    /**
     * 渠道
     */
    @TableId(value = "source", type = IdType.ASSIGN_ID)
    private String source;

    /**
     * 支付方式id
     */
    private Integer payChannelId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 更新人
	 */
	private String updateUser;


    @Override
    protected Serializable pkVal() {
        return this.source;
    }

}
