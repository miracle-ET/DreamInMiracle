package com.jt.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemService {
	public Item findItemById(Long itemId);
	public ItemDesc findItemDescById(Long itemId);
}
