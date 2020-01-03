package com.jt.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.Util.HttpClientService;
import com.jt.aoon.CacheFind;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private HttpClientService httpClientService;
	@CacheFind
	@Override
	public Item findItemById(Long itemId) {
		return httpClientService.doGet("http://manage.jt.com/web/item/findItemById?itemId="+itemId,Item.class);
	}
	@Override
	@CacheFind
	public ItemDesc findItemDescById(Long itemId) {
		return httpClientService.doGet("http://manage.jt.com/web/item/findItemDescById?itemId="+itemId, ItemDesc.class);
	}

}
