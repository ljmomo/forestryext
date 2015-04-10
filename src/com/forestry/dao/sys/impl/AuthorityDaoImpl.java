package com.forestry.dao.sys.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.AuthorityDao;
import com.forestry.model.sys.Authority;

import core.dao.BaseDao;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Repository
public class AuthorityDaoImpl extends BaseDao<Authority> implements AuthorityDao {

	public AuthorityDaoImpl() {
		super(Authority.class);
	}

	@Override
	public List<Authority> queryByParentIdAndRole(Short role) {
		// MySQL
		SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where a.id = ra.authority_id and a.parent_id is null and ra.role = ?"); 
		// Oracle
		// SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where to_char(a.id) = ra.authority_id and a.parent_id is null and ra.role = ?");
		// SQL Server
		// SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where convert(char(8),a.id) = ra.authority_id and a.parent_id is null and ra.role = ?");
		query.setParameter(0, role);
		query.addEntity(Authority.class);
		return query.list();
	}

	@Override
	public List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role) {
		// MySQL
		SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where a.id = ra.authority_id and a.parent_id = ? and ra.role = ?"); 
		// Oracle
		// SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where to_char(a.id) = ra.authority_id and a.parent_id = ? and ra.role = ?");
		// SQL Server
		// SQLQuery query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where convert(char(8),a.id) = ra.authority_id and a.parent_id = ? and ra.role = ?");
		query.setParameter(0, parentId);
		query.setParameter(1, role);
		query.addEntity(Authority.class);
		return query.list();
	}

}
