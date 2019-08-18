package com.wedul.wedul_timeline.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash Util 클래스
 * 
 * @author wedul
 *
 */
public final class HashUtil {

	private HashUtil() {
	}
	
	/**
	 * MD5
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str){
		String MD5 = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
		}
		return MD5;
	}

	/**
	 * SHA256
	 * 
	 * @param str
	 * @return
	 */
	public static String sha256(String str) {
		String SHA = null;
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return SHA;
	}

}
