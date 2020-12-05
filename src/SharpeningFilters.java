
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aditya
 */
public class SharpeningFilters {

    public BufferedImage img = null;
    public BufferedImage Simg = null;

    public BufferedImage getImg() {
        return Simg;
    }

    public int[] conv(int i, int j, int n, int[][] mask) {
        int a[] = {0, 0, 0};
        int sumr = 0, sumg = 0, sumb = 0;
        for (int k = 0, x = i - 1; x < i + 2; x++, k++) {
            for (int l = 0, y = j - 1; y < j + 2; y++, l++) {
                //Get original pixel colour
                Color original = new Color(img.getRGB(x, y));
                //add pixel to sum using appropriate wieght
                sumr = sumr + (original.getRed() * mask[k][l]);
                sumg = sumg + (original.getGreen() * mask[k][l]);
                sumb = sumb + (original.getBlue() * mask[k][l]);
            }
        }
        a[0] = Math.abs(Math.round(sumr / n));
        a[1] = Math.abs(Math.round(sumg / n));
        a[2] = Math.abs(Math.round(sumb / n));
        return a;

    }
}

class LaplacianFilter extends SharpeningFilters {

    public LaplacianFilter(BufferedImage image) {
        int laplaceFilterMask[][] = {
            {1, 1, 1},
            {1, -8, 1},
            {1, 1, 1}};
        
        int[] newColorArr;
        int n = 1;
        Color newColor;
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        int sumr = 0, sumg = 0, sumb = 0;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
//                Get convolution output of Laplacian filter                
                newColorArr = conv(i, j, n, laplaceFilterMask);
//                Cap the Values Greater than 255 to 255                
                sumr = (newColorArr[0] > 255) ? 255 : newColorArr[0];
                sumg = (newColorArr[1] > 255) ? 255 : newColorArr[1];
                sumb = (newColorArr[2] > 255) ? 255 : newColorArr[2];
//                Set the new pixel value
                newColor = new Color(sumr, sumg, sumb);
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
            }
        }
    }

}

class PrewittFilter extends SharpeningFilters {

    public PrewittFilter(BufferedImage image) {
        int PrewittFilterXMask[][] = {
            {-1, -1, -1}, 
            {0, 0, 0}, 
            {1, 1, 1}};
        
        int PrewittFilterYMask[][] = {
            {-1, 0, 1}, 
            {-1, 0, 1}, 
            {-1, 0, 1}};
        
        Color newColor;
        int[] newColorArrX, newColorArrY;
        int n = 1;
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
//                Get X and Y Mask convolution outputs of Prewitt filter                
                newColorArrX = conv(i, j, n, PrewittFilterXMask);
                newColorArrY = conv(i, j, n, PrewittFilterYMask);
//                Combine X and Y Mask convolution outputs of Prewitt filter                
                int sumr = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[0], 2) + Math.pow(newColorArrY[0], 2)));
                int sumg = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[1], 2) + Math.pow(newColorArrY[1], 2)));
                int sumb = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[2], 2) + Math.pow(newColorArrY[2], 2)));
//                Cap the Values Greater than 255 to 255                
                sumr = (sumr > 255) ? 255 : sumr;
                sumg = (sumg > 255) ? 255 : sumg;
                sumb = (sumb > 255) ? 255 : sumb;
//                Set the new pixel value                
                newColor = new Color(sumr, sumg, sumb);
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
            }
        }
    }
}

class SobelFilter extends SharpeningFilters {

    public SobelFilter(BufferedImage image) {
        int SobelFilterXMask[][] = {
            {-1, -2, -1}, 
            {0, 0, 0}, 
            {1, 2, 1}};
        
        int SobelFilterYMask[][] = {
            {-1, 0, 1}, 
            {-2, 0, 2}, 
            {-1, 0, 1}};
        
        Color newColor;
        int[] newColorArrX, newColorArrY;
        int n = 1;
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
//                Get X and Y Mask convolution outputs of sobel filter
                newColorArrX = conv(i, j, n, SobelFilterXMask);
                newColorArrY = conv(i, j, n, SobelFilterYMask);
//                Combine X and Y Mask convolution outputs of sobel filter
                int sumr = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[0], 2) + Math.pow(newColorArrY[0], 2)));
                int sumg = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[1], 2) + Math.pow(newColorArrY[1], 2)));
                int sumb = (int) Math.round(Math.sqrt(Math.pow(newColorArrX[2], 2) + Math.pow(newColorArrY[2], 2)));
//                Cap the Values Greater than 255 to 255
                sumr = (sumr > 255) ? 255 : sumr;
                sumg = (sumg > 255) ? 255 : sumg;
                sumb = (sumb > 255) ? 255 : sumb;
//                Set the new pixel value
                newColor = new Color(sumr, sumg, sumb);
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
            }
        }
    }
}
