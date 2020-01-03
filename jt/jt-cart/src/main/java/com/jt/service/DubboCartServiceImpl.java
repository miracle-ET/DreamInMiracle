package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;

@Service
public class DubboCartServiceImpl implements DubboCartService{
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findAll(Long userId) {
		// TODO Auto-generated method stub
		QueryWrapper<Cart> queryWrapper=new QueryWrapper<Cart>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
	}

	@Override
	public void addCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper=new QueryWrapper<Cart>();
		queryWrapper.eq("user_id", cart.getUserId());
		queryWrapper.eq("item_id", cart.getItemId());
		Cart cartDB=cartMapper.selectOne(queryWrapper);
		if(cartDB==null) {
			cart.setCreated(new Date()).setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}
		else {
			cartDB.setNum(cartDB.getNum()+cart.getNum()).setUpdated(new Date());
			cartMapper.updateById(cartDB);
		}		
	}

	@Override
	public void updateCartNum(Cart cart) {
		Cart temp=new Cart();
		temp.setUpdated(new Date());
		temp.setNum(cart.getNum());
		
		QueryWrapper<Cart> queryWrapper=new QueryWrapper<Cart>();
		queryWrapper.eq("user_id", cart.getUserId());
		queryWrapper.eq("item_id", cart.getItemId());
		
		cartMapper.update(temp, queryWrapper);

	}
}
