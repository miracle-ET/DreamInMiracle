package com.jt.controller;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	

	
	
	
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}
	
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		return SysResult.success(itemService.findItemDescById(itemId));
	}
	
	
	
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
			itemService.saveItem(item,itemDesc);
			return SysResult.success();
	}
	

	  
	  
	  

	
	
	
	
	
	
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
			itemService.updateItem(item, itemDesc);
			return SysResult.success();
	}
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids) {
			itemService.deleteItem(ids);
			return SysResult.success();
	}
	@RequestMapping("/instock")
	public SysResult instockItem(Long[] ids) {
			itemService.updateStarus(ids,2);
			return SysResult.success();
	}
	@RequestMapping("/reshelf")
	public SysResult reshelfItem(Long[] ids) {
		itemService.updateStarus(ids,1);
			return SysResult.success();
	}
}
