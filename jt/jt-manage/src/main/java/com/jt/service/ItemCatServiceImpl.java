package com.jt.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.aoon.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired(required = false)
	private Jedis jedis;
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public ItemCat findAll(Long id) {
		return itemCatMapper.selectById(id);
	}
	@Override
	@CacheFind
	public List<EasyUITree> findItemCat(Long parentId) {
		List<EasyUITree> list = new ArrayList<EasyUITree>();

		List<ItemCat> findItemCatByParentId = findItemCatByParentId(parentId);
		for (ItemCat itemCat : findItemCatByParentId) {
			list.add(new EasyUITree(itemCat.getId(), itemCat.getName(), itemCat.getIsParent() ? "closed" : "open"));
		}
		if(list.size()==0) {
			return null;
		}
		return list;
	}

	private List<ItemCat> findItemCatByParentId(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}
}