package com.cy.pj.sys.service.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		
		Integer userId=user.getId();
		
		List<Integer> roleIds=new ArrayList<Integer>();
		List<Map<String, Object>> list = sysUserRoleDao.findParamByParams("sys_user_roles", "user_id", "role_id", userId);
		for (Map<String, Object> map : list) {
			roleIds.add((Integer)mapWithEntity.mapToBean(map, Integer.class));
		}
		
		Integer[] array={};
		List<Integer> menuIds=new ArrayList<Integer>();
		List<Map<String, Object>> list2 =sysRoleMenuDao.findParamByParams("sys_role_menus", "role_id", "menu_id", roleIds.toArray(array));
		for (Map<String, Object> map : list2) {
			menuIds.add((Integer)mapWithEntity.mapToBean(map, Integer.class));
		}
		
		List<String> permissions=new ArrayList<String>();
		List<Map<String, Object>> list3=sysMenuDao.findParamByParams("sys_menus", "id", "permission", menuIds.toArray(array));
		for (Map<String, Object> map : list3) {
			permissions.add((String)mapWithEntity.mapToBean(map, Integer.class));
		}
		Set<String> set=new HashSet<>();
		for(String per:permissions){
	    	if(!StringUtils.isEmpty(per)){
	    		set.add(per);
	    	}
	    }

		
		SimpleAuthorizationInfo info=
			    new SimpleAuthorizationInfo();
			    info.setStringPermissions(set);
				return info;


	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken=
		(UsernamePasswordToken)token;
		String username=upToken.getUsername();
		SysUser user=(SysUser) mapWithEntity.mapToBean(sysUserDao.findObjectsByParams("sys_users", "username", username).get(0), SysUser.class);
		if(user==null) {
			throw new UnknownAccountException();
		}
		if(user.getValid()==0) {
			throw new LockedAccountException();
		}
		ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
		
		SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						user,//principal (身份)
						user.getPassword(),//hashedCredentials
						credentialsSalt, //credentialsSalt
						getName());//realName
				//6.返回封装结果
		return info;
	}
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
			//构建凭证匹配对象
			HashedCredentialsMatcher cMatcher=new HashedCredentialsMatcher();
			//设置加密算法
			cMatcher.setHashAlgorithmName("MD5");
			//设置加密次数
			cMatcher.setHashIterations(1);
			super.setCredentialsMatcher(cMatcher);
	}


}
