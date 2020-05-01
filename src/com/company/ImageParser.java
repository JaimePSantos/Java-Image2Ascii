package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;


public class ImageParser {
    private BufferedImage img;
    private URL path;
    private AscMatrix ascMatrix;
    private String imgName;

    public ImageParser(String fileName) throws IOException {
        this.imgName = fileName;
        fileName += ".jpg";
        this.path = getClass().getResource("/resources/"+fileName);
        this.img = ImageIO.read(this.path);
        System.out.println("Image successfully loaded: "+this.path.toString());
        this.ascMatrix = new AscMatrix(this);
    }

    public ImageParser(){
        this.img = null;
        this.path = null;
    }

    public ImageParser(BufferedImage image){

        this.img = image;
    }

    public int getWidth(){

        return this.img.getWidth();
    }

    public int getHeight(){

        return this.img.getHeight();
    }

    public void imgOut(String outputFile,String format) throws IOException{
        outputFile+="."+format;
        File path = new File("./out/production/AscImage/resources/outimg/"+outputFile);
        ImageIO.write(this.img,format,path);
        System.out.println("Created: "+path.getAbsolutePath());
    }

    public void imgOut(String outputFile,String format,boolean resize) throws IOException{
        outputFile+="_resize."+format;
        File path = null;

        if(resize==true){
            path = new File("./out/production/AscImage/resources/"+outputFile);
        }else{
            path = new File("./out/production/AscImage/resources/outimg/"+outputFile);
        }

        ImageIO.write(this.img,format,path);
        System.out.println("Created: "+path.getAbsolutePath());
    }

    public int[][] getBrightness(){
        int w = this.img.getWidth();
        int h = this.img.getHeight();
        int[][] brightness = new int[h][w];
        for(int row = 0; row < h;row++){
            for(int col=0; col<h;col++){
                int p = this.img.getRGB(col,row);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                brightness[row][col] = (r+g+b)/3;
            }
        }
        return brightness;
    }


    public int[][] getBrightness(boolean opt){
        if(opt==true){
            int w = this.img.getWidth();
            int h = this.img.getHeight();
            int[][] brightness = new int[h][w];
            FastRGB fastBright = new FastRGB(this.img);
            for(int row = 0; row < h;row++){
                for(int col=0; col<h;col++){
                    int p = fastBright.getRGB(col,row);
                    int a = (p>>24)&0xff;
                    int r = (p>>16)&0xff;
                    int g = (p>>8)&0xff;
                    int b = p&0xff;
                    brightness[row][col] = (r+g+b)/3;
                }
            }
            return brightness;
        }else{
           return this.getBrightness();
        }

    }

    public void printAsci(){
        this.ascMatrix.brightnessToAscii();
    }

    public void printAsci(boolean file) throws IOException {
        if(file==true){
            char[][] asciMatrix= this.ascMatrix.brightnessToAscii();
            int h=this.img.getHeight();
            FileWriter writer = new FileWriter(this.imgName+".txt");
            for(int row = 0; row < h;row++) {
                for (int col = 0; col < h; col++) {
                    writer.write(asciMatrix[row][col]+"");
                }
                writer.write("\n");
            }
            writer.close();
        }else{
            this.printAsci();
        }



    }

    public ImageParser resizeImage(String imageName,Integer imgWidth, Integer imgHeight) throws IOException {
        BufferedImage originalImage = this.img;
        int type = originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, imgWidth, imgHeight, null);
        g.dispose();
        ImageParser resImg = new ImageParser(resizedImage);

        resImg.imgOut(imageName,"jpg",true);

        return resImg;
    }



}
