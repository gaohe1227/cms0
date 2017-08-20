package com.common;
/**
 * @author Internet
 * Base64 加密算法的Java实现（改）
 * 使密文和补码可以自定义
 */
public class Base64Like {
	//加密字符编码表
	private static final StringBuilder codingTable =
		new StringBuilder("abcdefghijklmnopqrstuvwxyz-0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	//填充字符
	private static final char fillChar
		// = '-';
		= '!';
	
	//编码表是否正确
	private static boolean isValidCodingTable = true;
	
	//加密编码对照表
	private static final byte[] encodingTable;
	//解密编码对照表
	private static final byte[] decodingTable;
	
	static {
		//根据字符编码表构建加、解密编码对照表
		int codingCount = codingTable.length();
		if (codingCount == 64) {
			encodingTable = new byte[64];
			decodingTable = new byte[128];

			for (int i = 0; i < 128; i++) {
				decodingTable[i] = (byte) -1;
			}
			
			for (int i = 0; i < codingCount; i ++) {
				char curCode = codingTable.charAt(i);
				if (decodingTable[curCode] == -1) {
					encodingTable[i] = (byte)curCode;
					decodingTable[curCode] = (byte)i;
				} else {
					isValidCodingTable = false;
					break;
				}
			}
		} else {
			encodingTable = null;
			decodingTable = null;

			isValidCodingTable = false;
		}

	}

	public static String encode(String input) throws Exception {
		if (!isValidCodingTable) {
			throw new Exception(Base64Like.class.getName() + " encode method error: bad coding table defined!");
		}
		
		byte[] bytes;
		byte[] data = input.getBytes();

		int modulus = data.length % 3;

		if (modulus == 0) {
			bytes = new byte[(4 * data.length) / 3];
		} else {
			bytes = new byte[4 * ((data.length / 3) + 1)];
		}

		int dataLength = (data.length - modulus);
		int a1;
		int a2;
		int a3;

		for (int i = 0, j = 0; i < dataLength; i += 3, j += 4) {
			a1 = data[i] & 0xff;
			a2 = data[i + 1] & 0xff;
			a3 = data[i + 2] & 0xff;

			bytes[j] = encodingTable[(a1 >>> 2) & 0x3f];
			bytes[j + 1] = encodingTable[((a1 << 4) | (a2 >>> 4)) & 0x3f];
			bytes[j + 2] = encodingTable[((a2 << 2) | (a3 >>> 6)) & 0x3f];
			bytes[j + 3] = encodingTable[a3 & 0x3f];
		}

		int b1;
		int b2;
		int b3;
		int d1;
		int d2;

		switch (modulus) {
		case 0: /* nothing left to do */
			break;

		case 1:
			d1 = data[data.length - 1] & 0xff;
			b1 = (d1 >>> 2) & 0x3f;
			b2 = (d1 << 4) & 0x3f;

			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = (byte) fillChar;
			bytes[bytes.length - 1] = (byte) fillChar;

			break;

		case 2:
			d1 = data[data.length - 2] & 0xff;
			d2 = data[data.length - 1] & 0xff;

			b1 = (d1 >>> 2) & 0x3f;
			b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
			b3 = (d2 << 2) & 0x3f;

			bytes[bytes.length - 4] = encodingTable[b1];
			bytes[bytes.length - 3] = encodingTable[b2];
			bytes[bytes.length - 2] = encodingTable[b3];
			bytes[bytes.length - 1] = (byte) fillChar;

			break;
		}

		return new String(bytes);
	}

	public static String decode(byte[] data) throws Exception {
		if (!isValidCodingTable) {
			throw new Exception(Base64Like.class.getName() + " encode method error: bad coding table defined!");
		}
		
		byte[] bytes;
		byte b1;
		byte b2;
		byte b3;
		byte b4;

		data = discardNonBase64Bytes(data);

		if (data[data.length - 2] == fillChar) {
			bytes = new byte[(((data.length / 4) - 1) * 3) + 1];
		} else if (data[data.length - 1] == fillChar) {
			bytes = new byte[(((data.length / 4) - 1) * 3) + 2];
		} else {
			bytes = new byte[((data.length / 4) * 3)];
		}

		for (int i = 0, j = 0; i < (data.length - 4); i += 4, j += 3) {
			b1 = decodingTable[data[i]];
			b2 = decodingTable[data[i + 1]];
			b3 = decodingTable[data[i + 2]];
			b4 = decodingTable[data[i + 3]];

			bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[j + 2] = (byte) ((b3 << 6) | b4);
		}

		if (data[data.length - 2] == fillChar) {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];

			bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
		} else if (data[data.length - 1] == fillChar) {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];
			b3 = decodingTable[data[data.length - 2]];

			bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
		} else {
			b1 = decodingTable[data[data.length - 4]];
			b2 = decodingTable[data[data.length - 3]];
			b3 = decodingTable[data[data.length - 2]];
			b4 = decodingTable[data[data.length - 1]];

			bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
		}

