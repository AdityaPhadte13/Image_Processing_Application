
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aditya
 */
//MAIN PARENT CLASS
public class SmoothingFilters {

    public BufferedImage img = null;
    public BufferedImage Simg = null;

    public BufferedImage getImg() {
        return Simg;
    }
}

//BOX FILTER
class BoxFilter extends SmoothingFilters {

    public BoxFilter(BufferedImage image) {
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        int sumr = 0, sumg = 0, sumb = 0;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                //calaculate pixel value through box filter
                for (int x = i - 1; x < i + 2; x++) {
                    for (int y = j - 1; y < j + 2; y++) {
                        //Get original pixel colour
                        Color original = new Color(img.getRGB(x, y));
                        //add pixel to sum
                        sumr = sumr + original.getRed();
                        sumg = sumg + original.getGreen();
                        sumb = sumb + original.getBlue();
                    }
                }
                //add new value in new colour
                Color newColor = new Color(Math.round(sumr / 9), Math.round(sumg / 9), Math.round(sumb / 9));
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
                sumr = 0;
                sumg = 0;
                sumb = 0;
            }
        }
    }
}

//WEIGHTED AVERAGING FILTER
class WeightedAvgFilter extends SmoothingFilters {

    public WeightedAvgFilter(BufferedImage image) {
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        int sumr = 0, sumg = 0, sumb = 0;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                //calaculate pixel value through box filter
                for (int x = i - 1; x < i + 2; x++) {
                    for (int y = j - 1; y < j + 2; y++) {
                        //Get original pixel colour
                        Color original = new Color(img.getRGB(x, y));
                        //add pixel to sum
                        if (x == i && y == j) {
                            //For current pixel weight is 4
                            sumr = sumr + (4 * original.getRed());
                            sumg = sumg + (4 * original.getGreen());
                            sumb = sumb + (4 * original.getBlue());
                        } else if ((x == i - 1 && y == j - 1) //top left corner
                                || (x == i - 1 && y == j + 1 //top right corner
                                || (x == i + 1 && y == j - 1)) //bottom left corner
                                || (x == i + 1 && y == j + 1)) { //bottom right corner
                            //For Diagonal neighbours weight is 1
                            sumr = sumr + original.getRed();
                            sumg = sumg + original.getGreen();
                            sumb = sumb + original.getBlue();
                        } else {
                            //For 4-neighbours weight is 2
                            sumr = sumr + (2 * original.getRed());
                            sumg = sumg + (2 * original.getGreen());
                            sumb = sumb + (2 * original.getBlue());
                        }
                    }
                }
                //add new value in new colour
                Color newColor = new Color(Math.round(sumr / 16), Math.round(sumg / 16), Math.round(sumb / 16));
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
                //reset sum to zero
                sumr = 0;
                sumg = 0;
                sumb = 0;
            }
        }
    }
}

//MEDIAN FILTER
class MedianFilter extends SmoothingFilters {

    public MedianFilter(BufferedImage image) {
        this.img = image;
        this.Simg = new BufferedImage(image.getWidth() - 2, image.getHeight() - 2, image.getType());
        List<Integer> listr = new ArrayList<>();
        List<Integer> listg = new ArrayList<>();
        List<Integer> listb = new ArrayList<>();
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                //calaculate pixel value through box filter
                for (int x = i - 1; x < i + 2; x++) {
                    for (int y = j - 1; y < j + 2; y++) {
                        //Get original pixel colour
                        Color original = new Color(img.getRGB(x, y));
                        //add pixel to to list
                        listr.add(original.getRed());
                        listg.add(original.getGreen());
                        listb.add(original.getBlue());
                    }
                }
                //Sort Lists 
                sort(listr);
                sort(listg);
                sort(listb);
                //add new value in new colour
                Color newColor = new Color(listr.get(4), listg.get(4), listb.get(4));
                Simg.setRGB(i - 1, j - 1, newColor.getRGB());
                //Remove all elements from list
                listr.clear();
                listg.clear();
                listb.clear();
            }
        }
    }
}
