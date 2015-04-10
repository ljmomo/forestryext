package com.forestry.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.ForestryTypeDao;
import com.forestry.model.sys.ForestryType;
import com.forestry.service.sys.ForestryTypeService;

import core.service.BaseService;
import core.util.HtmlUtils;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Service
public class ForestryTypeServiceImpl extends BaseService<ForestryType> implements ForestryTypeService {

	private ForestryTypeDao forestryTypeDao;

	@Resource
	public void setForestryTypeDao(ForestryTypeDao forestryTypeDao) {
		this.forestryTypeDao = forestryTypeDao;
		this.dao = forestryTypeDao;
	}

	@Override
	public List<ForestryType> getForestryTypeList(List<ForestryType> resultList) {
		List<ForestryType> forestryTypeList = new ArrayList<ForestryType>();
		for (ForestryType entity : resultList) {
			ForestryType forestryType = new ForestryType();
			forestryType.setId(entity.getId());
			forestryType.setName(entity.getName());
			forestryType.setDescription(entity.getDescription());
			forestryType.setDescriptionNoHtml(HtmlUtils.htmltoText(entity.getDescription()));
			forestryTypeList.add(forestryType);
		}
		return forestryTypeList;
	}

}
