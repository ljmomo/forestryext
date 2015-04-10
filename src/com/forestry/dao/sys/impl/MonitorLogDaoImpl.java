package com.forestry.dao.sys.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.MonitorLogDao;
import com.forestry.model.sys.MonitorLog;

import core.dao.BaseDao;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Repository
public class MonitorLogDaoImpl extends BaseDao<MonitorLog> implements MonitorLogDao {

	public MonitorLogDaoImpl() {
		super(MonitorLog.class);
	}

	@Override
	public List<MonitorLog> querySensorMonitorLog() {
		SQLQuery query = getSession()
				.createSQLQuery(
						"select ss.xcoordinate,ss.ycoordinate,ss.epc_id,ml.object_id,ml.message,ml.value from sensor ss,monitor_log ml where ss.sensor_id = ml.object_id and ss.xcoordinate is not null and ss.ycoordinate is not null and ss.type = 1 and ml.value = (select min(value) from monitor_log mlt where ml.object_id = mlt.object_id)");
		return query.list();
	}

}
