package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class ForestryParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = 4462121985297150686L;
	private String forestryTypeName;
	private Long forestryTypeId;
	private String $like_epcId;
	private String $like_name;
	private Long $eq_typeId;
	private String forestryTypeImagePath;

	public String getForestryTypeName() {
		return forestryTypeName;
	}

	public void setForestryTypeName(String forestryTypeName) {
		this.forestryTypeName = forestryTypeName;
	}

	public Long getForestryTypeId() {
		return forestryTypeId;
	}

	public void setForestryTypeId(Long forestryTypeId) {
		this.forestryTypeId = forestryTypeId;
	}

	public String get$like_epcId() {
		return $like_epcId;
	}

	public void set$like_epcId(String $like_epcId) {
		this.$like_epcId = $like_epcId;
	}

	public String get$like_name() {
		return $like_name;
	}

	public void set$like_name(String $like_name) {
		this.$like_name = $like_name;
	}

	public Long get$eq_typeId() {
		return $eq_typeId;
	}

	public void set$eq_typeId(Long $eq_typeId) {
		this.$eq_typeId = $eq_typeId;
	}

	public String getForestryTypeImagePath() {
		return forestryTypeImagePath;
	}

	public void setForestryTypeImagePath(String forestryTypeImagePath) {
		this.forestryTypeImagePath = forestryTypeImagePath;
	}

}
