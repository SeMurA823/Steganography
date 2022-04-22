package com.semura.steganography;

import com.semura.steganography.ui.GUI;
import com.semura.steganography.utils.SteganographyUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class Steganography {
    public static void main(String[] args) {
//        File file = SteganographyUtil.integrateMessage(new File("C:\\Users\\murav\\IdeaProjects\\Steganography\\images\\sources\\1.png"), "Hello".getBytes(StandardCharsets.UTF_8));
//        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(SteganographyUtil.reader(file))));
//        String s = new String(SteganographyUtil.disintegrateMessage(new File("C:\\Users\\murav\\IdeaProjects\\Steganography\\images\\results\\1-ENCRPYPTED.png")));
//        ;
        new GUI();
    }
}
