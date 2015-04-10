package com.forestry.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import com.forestry.dao.sys.SysUserDao;
import com.forestry.model.sys.SysUser;
import com.forestry.service.sys.SysUserService;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class SSHTest {

	@Resource
	private SysUserDao sysUserDao;

	@Resource
	private SysUserService sysUserService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void testSave() {
		// System.out.println("sysUserService:::" + sysUserService);
		SysUser sysUser = sysUserService.getByProerties("userName", "admin");
	}

}
