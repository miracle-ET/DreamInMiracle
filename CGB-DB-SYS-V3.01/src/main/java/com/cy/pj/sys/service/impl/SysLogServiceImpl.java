package com.cy.pj.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.common.config.Config;
import com.cy.pj.sys.common.exception.ServiceException;
import com.cy.pj.sys.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	@Autowired
	private Config config;

	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		// 1.验证参数合法性
		// 1.1验证pageCurrent的合法性，
		// 不合法抛出IllegalArgumentException异常
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysLogDao.getRowCount("sys_Logs", "username", username);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		// 3.基于条件查询当前页记录(pageSize定义为2)
		// 3.1)定义pageSize
		int pageSize = config.getPageSize();
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;
		// 3.3)执行当前数据的查询操作
		List<SysLog> records =new ArrayList<SysLog>();
		List<Map<String, Object>> list=sysLogDao.findPageObjects("sys_Logs", "username", username, startIndex, pageSize);
		for (Map<String, Object> map : list) {
			records.add((SysLog)mapWithEntity.mapToBean(map, SysLog.class));
		}
		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject<SysLog> pageObject = new PageObject<>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		// 5.返回封装结果。
		return pageObject;

	}

	@Override
	public int doDeleteObjects(Integer... ids) {
		if(ids==null||ids.length<=0) {
			return 0;
		}
		else {
			return sysLogDao.deleteObjectsByParams("sys_Logs", "id", ids);
		}
	}
	@Override
	public void saveObject(SysLog entity) {
	  sysLogDao.insertObject("sys_logs",mapWithEntity.beanToMap(entity));
}


}
