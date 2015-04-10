package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class SysUserParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = 7656443663108619135L;
	private String $like_userName;
	private String $like_realName;
	private Short $eq_role;
	private String roleName;

	public String get$like_userName() {
		return $like_userName;
	}

	public void set$like_userName(String $like_userName) {
		this.$like_userName = $like_userName;
	}

	public String get$like_realName() {
		return $like_realName;
	}

	public void set$like_realName(String $like_realName) {
		this.$like_realName = $like_realName;
	}

	public Short get$eq_role() {
		return $eq_role;
	}

	public void set$eq_role(Short $eq_role) {
		this.$eq_role = $eq_role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
