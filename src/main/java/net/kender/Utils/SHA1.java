package net.kender.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;

public class SHA1 {
    private String sha1NUM;
    private SHA1(String sha){
        sha1NUM = sha;
    }
    public boolean compareTo(SHA1 a){
        if(sha1NUM == a.sha1NUM){
            return true;
        }else{
            return false;
        }
    }
    public static SHA1 of(File a) throws NoSuchAlgorithmException, IOException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(a.getAbsolutePath()), md)) {
            // Leemos el archivo mientras se calcula el hash
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) {
                // No es necesario hacer nada aquí, el hash se calcula automáticamente
            }
        }

        // Convertimos el hash a una cadena hexadecimal
        byte[] hash = md.digest();
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return new SHA1(hexString.toString());
    }
    public static SHA1 of(String sha){
        return new SHA1(sha);
    }
}
