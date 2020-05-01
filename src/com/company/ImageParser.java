package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;


public class ImageParser {
    private BufferedImage img;
    private URL path;
    private AscMatrix ascMatrix;

    public ImageParser(String fileName) throws IOException {
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


    public void imgOut(String outputFile,String format) throws IOException{
        outputFile+="."+format;
        String cam = getClass().getResource("/resources/").toString().replace("/","\\");
        System.out.println(cam);
        File path = new File("./out/production/AscImage/resources/outimg/"+outputFile);
        ImageIO.write(this.img,format,path);
        System.out.println("Created: "+path.getAbsolutePath());
    }

    public void imgOut(String outputFile,String format,boolean resize) throws IOException{
        outputFile+="_resize."+format;
        String cam = getClass().getResource("/resources/").toString().replace("/","\\");
        File path = null;

        if(resize==true){
            path = new File("./out/production/AscImage/resources/"+outputFile);
        }else{
            path = new File("./out/production/AscImage/resources/outimg/"+outputFile);
        }

        ImageIO.write(this.img,format,path);
        System.out.println("Created: "+path.getAbsolutePath());
    }

    public int getWidth(){

        return this.img.getWidth();
    }

    public int getHeight(){

        return this.img.getHeight();
    }


    public int[][] getBrightness(){
        int w = this.img.getWidth();
        int h = this.img.getHeight();
        int[][] brightness = new int[h][w];
        for(int row = 0; row < h;row++){
            for(int col=0; col<h;col++){
                int p = img.getRGB(col,row);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                brightness[row][col] = (r+g+b)/3;
            }
        }
        return brightness;
    }

    public void printAsci(){
        this.ascMatrix.brightnessToAscii();
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
