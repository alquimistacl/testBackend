package com.ionix.backend;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncodeTest {

	private String encode(String plainRut)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

		DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

		SecretKey generateSecret = keyFactory.generateSecret(keySpec);

		byte[] cleartext = plainRut.getBytes("UTF8");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, generateSecret);

		return Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
	}

	public static void main(String[] args) {

		EncodeTest encode = new EncodeTest();
		try {
			System.out.println(encode.encode("1-9"));
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}
}
