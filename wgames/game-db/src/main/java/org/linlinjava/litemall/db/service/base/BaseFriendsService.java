package org.linlinjava.litemall.db.service.base;

import org.linlinjava.litemall.db.dao.FriendsMapper;
import org.linlinjava.litemall.db.domain.Friends;
import org.linlinjava.litemall.db.domain.example.FriendsExample;
import org.linlinjava.litemall.db.domain.example.FriendsExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
@Service
public class BaseFriendsService{
    @Autowired
    protected FriendsMapper mapper;

    @Cacheable(cacheNames="Friends", key="#id")
	public Friends findById(int id) {
        return mapper.selectByPrimaryKeyWithLogicalDelete(id,false);
    }
    @Cacheable(cacheNames="Friends", key="#id",condition="#result.deleted == 0")
	public Friends findByIdContainsDelete(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void add(Friends friends) {
        friends.setAddTime(LocalDateTime.now());
        friends.setUpdateTime(LocalDateTime.now());
        mapper.insertSelective(friends);
    }
    
    @CachePut(cacheNames="Friends", key="#friends.id")
	public int updateById(Friends friends) {
        friends.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(friends);
    }
    
   	@CacheEvict(cacheNames="Friends", key="#id")
	public void deleteById(int id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }
    
    
    
    
    
 	public List<Friends> findByPid(String pid) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(false).andPidEqualTo(pid);
		return mapper.selectByExample(example);
	}

 	public List<Friends> findByHy1(String hy1) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(false).andHy1EqualTo(hy1);
		return mapper.selectByExample(example);
	}

 	public Friends findOneByPid(String pid) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(false).andPidEqualTo(pid);
		return mapper.selectOneByExample(example);
	}

 	public Friends findOneByHy1(String hy1) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(false).andHy1EqualTo(hy1);
		return mapper.selectOneByExample(example);
	}

	
	public List<Friends> findAll(int page, int size, String sort, String order) {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }
    
    public List<Friends> findAll() {
        FriendsExample example = new FriendsExample();
        FriendsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }
}
