package com.semura.steganography;

import com.semura.steganography.utils.SteganographyUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class Steganography {
    public static void main(String[] args) {
        SteganographyUtil.integrateMessage(new File("C:\\Users\\murav\\IdeaProjects\\Steganography\\images\\sources\\1.png"), "Hello".getBytes(StandardCharsets.UTF_8));
        String s = new String(SteganographyUtil.disintegrateMessage(new File("C:\\Users\\murav\\IdeaProjects\\Steganography\\images\\results\\1-ENCRPYPTED.png")));
        System.out.println(s);
    }
}
