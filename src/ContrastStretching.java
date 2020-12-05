
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
public class ContrastStretching {
    public BufferedImage img = null;
    
    public ContrastStretching(BufferedImage image){
        this.img = image;
        int rmin = 255, rmax = 0, gmin = 255, gmax = 0, bmin = 255, bmax = 0;
        int rS = 0, gS = 0, bS = 0,S1 = 0, S2 = 255;
        
        //GET rmin,rmax,gmin,gmax,bmin and bmax
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                if (rmin > original.getRed()) {
                    rmin =  original.getRed();
                }
                
                if (gmin > original.getGreen()) {
                    gmin =  original.getGreen();
                }
                
                if (bmin > original.getBlue()) {
                    bmin =  original.getBlue();
                }
                
                if (rmax < original.getRed()) {
                    rmax =  original.getRed();
                }
                
                if (gmax < original.getGreen()) {
                    gmax =  original.getGreen();
                }
                
                if (bmax < original.getBlue()) {
                    bmax =  original.getBlue();
                }
            }
        }
        
        //APPLY CONTRAST STRETCHING
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                //GET CONTRAST STRETCHED RED, GREEN AND BLUE VALUES
                //FORMULA: S = (S1 + (((S2 - S1)*(r - r1))/(r2 - r1)))
                rS = Math.round((S1 + (((S2 - S1)*(original.getRed() - rmin))/(rmax - rmin))));
                gS = Math.round((S1 + (((S2 - S1)*(original.getGreen() - gmin))/(gmax - gmin))));
                bS = Math.round((S1 + (((S2 - S1)*(original.getBlue() - bmin))/(bmax - bmin))));
                Color newColor = new Color(rS, gS, bS);
                img.setRGB(i, j, newColor.getRGB());
            }
        }
    }
    
    
    public BufferedImage getImg(){
        return img;
    }
}
