package com.colourful.colourful.codegen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.colourful.colourful.codegen.entity.ColumnEntity;
import com.colourful.colourful.codegen.entity.GenConfig;
import com.colourful.colourful.codegen.mapper.GenTableColumnMapper;
import com.colourful.colourful.codegen.service.GenTableColumnService;
import com.colourful.colourful.codegen.util.GenUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 表字段信息管理
 *
 * @author colourful
 * @date 2020/5/18
 */
@Service
@AllArgsConstructor
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, ColumnEntity>
		implements GenTableColumnService {

	@Override
	public IPage<ColumnEntity> listTable(Page page, GenConfig genConfig) {
		IPage<ColumnEntity> columnPage = baseMapper.selectTableColumn(page, genConfig.getTableName(),
				genConfig.getDsName());

		// 处理 数据库类型和 Java 类型关系
		Configuration config = GenUtils.getConfig();
		columnPage.getRecords().forEach(column -> {
			String attrType = config.getString(column.getDataType(), "unknowType");
			column.setLowerAttrName(StringUtils.uncapitalize(GenUtils.columnToJava(column.getColumnName())));
			column.setJavaType(attrType);
		});
		return columnPage;
	}

}
