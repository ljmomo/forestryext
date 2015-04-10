package core.util;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class DESede {

	public static final String DESede = "DESede";

	public static String decode(String secretKey, String cipherText) throws GeneralSecurityException {
		byte[] kb = secretKey.getBytes();
		SecretKeySpec k = new SecretKeySpec(kb, DESede);
		Cipher cp = Cipher.getInstance(DESede);
		cp.init(Cipher.DECRYPT_MODE, k);
		byte[] srcByte = cp.doFinal(cipherText.getBytes());
		return new String(srcByte);
	}

	public static String decode(String secretKey, byte[] cipherByte) throws GeneralSecurityException {
		byte[] kb = secretKey.getBytes();
		SecretKeySpec k = new SecretKeySpec(kb, DESede);
		Cipher cipher = Cipher.getInstance(DESede);
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] srcByte = cipher.doFinal(cipherByte);
		return new String(srcByte);
	}

	public static String encode(String secretKey, String srcText) throws GeneralSecurityException {
		byte[] kb = secretKey.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(kb, DESede);
		Cipher cipher = Cipher.getInstance(DESede);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] cipherByte = cipher.doFinal(srcText.getBytes());
		return new String(cipherByte);
	}

	public static String encode(String secretKey, byte[] srcByte) throws GeneralSecurityException {
		byte[] kb = secretKey.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(kb, DESede);
		Cipher cipher = Cipher.getInstance(DESede);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] cipherByte = cipher.doFinal(srcByte);
		return new String(cipherByte);
	}

}
