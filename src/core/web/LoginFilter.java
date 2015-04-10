package core.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forestry.core.Constant;
import com.forestry.model.sys.SysUser;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String contextPath = request.getContextPath();
		String url = request.getRequestURI();
		url = url.replace(contextPath, "");
		if (url.startsWith("/sys") && !url.contains("/sys/sysuser/login") && !url.contains("/sys/attachment/getFlower") && !url.contains("/sys/sysuser/externalVerifySysUser")) {
			SysUser sysUser = (SysUser) request.getSession().getAttribute(Constant.SESSION_SYS_USER);
			if (null == sysUser) {
				response.sendRedirect(contextPath + "/login.jsp");
				return;
			}
			// SessionThreadLocal.setThreadSysUser(sysUser);
		}
		if (request.getMethod().equalsIgnoreCase("get")) {
			request = new GetHttpServletRequestWrapper(request, "UTF-8");
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
