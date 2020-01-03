package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.aoon.CacheFind;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;


@RestController
@RequestMapping("/web/item")
public class WebItemController {
	@Autowired
	private ItemService itemService;
	
	@CacheFind
	@RequestMapping("/findItemDescById")
	public ItemDesc findItemDescById(Long itemId) {
		return itemService.findItemDescById(itemId);
	}
	@CacheFind
	@RequestMapping("/findItemById")
	public Item findItemById(Long itemId) {
		return itemService.findItemById(itemId);
	}
}
