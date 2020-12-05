
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
public class IntensitySlicing {
    public BufferedImage img = null;
    
    //INTENSITY SLICING WITH RETAINING BACKGROUND
    public IntensitySlicing(BufferedImage image, int A, int B, int C){
        this.img = image;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int S = original.getRed();
                if(S >= A && S <= B){
                    S = C;
                }
                Color newColor = new Color(S, S, S);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
    }
    
    //INTENSITY SLICING WITH LOWERING BACKGROUND
    public IntensitySlicing(BufferedImage image, int A, int B, int C, int D){
        this.img = image;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int S = original.getRed();
                if(S >= A && S <= B){
                    S = C;
                } else{
                    S = D;
                }
                Color newColor = new Color(S, S, S);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
    }
    
    public BufferedImage getImg(){
        return img;
    }
}
