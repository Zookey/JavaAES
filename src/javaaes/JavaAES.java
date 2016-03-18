/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaaes;

/**
 *
 * @author Zoran
 */
public class JavaAES {

    public static void main(String[] args) throws Exception {
        
        String plainText = "SECRET MESSAGE";

        AESCrypt aesCrypt = new AESCrypt();
        String encryptedText = aesCrypt.encrypt(plainText);
        String decryptedText = aesCrypt.decrypt(encryptedText);

        System.out.println("Plain text: "+plainText);
        System.out.println("Encrypted message: " + encryptedText);
        System.out.println("Decrypted message: " + decryptedText);
    }

}
