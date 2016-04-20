package com.example.design.util;

import com.example.design.model.resource.User;
import com.example.design.service.impl.UserService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * Created by lxh on 4/20/16.
 */
public class UserAuth {

    // 加密算法
    private static final String ALGO = "AES";

    // 加密密钥
    // private static final byte[] keyValue = new byte[] { 'T', 'h', 'e',
    // 'B','e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
    // 16位的加密密钥
    private static final byte[] keyValue = "4E7FF1C1F04F4B36".getBytes();

    private static UserService userService;

    public UserAuth(UserService userService) {
        this.userService = userService;
    }

    public static User auth(String value) throws Exception {
        String userInfo = new String(Base64.getDecoder().decode(value));
        String name = userInfo.split(":")[0];
        String hashedPasswd = userInfo.split(":")[1];
        String passwd = decrypt(hashedPasswd);
        User user = userService.getByAccountName(name);
        if (user != null) {
            return userService.getByAccountName(name);
        }
        return null;
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }

    /**
     * 用来进行解密的操作
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    /**
     * 用来进行加密的操作
     *
     * @param Data
     * @return
     * @throws Exception
     */
    public String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
}
