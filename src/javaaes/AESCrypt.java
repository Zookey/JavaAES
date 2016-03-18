/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaaes;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author Zoran
 */
public class AESCrypt {

    private final Cipher cipher;
    IvParameterSpec ivParams;
    private final SecretKey secertKey;
    private String encryptedText, decryptedText;

    public AESCrypt() throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        ivParams = new IvParameterSpec(iv);
        secertKey = generateKey();
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generate a 128-bit key
        final int outputKeyLength = 128;

        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(outputKeyLength, secureRandom);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secertKey, ivParams);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        encryptedText = DatatypeConverter.printBase64Binary(encrypted);
        return encryptedText;
    }

    public String decrypt(String cryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secertKey, ivParams);
        byte[] bytes = DatatypeConverter.parseBase64Binary(cryptedText);
        byte[] decrypted = cipher.doFinal(bytes);
        decryptedText = new String(decrypted, "UTF-8");
        return decryptedText;
    }

}
