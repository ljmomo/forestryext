package com.forestry.controller.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.Attachment;
import com.forestry.service.sys.AttachmentService;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Controller
@RequestMapping("/sys/attachment")
public class AttachmentController extends ForestryBaseController<Attachment> {

	@Resource
	private AttachmentService attachmentService;

	@RequestMapping("/getFlower")
	public String getFlower(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String epcId = request.getParameter("epcId");
		List<Object[]> flowerList = attachmentService.queryFlowerList(epcId);
		Attachment attachment = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for (int i = 0; i < flowerList.size(); i++) {
			attachment = new Attachment();
			attachment.setFileName(String.valueOf(flowerList.get(i)[0]));
			attachment.setFilePath(String.valueOf(flowerList.get(i)[1]));
			attachment.setDescription(String.valueOf(flowerList.get(i)[2]));
			attachment.setEpcId(String.valueOf(flowerList.get(i)[3]));
			attachmentList.add(attachment);
		}
		writeJSON(response, attachmentList);
		return null;
	}

}
