package com.forestry.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.SensorDao;
import com.forestry.model.sys.Sensor;
import com.forestry.service.sys.SensorService;

import core.service.BaseService;
import core.support.QueryResult;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Service
public class SensorServiceImpl extends BaseService<Sensor> implements SensorService {

	private SensorDao sensorDao;

	@Resource
	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
		this.dao = sensorDao;
	}

	@Override
	public List<Sensor> querySensorBySensorType(Short sensorType) {
		return sensorDao.querySensorBySensorType(sensorType);
	}

	@Override
	public List<Sensor> querySensorLastData() {
		return sensorDao.querySensorLastData();
	}

	@Override
	public QueryResult<Sensor> querySensorList(Sensor sensor) {
		return sensorDao.querySensorList(sensor);
	}

	@Override
	public List<Sensor> querySensorLastDataWithEpcId() {
		return sensorDao.querySensorLastDataWithEpcId();
	}

	@Override
	public List<Sensor> queryForestrySensorLastData() {
		return sensorDao.queryForestrySensorLastData();
	}

}
