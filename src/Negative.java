
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
public class Negative {
    
    public BufferedImage img = null;
    
    public Negative(BufferedImage image){
        this.img = image;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int newRed = 255 - original.getRed();
                int newBlue = 255 - original.getBlue();
                int newGreen = 255 - original.getGreen();
                Color newColor = new Color(newRed, newGreen, newBlue);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
    }
    
    public BufferedImage getImg(){
        return img;
    }
}
