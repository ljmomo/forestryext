package com.forestry.controller.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.Department;
import com.forestry.service.sys.DepartmentService;

import core.extjs.ExtJSBaseParameter;
import core.extjs.ListView;
import core.support.QueryResult;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
@Controller
@RequestMapping("/sys/department")
public class DepartmentController extends ForestryBaseController<Department> {

	@Resource
	private DepartmentService departmentService;

	@RequestMapping(value = "/saveDepartment", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(Department entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		Department department = departmentService.getByProerties("id", entity.getId());
		if (CMD_EDIT.equals(parameter.getCmd())) {
			departmentService.update(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			departmentService.persist(entity);
		}
		parameter.setCmd(CMD_EDIT);
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	@RequestMapping(value = "/getDepartmentList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getSysUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("start"));
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		String sortedObject = null;
		String sortedValue = null;
		List<LinkedHashMap<String, Object>> sortedList = mapper.readValue(request.getParameter("sort"), List.class);
		for (int i = 0; i < sortedList.size(); i++) {
			Map<String, Object> map = sortedList.get(i);
			sortedObject = (String) map.get("property");
			sortedValue = (String) map.get("direction");
		}
		Department department = new Department();
		department.setFirstResult(firstResult);
		department.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		department.setSortedConditions(sortedCondition);
		QueryResult<Department> queryResult = departmentService.doPaginationQuery(department);
		ListView<Department> forestryListView = new ListView<Department>();
		forestryListView.setData(queryResult.getResultList());
		forestryListView.setTotalRecord(queryResult.getTotalCount());
		writeJSON(response, forestryListView);
	}

	@RequestMapping("/deleteDepartment")
	public void deleteSysUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = departmentService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

}
