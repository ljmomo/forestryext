package core.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private String charset = "UTF-8";

	public GetHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 获得被装饰对象的引用和采用的字符编码
	 * 
	 * @param request
	 * @param charset
	 */
	public GetHttpServletRequestWrapper(HttpServletRequest request, String charset) {
		super(request);
		this.charset = charset;
	}

	/**
	 * 调用被包装的请求对象的getParameter方法获得参数，然后再进行编码转换
	 */
	public String getParameter(String name) {
		String value = super.getParameter(name);
		value = value == null ? null : convert(value);
		return value;
	}

	public String convert(String target) {
		try {
			return new String(target.trim().getBytes("ISO-8859-1"), charset);
		} catch (UnsupportedEncodingException e) {
			return target;
		}
	}
}
