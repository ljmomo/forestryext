package com.forestry.dao.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.AttachmentDao;
import com.forestry.model.sys.Attachment;

import core.dao.BaseDao;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Repository
public class AttachmentDaoImpl extends BaseDao<Attachment> implements AttachmentDao {

	public AttachmentDaoImpl() {
		super(Attachment.class);
	}

	@Override
	public List<Object[]> queryFlowerList(String epcId) {
		Query query = this.getSession().createSQLQuery("select ft.name,group_concat(a.file_path),ft.description,f.epc_id from forestry_type ft inner join forestry f on ft.id=f.type_id left join attachment a on a.forestrytype_id=ft.id where f.epc_id=? group by a.file_name");
		query.setParameter(0, epcId);
		return query.list();
	}

	@Override
	public void deleteAttachmentByForestryTypeId(Long forestryTypeId) {
		Query query = this.getSession().createSQLQuery("delete from attachment where forestrytype_id = :forestryTypeId");
		query.setParameter("forestryTypeId", forestryTypeId);
		query.executeUpdate();
	}

}
