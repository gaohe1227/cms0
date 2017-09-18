package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guoqiang 2013-11-14
 * 
 */
public class MD5MessageEncrypt extends MessageEncrypt {

	@Override
	public byte[] decode(byte[] input) {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] encode(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				int i = b & 0xFF;
				if (i <= 0xF) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
			return sb.toString().getBytes();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
