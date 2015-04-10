package core.util;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class IPChecker {

	/**
	 * 判断目标IP是否在指定范围内
	 * 
	 * @param ipCheck 目标IP
	 * @param ipStart 指定I范围开始
	 * @param ipEnd 指定I范围结束
	 * @return
	 */
	public static boolean ipRangCheck(String ipCheck, String ipStart, String ipEnd) {
		boolean result = false;
		long ipStartL = getIpNum(ipStart);
		long ipEndL = getIpNum(ipEnd);
		long ipCheckL = getIpNum(ipCheck);
		if (isInner(Long.valueOf(ipCheckL), Long.valueOf(ipStartL), Long.valueOf(ipEndL))) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/* 获取IP数 */
	private static long getIpNum(String ipAddress) {
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);
		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}

	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}

}