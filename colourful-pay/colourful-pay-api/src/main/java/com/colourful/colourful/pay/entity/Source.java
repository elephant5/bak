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
 * 终端渠道配置表
 * </p>
 *
 * @author json
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("source")
public class Source extends Model<Source> {

    private static final long serialVersionUID=1L;

    /**
     * 终端渠道
     */
    @TableId(value = "source", type = IdType.ASSIGN_ID)
    private String source;

    /**
     * 支付成功前端回调地址
     */
    private String frontendUrl;

    /**
     * 支付成功后端回调地址
     */
    private String backendUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识（0-正常，1-删除）
     */
    private String delFlag;

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
