package com.jt.service;

import java.util.List;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

public interface ItemCatService {
	public ItemCat findAll(Long id);
	public List<EasyUITree> findItemCat(Long parentId);
}
