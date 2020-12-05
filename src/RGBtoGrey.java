
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
public class RGBtoGrey {

    public BufferedImage img = null;

    public RGBtoGrey(BufferedImage image) {
        this.img = image;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int newValue = (original.getRed() + original.getBlue() + original.getGreen()) / 3;  //Average Calulation
//                int newValue =  (int)(0.30*original.getRed() + 0.11*original.getBlue() + 0.59*original.getGreen())/3;     //Weighted Calculation
                Color newColor = new Color(newValue, newValue, newValue);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public BufferedImage getImg() {
        return img;
    }
}
