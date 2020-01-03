package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		return itemCatService.findAll(itemCatId).getName();
	}
	@RequestMapping("/list")
	public List<EasyUITree> findItemCat(@RequestParam(name="id",defaultValue = "0") Long parentId){
		return itemCatService.findItemCat(parentId);
	}
}
