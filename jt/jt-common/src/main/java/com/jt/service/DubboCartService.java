package com.jt.service;

import java.util.List;

import com.jt.pojo.Cart;

public interface DubboCartService {

	List<Cart> findAll(Long userId);

	void addCart(Cart cart);

	void updateCartNum(Cart cart);

}
