package com.semura.steganography.ui;

import com.semura.steganography.utils.DecryptCipherOperator;
import com.semura.steganography.utils.EncryptCipherOperator;
import com.semura.steganography.utils.SteganographyUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class GUI extends JFrame {
    private JFileChooser fileInputChooser = new JFileChooser();
    private JFileChooser fileOutputChooser = new JFileChooser();
    private JTextArea textArea = new JTextArea();
    private JLabel labelInputPath = new JLabel("Empty");
    private JLabel labelOutputPath = new JLabel("Empty");
    private JButton encryptBtn = new JButton("Encrypt");
    private JButton decryptBtn = new JButton("Decrypt");
    private JButton chooserInputBtn = new JButton("Select (IN)");
    private JButton chooserOutputBtn = new JButton("Select (OUT)");

    private final EncryptCipherOperator encryptCipherOperator = new EncryptCipherOperator();
    private final DecryptCipherOperator decryptCipherOperator = new DecryptCipherOperator();

    public GUI() throws HeadlessException {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new JPanel());
        add(labelInputPath);
        add(chooserInputBtn);
        add(labelOutputPath);
        add(chooserOutputBtn);
        chooserInputBtn.addActionListener(l -> selectInputFile());
        chooserOutputBtn.addActionListener(l -> selectOutputFile());
        add(new JScrollPane(textArea));
        textArea.setRows(5);
        textArea.setColumns(30);
        add(encryptBtn);
        encryptBtn.addActionListener(l -> encrypt());
        add(decryptBtn);
        decryptBtn.addActionListener(l -> decrypt());
        setSize(300, 300);
        setVisible(true);
    }

    private void btnDrawer() {
        File in = fileInputChooser.getSelectedFile();
        File out = fileOutputChooser.getSelectedFile();
        if (in == null) {
            String empty = "Empty";
            labelInputPath.setText(empty);
            labelOutputPath.setText(empty);
            encryptBtn.setVisible(false);
            decryptBtn.setVisible(false);
            return;
        }
        if (out == null) {
            String empty = "Empty";
            labelOutputPath.setText(empty);
            encryptBtn.setVisible(false);
            return;
        }
        encryptBtn.setVisible(true);
        decryptBtn.setVisible(true);
    }

    private void selectInputFile() {
        fileInputChooser.showOpenDialog(this);
        File selectedFile = fileInputChooser.getSelectedFile();
        if (selectedFile == null)
            labelInputPath.setText("Empty");
        else
            labelInputPath.setText(selectedFile.getAbsolutePath());
        btnDrawer();
    }

    private void selectOutputFile() {
        fileOutputChooser.showSaveDialog(this);
        File selectedFile = fileOutputChooser.getSelectedFile();
        if (selectedFile == null)
            labelOutputPath.setText("Empty");
        else
            labelOutputPath.setText(selectedFile.getAbsolutePath());
        btnDrawer();
    }

    private void encrypt() {
        String s = encryptCipherOperator.apply(textArea.getText());
        System.out.println(s.getBytes(StandardCharsets.UTF_8).length);
        File file = SteganographyUtil.integrateMessage(fileInputChooser.getSelectedFile(),
                s.getBytes(StandardCharsets.UTF_8),
                fileOutputChooser.getSelectedFile());
        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(SteganographyUtil.reader(file))));
    }

    private void decrypt() {
        byte[] bytes = SteganographyUtil.disintegrateMessage(fileInputChooser.getSelectedFile());
        JOptionPane.showMessageDialog(null, new JLabel(decryptCipherOperator.apply(new String(bytes))));
    }
}
