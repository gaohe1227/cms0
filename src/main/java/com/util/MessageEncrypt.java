/**
 * 
 */
package com.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author guoqiang 2013-11-14
 * 
 */
public abstract class MessageEncrypt {
	private static Map<String, MessageEncrypt> algorithm = new HashMap<String, MessageEncrypt>();

	static {
		MessageEncrypt.put("MD5", new MD5MessageEncrypt());
	}

	public String encode(String... input) {
		StringBuilder sb = new StringBuilder();
		for ( String s : input ) {
			sb.append(s);
		}
		return encode(sb.toString());
	}

	public String encode(String input) {
		if (input != null) {
			byte[] b = null;
			try {
				b = encode(input.getBytes("GBK"));
			} catch ( UnsupportedEncodingException e ) {
				e.printStackTrace();
			}

			if (b != null) return new String(b);
		}
		return null;
	}

	public String decode(String input) {
		if (input != null) {
			byte[] b = null;
			try {
				b = decode(input.getBytes("GBK"));
			} catch ( UnsupportedEncodingException e ) {
				e.printStackTrace();
			}

			if (b != null) try {
				return new String(b, "GBK");
			} catch ( UnsupportedEncodingException e ) {
				return new String(b);
			}
		}
		return null;
	}

	public abstract byte[] encode(byte[] input);

	public abstract byte[] decode(byte[] input);

	public static void put(String name, MessageEncrypt alg) {
		algorithm.put(name.toUpperCase(), alg);
	}

	public static MessageEncrypt getInstance(String name) {
		return algorithm.get(name.toUpperCase());
	}

}
