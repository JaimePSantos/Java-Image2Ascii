package com.company;

import java.io.IOException;

public class AscMatrix {
    private String ascCode;
    private char[] ascCodeArray;
    private char[][] ascMatrix;
    private int[][] brightMatrix;
    private ImageParser img;
    private int height;
    private int width;

    public AscMatrix(ImageParser image) throws IOException {
        this.img = image;
        this.height = this.img.getHeight();
        this.width = this.img.getWidth();
        this.brightMatrix = this.img.getBrightness();
        this.ascCode = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        this.ascMatrix = new char[this.height][this.width];
        this.ascCodeArray = new char[this.ascCode.length()];
        for (int i = 0; i < ascCode.length(); i++) {
            this.ascCodeArray[i] = this.ascCode.charAt(i);
        }
    }

    public char[][] brightnessToAscii() {
        int a = 0;
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                a = (int) Math.floor(this.brightMatrix[row][col] / 4);
                this.ascMatrix[row][col] = ascCodeArray[a];
                System.out.print(this.ascMatrix[row][col]);
            }
            System.out.println("");
        }
        return this.ascMatrix;
    }

    public char[][] brightnessToAscii(boolean file) {
        int a = 0;
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.height; col++) {
                a = (int) Math.floor(this.brightMatrix[row][col] / 4);
                this.ascMatrix[row][col] = ascCodeArray[a];
                System.out.print(this.ascMatrix[row][col]);
            }
            System.out.println("");
        }
        return this.ascMatrix;
    }
}
