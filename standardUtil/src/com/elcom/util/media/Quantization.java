package com.elcom.util.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class performs simple color quantization of images,
 * utilising masks to diminish the amount of colors in a given picture.
 * Note that the masks below are not at all exhaustive of all palette
 * possibilities.
 */
public class Quantization {
    /*
     * The masks below are used for filtering the bits in an integer that
     * represents an RGB color. Note that the leftmost byte represents
     * the color's alpha channel, and the other bytes represent red, green
     * and blue (from left to right).
     */
    public static final int MASK_0 = 0x00800000; // 0 bits per channel (except red)
    public static final int MASK_1 = 0xff808080; // 1 bit per channel
    public static final int MASK_2 = 0xffc0c0c0; // 2 bits per channel
    public static final int MASK_3 = 0xffe0e0e0; // 3 bits per channel
    public static final int MASK_4 = 0xfff0f0f0; // 4 bits per channel

    /**
     * Returns a version of the original image with reduced amount of colours
     * (colours filtered using the mask passed in). The method returns the
     * processed image.
     * 
     * @param mask - to be used for color quantization
     * @param original - the original image
     * @return the processed image
     */
    public static BufferedImage quantizeImageColor(int mask, BufferedImage original) {
        int w = original.getWidth();
        int h = original.getHeight();
        // Create result image
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
        // Go through every pixel of the image
        for(int x=0; x< w; x++){
            for(int y=0; y< h; y++){
            	
            	/*int rgb = original.getRGB(x, y);
				int red = (rgb & 0x00ff0000) >> 16;
				int green = (rgb & 0x0000ff00) >> 8;
				int blue = rgb & 0x000000ff;
            	
				boolean isRed = isColorSpecify(red, green, blue, "red");
				boolean isPink = isColorSpecify(red, green, blue, "pink");
				boolean maroon = isColorSpecify(red, green, blue, "maroon");
				boolean coral = isColorSpecify(red, green, blue, "coral");
				
				int rgbValue = 0;
				if( isRed || isPink || coral || maroon )
					rgbValue = Color.RED.getRGB();
				else {
					rgbValue = detectDarkLightHumanEye("BLACK", red, green, blue) ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
				}*/
				
                // Apply mask to original value and save it in result image
                result.setRGB(x,y, 
                		//rgbValue
                		original.getRGB(x, y)
                		& mask);
            }
        }
        return result;
    }
    
    /*private static final int[][] distinctRGB = {{255, 255, 255},{0,0,0},{128,0,0},{255,0,0},{255, 200, 220},{170, 110, 40},{255, 150, 0},{255, 215, 180},{128, 128, 0},{255, 235, 0},{255, 250, 200},{190, 255, 0},{0, 190, 0},{170, 255, 195},{0, 0, 128},{100, 255, 255},{0, 0, 128},{67, 133, 255},{130, 0, 150},{230, 190, 255},{255, 0, 255},{128, 128, 128}};
	private static final String[] distinctColors = {"white","black","maroon","red","pink","brown","orange","coral","olive","yellow","beige","lime","green","mint","teal","cyan","navy","blue","purple","lavender","magenta","grey"};
	private static boolean isColorSpecify(int r, int g, int b, String inputColorName) {
		
		String colorReturn = "NA";
		
		double biggestDifference = 1000;
		
		for (int i = 0; i < distinctColors.length; i++) {
			
			if( Math.sqrt(Math.pow(r - distinctRGB[i][0],2) + Math.pow(g - distinctRGB[i][1],2) + Math.pow(b - distinctRGB[i][2],2)) < biggestDifference ) {
				
			    colorReturn = distinctColors[i];
			    biggestDifference = Math.sqrt(Math.pow(r - distinctRGB[i][0],2) + Math.pow(g - distinctRGB[i][1],2) + Math.pow(b - distinctRGB[i][2],2));
		    }
		}
		
		return inputColorName.equals(colorReturn);
	}
    
    private static boolean detectDarkLightHumanEye(String color, int red, int green, int blue) {
    	
    	double luminance = ( 0.299 * red + 0.587 * green + 0.114 * blue) / 255;
		
    	return "WHITE".equals(color) ? luminance > 0.5 : "BLACK".equals(color) ? luminance <= 0.5 : false;
    }*/
    
    public static void main(String[] args) throws IOException {
    	
        try {      
            BufferedImage img = ImageIO.read( new File("D:/tao.png"));
            
            BufferedImage quantImg =  Quantization.quantizeImageColor( Quantization.MASK_1, img );
            
            ImageIO.write(quantImg, "png", new File("D:/output.png"));
        }
        catch ( IOException e ) {
            System.out.println("Problem reading image.");
            e.printStackTrace();
        }
    }
}

