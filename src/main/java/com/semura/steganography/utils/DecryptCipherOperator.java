package com.semura.steganography.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.UnaryOperator;

public class DecryptCipherOperator implements UnaryOperator<String> {

    private final File keyFile = new File("./k.key");
    private final Cipher cipher;

    private SecretKey key;

    @SneakyThrows
    public DecryptCipherOperator() {
        try {
            key = readKey(keyFile);
        } catch (IOException | ClassNotFoundException e) {
            KeyGenerator aes = KeyGenerator.getInstance("AES");
            aes.init(256);
            key = aes.generateKey();
            writeKey(keyFile);
        }
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
    }

    private <T> T readKey(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        return (T) inputStream.readObject();
    }

    @SneakyThrows
    private void writeKey(File file) {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(key);
    }


    @SneakyThrows
    @Override
    public String apply(String s) {
        System.out.println(s.length());
        return new String(s.getBytes(StandardCharsets.UTF_8));
    }
}
