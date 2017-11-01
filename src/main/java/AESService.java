package com.szkingdom.wsp.common.service;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESService {

	public String encrypt(String sSrc, String key) {
		// TODO Auto-generated method stub
		String res = null;
		try{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        byte[] raw = key.getBytes();
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
	        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
	        // 此处使用BASE64做转码。
	        res= new BASE64Encoder().encode(encrypted);
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return res;
	}

	public AESService(String ivParameter) {
		super();
		this.ivParameter = ivParameter;
	}

	public String decrypt(String sSrc, String key) {
		// TODO Auto-generated method stub
		try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
             // 先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
	}
	private String ivParameter = "12345678901234ad";

	public static void main(String[] args) {
		AESService aesService = new AESService("qweasdzxc1234567");
		String encrypt = aesService.encrypt("你是傻逼吗？、这么坑爹","12345678901234ad");
		String decrypt = aesService.decrypt(encrypt,"12345678901234ad");
		System.out.println("encrypt ：" + encrypt);
		System.out.println("decrypt ： " + decrypt);
	}
}
