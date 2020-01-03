package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;


@TableName("tb_item_cat")
@Data
@Accessors(chain = true)
public class ItemCat extends BasePojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long parentId;
	private String name;
	private String status;
	private Integer sortOrder;
	private Boolean isParent;
}
