package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.SensorDataDao;
import com.forestry.model.sys.SensorData;
import com.forestry.service.sys.SensorDataService;

import core.service.BaseService;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Service
public class SensorDataServiceImpl extends BaseService<SensorData> implements SensorDataService {

	private SensorDataDao sensorDataDao;

	@Resource
	public void setSensorDataDao(SensorDataDao sensorDataDao) {
		this.sensorDataDao = sensorDataDao;
		this.dao = sensorDataDao;
	}

	@Override
	public List<Object[]> doGetSensorDataStatistics(Short sensorType) {
		return sensorDataDao.doGetSensorDataStatistics(sensorType);
	}

	@Override
	public List<Object[]> doGetEnhanceSensorDataStatistics(List<Object[]> list) {
		List<Object[]> enhanceList = new ArrayList<Object[]>();
		for (Object[] objectArray : list) {
			String[] sted = String.valueOf(objectArray[4]).split(",");
			Object[] object = new Object[] { objectArray[0], objectArray[1], objectArray[2], objectArray[3], String.valueOf(sted[0]), String.valueOf(sted[sted.length - 1]) };
			enhanceList.add(object);
		}
		return enhanceList;
	}

}
