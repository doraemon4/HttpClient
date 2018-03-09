package com.cn.stephen.HttpClient;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Encoder;

public class EncryptUtil {
	/**
	 * 使用java原生(先MD5加密在base64编码)
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5Encode(String str) {
		String newString = "";
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			newString = base64Encoder.encode(digest.digest(str
					.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newString;
	}

	/**
	 * 使用Apache的工具类
	 * @param str
	 * @return
	 */
	public static String MD5Encode2(String str) {
		String newString = "";
		try {
			newString = Base64.encodeBase64String(DigestUtils.md5(str
					.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newString;
	}
	
	public String encode(String str) {
		try {
			Hex.encodeHexString(DigestUtils.md5(str));
			//查看源码等价于
			DigestUtils.md5Hex(str);
			
			DigestUtils.shaHex(str);
			
			Hex.encodeHexString(DigestUtils.sha256(str));
			//查看源码等价于
			DigestUtils.sha256Hex(str.getBytes("utf-8"));
			
			DigestUtils.sha384Hex(str);
			DigestUtils.sha512Hex(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(EncryptUtil.MD5Encode("哆啦A梦"));
		System.out.println(EncryptUtil.MD5Encode2("哆啦A梦"));
	}
}
