/* 
 * Copyright 2013 Universal Bilgi Teknolojileri.
 * 
 * UKL 1.1 lisansı ile lisanslanmıştır. Bu dosyanın lisans koşullarına uygun 
 * olmayan şekilde kullanımı yasaklanmıştır. Lisansın bir kopyasını aşağıdaki 
 * linkten edinebilirsiniz.
 * 
 * http://www.uni-yaz.com/lisans/UKL_1_1.pdf
 * 
 * Yasalar aksini söylemediği veya yazılı bir sözleşme ile aksi belirtilmediği sürece,
 * bu yazılım mevcut hali ile hiç bir garanti vermeden veya herhangi bir şart ileri
 * sürmeden dağıtılır. Bu yazılımın edinim izinleri ve limitler konusunda lisans 
 * sözleşmesine bakınız.
 * 
 */
package com.tr.wordbook.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Service {

	public static String getHash(String toHash) {

		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] defaultBytes = toHash.getBytes();
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < messageDigest.length; i++) {
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