		return new String(bytes);
	}

	public static String decode(String data) throws Exception {
		if (!isValidCodingTable) {
			throw new Exception(Base64Like.class.getName() + " encode method error: bad coding table defined!");
		}
		
		byte[] bytes;
		byte b1;
		byte b2;
		byte b3;
		byte b4;

		data = discardNonBase64Chars(data);

		if (data.charAt(data.length() - 2) == fillChar) {
			bytes = new byte[(((data.length() / 4) - 1) * 3) + 1];
		} else if (data.charAt(data.length() - 1) == fillChar) {
			bytes = new byte[(((data.length() / 4) - 1) * 3) + 2];
		} else {
			bytes = new byte[((data.length() / 4) * 3)];
		}

		for (int i = 0, j = 0; i < (data.length() - 4); i += 4, j += 3) {
			b1 = decodingTable[data.charAt(i)];
			b2 = decodingTable[data.charAt(i + 1)];
			b3 = decodingTable[data.charAt(i + 2)];
			b4 = decodingTable[data.charAt(i + 3)];

			bytes[j] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[j + 1] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[j + 2] = (byte) ((b3 << 6) | b4);
		}

		if (data.charAt(data.length() - 2) == fillChar) {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];

			bytes[bytes.length - 1] = (byte) ((b1 << 2) | (b2 >> 4));
		} else if (data.charAt(data.length() - 1) == fillChar) {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];
			b3 = decodingTable[data.charAt(data.length() - 2)];

			bytes[bytes.length - 2] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 1] = (byte) ((b2 << 4) | (b3 >> 2));
		} else {
			b1 = decodingTable[data.charAt(data.length() - 4)];
			b2 = decodingTable[data.charAt(data.length() - 3)];
			b3 = decodingTable[data.charAt(data.length() - 2)];
			b4 = decodingTable[data.charAt(data.length() - 1)];

			bytes[bytes.length - 3] = (byte) ((b1 << 2) | (b2 >> 4));
			bytes[bytes.length - 2] = (byte) ((b2 << 4) | (b3 >> 2));
			bytes[bytes.length - 1] = (byte) ((b3 << 6) | b4);
		}

		return new String(bytes);
	}

	private static byte[] discardNonBase64Bytes(byte[] data) {
		byte[] temp = new byte[data.length];
		int bytesCopied = 0;

		for (int i = 0; i < data.length; i++) {
			if (isValidBase64Byte(data[i])) {
				temp[bytesCopied++] = data[i];
			}
		}

		byte[] newData = new byte[bytesCopied];

		System.arraycopy(temp, 0, newData, 0, bytesCopied);

		return newData;
	}

	private static String discardNonBase64Chars(String data) {
		StringBuffer sb = new StringBuffer();

		int length = data.length();

		for (int i = 0; i < length; i++) {
			if (isValidBase64Byte((byte) (data.charAt(i)))) {
				sb.append(data.charAt(i));
			}
		}

		return sb.toString();
	}

	private static boolean isValidBase64Byte(byte b) {
		if (b == fillChar) {
			return true;
		} else if ((b < 0) || (b >= 128)) {
			return false;
		} else if (decodingTable[b] == -1) {
			return false;
		}

		return true;
	}
	
	public static void main(String[] args) {
		try {
			//String test = "Hellow Word!1";
			String test = "张新荣2010-09-08德惠也扩.xls";
			//String test = "在用C或者C++处理大数时感觉非常麻烦，但是在JAVA中有两个类BigInteger和BigDecimal分别表示大整数类和大浮点数类";
			System.out.println("加密前的字符：" + test);
			System.out.println("加密后的字符：" + encode(test));
			System.out.println("解密后的字符：" + decode(encode(test)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
