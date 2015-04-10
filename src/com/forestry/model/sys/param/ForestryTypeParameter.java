package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class ForestryTypeParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = -6335587468796360403L;
	private String property;
	private String direction;
	private String descriptionNoHtml;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDescriptionNoHtml() {
		return descriptionNoHtml;
	}

	public void setDescriptionNoHtml(String descriptionNoHtml) {
		this.descriptionNoHtml = descriptionNoHtml;
	}

}
