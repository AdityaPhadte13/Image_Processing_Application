
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
public class HistogramEqualization {

    public BufferedImage img = null;

    public BufferedImage getImg() {
        return img;
    }

    public HistogramEqualization(BufferedImage image, int Hist[]) {
        this.img = image;
        int Sk[] = new int[256];
        int MN = image.getHeight() * image.getWidth();
        double a = 0;
        
        for (int i = 0; i < Hist.length; i++) {
            a = a + (double) Hist[i] / (double) MN;
            Sk[i] = (int) Math.round(255 * a);
        }

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int S = original.getRed();
                S = Sk[S];
                img.setRGB(i, j, new Color(S, S, S).getRGB());
            }
        }
    }
}
