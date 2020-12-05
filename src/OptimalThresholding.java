
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
public class OptimalThresholding {

    public BufferedImage img = null;
    public int finalThreshold;

    public OptimalThresholding(BufferedImage image) {
        this.img = image;
        final double delta = 0.0001;
        double t0;
        long NM = img.getHeight() * img.getWidth();
        long sum = 0;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color original = new Color(img.getRGB(i, j));
                int intensity = original.getRed();
                sum = sum + intensity;
            }
        }

        t0 = sum / (double)NM;
        System.out.println("t0 = "+ t0 + '\n');
        double tk = t0, tk_1 = t0;
        double u1, u2;
        long CountOmega1 = 0, CountOmega2 = 0;
        int k = 0;
        do {
            CountOmega1 = 0; 
            CountOmega2 = 0;
            long SumOmega1 =0 , SumOmega2 =0;
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color original = new Color(img.getRGB(i, j));
                    int intensity = original.getRed();
                    if ((double)intensity >= tk) {
                        SumOmega1 = SumOmega1 + intensity;
                        CountOmega1++;
                    } else {
                        SumOmega2 = SumOmega2 + intensity;
                        CountOmega2++;
                    }
                }
            }
            u1 = SumOmega1/(double)CountOmega1;
            u2 = SumOmega2/(double)CountOmega2;
            tk_1 = tk;
            tk = (u1+u2)/2;
            System.out.println("For K = "+ k);
            System.out.println("u1 = " + u1);
            System.out.println("u2 = " + u2);
            System.out.println("tk = " + tk + '\n');
            k++;
        } while (Math.abs(tk - tk_1) > delta);
        System.out.println("Final Threshold = "+ tk);
        finalThreshold = (int) Math.floor(tk);
    }

    public int getFinalThreshold() {
        return finalThreshold;
    }


}
