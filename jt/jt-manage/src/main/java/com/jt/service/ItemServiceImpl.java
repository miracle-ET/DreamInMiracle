package com.jt.service;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.aoon.CacheFind;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	public EasyUITable findItemByPage(Integer page,Integer rows) {
		int total=itemMapper.selectCount(null);
		List<Item> rows1=itemMapper.findItemByPage((page-1)*rows, rows);
		return new EasyUITable(total,rows1);
	}
	/*public EasyUITable findItemByPage(Integer page,Integer rows) {
		Page<Item> itemPage=new Page<Item>(page,rows);
		QueryWrapper<Item> queryWrapper=new QueryWrapper<Item>();
		queryWrapper.orderByDesc("updated");
		IPage<Item> selectPage = itemMapper.selectPage(itemPage, queryWrapper);
		return new EasyUITable((int)selectPage.getTotal(),selectPage.getRecords());
	}*/

	@Override
	@Transactional
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date(System.currentTimeMillis())).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId()).setCreated(new Date(System.currentTimeMillis())).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setUpdated(new Date(System.currentTimeMillis()));
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(new Date(System.currentTimeMillis()));
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	@Transactional
	public void deleteItem(Long[] ids) {
		if(ids.length<=10) {
			for (Long long1 : ids) {
				itemMapper.deleteById(long1);
				itemDescMapper.deleteById(long1);
			}
		}
		else {
			List<Long> list=new ArrayList<Long>();
			for(int i=0;i<ids.length;i++) {
				list.add(ids[i]);
			}
			itemMapper.deleteBatchIds(list);
			itemDescMapper.deleteBatchIds(list);
		}	
	}

	@Override
	public void updateStarus(Long[] ids, int i) {
		for (Long id : ids) {
			Item item=new Item(id,null,null,null,null,null,null,null, i);
			item.setCreated(new Date(System.currentTimeMillis()));
			itemMapper.updateById(item);
		}
	}

	@Override
	@CacheFind
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectById(itemId);
	}

	@Override
	@CacheFind
	public Item findItemById(Long itemId) {
		return itemMapper.selectById(itemId);
	}
}
