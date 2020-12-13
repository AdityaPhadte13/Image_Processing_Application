
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
public class GrayScaleErosion {

    public BufferedImage img = null;
    public BufferedImage Simg = null;

    public BufferedImage getImg() {
        return Simg;
    }

    public GrayScaleErosion(BufferedImage image) {
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        int mask[][] = {{0, 1, 0},
        {1, 1, 1},
        {0, 1, 0}};
        int temp, min;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                //calaculate pixel value through box filter
                min = 255;
                for (int k = 0, x = i - 1; x < i + 2; x++, k++) {
                    for (int l = 0, y = j - 1; y < j + 2; y++, l++) {
                        //Get original pixel colour
                        Color original = new Color(img.getRGB(x, y));
                        temp = original.getRed() - mask[k][l];
                        if (min > temp) {
                            min = temp;
                        }
                    }
                }
                min = (min < 0) ? 0 : min;
                Color newColor = new Color(min, min, min);
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
            }
        }
    }

}
