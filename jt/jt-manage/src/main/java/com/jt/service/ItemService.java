package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {
	public EasyUITable findItemByPage(Integer page,Integer rows);



	public void deleteItem(Long[] ids);



	void updateStarus(Long[] ids, int i);

	public void saveItem(Item item, ItemDesc itemDesc);


	public void updateItem(Item item, ItemDesc itemDesc);



	public ItemDesc findItemDescById(Long itemId);

	public Item findItemById(Long itemId);
}
