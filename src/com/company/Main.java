package com.company;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("File name?");
        String fileName = scanner.nextLine();
        String path =  Main.class.getResource(fileName).toString();
        ImageParser image = new ImageParser(fileName);

        int[][] array1 = image.getBrightness();

        image.printAsci(image.getBrightness());

    }

}

