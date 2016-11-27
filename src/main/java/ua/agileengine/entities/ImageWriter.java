package ua.agileengine.entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The class represents an object which saves images in png.
 */
public class ImageWriter {
    public void saveImage(BufferedImage image) {
        try {
            ImageIO.write(image, "png", new File("comparison_result.png"));
            System.out.println("The file comparison_result.png was saved to the directory where application located.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
