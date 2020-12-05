
import java.awt.image.BufferedImage;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aditya
 */
public class Histogram {

    public int[] Hist; //Histogram Array
    public BufferedImage img = null; //Original Image
    public BufferedImage HistImg = null; //Histogram Image

    public int[] getHist() {
        return Hist;
    }

    public BufferedImage getHistImg() {
        int max = 0;
        for (int i = 0; i < Hist.length; i++) {
            if (Hist[i] > max) {
                max = Hist[i];
            }
        }

        this.HistImg = new BufferedImage(512, max + 100, img.getType());

        for (int i = 0; i < HistImg.getWidth(); i++) {
            for (int j = 0; j < HistImg.getHeight(); j++) {
                HistImg.setRGB(i, j, Color.WHITE.getRGB());
            }
        }

        for (int i = 128; i < Hist.length + 128; i++) {
            for (int j = Hist[i - 128]; j > 0; j--) {
                HistImg.setRGB(i, HistImg.getHeight() - j, Color.BLUE.getRGB());
            }
        }
        
        return HistImg;
    }

    public Histogram(BufferedImage transform) {
        this.img = transform;
        
        this.Hist = new int[256];
        for (int i = 0; i < Hist.length; i++) {
            this.Hist[i] = 0;
        }
        
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int col = original.getRed();
                ++Hist[col];
            }
        }
    }

}
