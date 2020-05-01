package com.company;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;
import java.net.URL;


public class ImageParser {
    private BufferedImage img;
    private URL path;
    public ImageParser(String fileName) throws IOException {
        this.path = getClass().getResource("/resources/"+fileName);
        this.img = ImageIO.read(this.path);
        System.out.println("Image successfully loaded.");
    }


    public void imgOut(String outputFile,String format) throws IOException{
        outputFile+="."+format;
        String cam = getClass().getResource("/resources/").toString().replace("/","\\");
        System.out.println(cam);
        File path = new File("./out/production/AscImage/resources/outimg/"+outputFile);
        ImageIO.write(this.img,format,path);
        System.out.println("Created: "+path.getAbsolutePath());
    }

    public int getWidth(){
        return this.img.getWidth();
    }

    public int getHeight(){
        return this.img.getHeight();
    }

    public int[][] pixelDataArray() {

        final byte[] pixels = ((DataBufferByte) this.img.getRaster().getDataBuffer()).getData();
        final int width = this.img.getWidth();
        final int height = this.img.getHeight();
        final boolean hasAlphaChannel = this.img.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }
    public int[][] convertTo2DUsingGetRGB() {
        int width = this.img.getWidth();
        int height = this.img.getHeight();
        FastRGB image = new FastRGB(this.img);
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(col, row);
            }
        }

        return result;
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

    public void printAsci(int[][] brightness){
        String string = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        char[] ch = new char[string.length()];

        for(int i = 0; i< string.length();i++){
            ch[i] = string.charAt(i);
        }

        int w = this.img.getWidth();
        int h = this.img.getHeight();
        char[][] brightAscii = new char[h][w];

        for(int row = 0; row < h;row++) {
            for (int col = 0; col < h; col++) {
                int a = (int)Math.floor(brightness[row][col]/4);
                brightAscii[row][col] = ch[a];
                System.out.print(brightAscii[row][col]);
            }
            System.out.println("");
        }
    }

}
