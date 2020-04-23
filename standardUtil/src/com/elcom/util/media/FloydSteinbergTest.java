package com.elcom.util.media;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;

public class FloydSteinbergTest {

	private static final Color[] PALETTE = new Color[]{
	        new Color(221, 221, 221),
	        new Color(19, 125, 62),
	        new Color(179, 80, 188),
	        new Color(107, 138, 201),
	        new Color(177, 166, 39),
	        new Color(65, 174, 56),
	        new Color(208, 132, 153),
	        new Color(64, 64, 64),
	        new Color(154, 161, 161),
	        new Color(46, 110, 137),
	        new Color(126, 61, 181),
	        new Color(46, 56, 141),
	        new Color(79, 50, 31),
	        new Color(53, 70, 27),
	        new Color(150, 52, 48),
	        new Color(25, 22, 22)};

	public static void main(String[] args) {

	    String lImgFile = "D:/tao.png";
	    try {
	        // Load image
	        BufferedImage lImage = ImageIO.read(new File(lImgFile));

	        BufferedImage lOutImage = applyDitheredPalette(lImage, PALETTE);
	        ImageIO.write(lOutImage, "png", new File("D:/output.png"));
	    } catch (IOException lEx) {
	        System.out.println(lEx.getMessage());
	    }
	}

	/**
	 * @param pPalette Color palette to apply.
	 * @param pImage   Image to apply palette on.
	 * @return {@link java.awt.image.BufferedImage} corresponding to pPalette applied on pImage using naive Floyd-Steinberg implementation
	 */
	public static BufferedImage applyDitheredPalette(BufferedImage pImage, Color[] pPalette) {
	    int lWidth = pImage.getWidth();
	    int lHeight = pImage.getHeight();
	    IndexColorModel lColorModel = paletteToColorModel(pPalette);
	    BufferedImage lImageOut = new BufferedImage(lWidth, lHeight, BufferedImage.TYPE_BYTE_INDEXED, lColorModel);
	    for (int y = (lHeight - 1); y >= 0; y--) {
	        for (int x = 0; x < lWidth; x++) {

	            // Get original pixel color channels
	            int lInitialPixelColor = pImage.getRGB(x, y);

	            // Finding nearest color in the palette
	            Color lNearestColor = getNearestColor(lInitialPixelColor, pPalette);

	            // Set quantized pixel
	            lImageOut.setRGB(x, y, lNearestColor.getRGB());

	            // Applying Floyd-Steinberg dithering
	            int quantizationError = lInitialPixelColor - lNearestColor.getRGB();

	            if ((x + 1) < lWidth) {
	                int lPixel = pImage.getRGB(x + 1, y);
	                lImageOut.setRGB(x + 1, y, lPixel + (quantizationError * (7 / 16)));
	            }

	            if ((x - 1) > 0 && (y + 1) < lHeight) {
	                int lPixel = pImage.getRGB(x - 1, y + 1);
	                lImageOut.setRGB(x - 1, y + 1, lPixel + (quantizationError * (3 / 16)));
	            }

	            if ((y + 1) < lHeight) {
	                int lPixel = pImage.getRGB(x, y + 1);
	                lImageOut.setRGB(x, y + 1, lPixel + (quantizationError * (5 / 16)));
	            }

	            if ((x + 1 < lWidth) && (y + 1 < lHeight)) {
	                int lPixel = pImage.getRGB(x + 1, y + 1);
	                lImageOut.setRGB(x + 1, y + 1, lPixel + (quantizationError * (1 / 16)));
	            }
	            // End of Floyd-Steinberg dithering
	        }
	    }

	    return lImageOut;
	}

	/**
	 * @param pPalette to load color model from
	 * @return {@link java.awt.image.IndexColorModel} Color model initialized using pPalette colors
	 */
	private static IndexColorModel paletteToColorModel(Color[] pPalette) {
	    int lSize = pPalette.length;

	    // Getting color component for each palette color
	    byte[] lReds = new byte[lSize];
	    byte[] lGreens = new byte[lSize];
	    byte[] lBlues = new byte[lSize];

	    for (int i = 0; i < lSize; i++) {
	        Color lColor = pPalette[i];
	        lReds[i] = (byte) lColor.getRed();
	        lGreens[i] = (byte) lColor.getGreen();
	        lBlues[i] = (byte) lColor.getBlue();
	    }

	    return new IndexColorModel(4, lSize, lReds, lGreens, lBlues);
	}

	/**
	 * @param pColor   Color to approximate
	 * @param pPalette Color palette to use for quantization
	 * @return {@link java.awt.Color} nearest from pColor value took in pPalette
	 */
	private static Color getNearestColor(int pColor, Color[] pPalette) {
	    Color lNearestColor = null;
	    double lNearestDistance = Integer.MAX_VALUE;
	    double lTempDist;
	    for (Color lColor : pPalette) {
	        Color lRgb = new Color(pColor);
	        lTempDist = distance(lRgb.getRed(), lRgb.getGreen(), lRgb.getBlue(), lColor.getRed(), lColor.getGreen(), lColor.getBlue());
	        if (lTempDist < lNearestDistance) {
	            lNearestDistance = lTempDist;
	            lNearestColor = lColor;
	        }
	    }
	    return lNearestColor;
	}

	/**
	 * @return Distance between 2 pixels color channels.
	 */
	private static double distance(int pR1, int pG1, int pB1, int pR2, int pG2, int pB2) {
	    double lDist = Math.pow(pR1 - pR2, 2) + Math.pow(pG1 - pG2, 2) + Math.pow(pB1 - pB2, 2);
	    return Math.sqrt(lDist);
	}
}
