
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
public class BitPlaneSlicing {
    public BufferedImage img = null;
    
    public BitPlaneSlicing(BufferedImage image,int BitPos){
        this.img = image;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int S = original.getRed();
                //CONVERT 8 bit byte to binary string
                String s1 = String.format("%8s", Integer.toBinaryString(S)).replace(' ', '0'); 
                //To check if bit is 1 or 0 and set value 255 or 0 to get binary image
                if ('1' == s1.charAt(BitPos)) {
                    S = 255;
                } else{
                    S = 0;
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
