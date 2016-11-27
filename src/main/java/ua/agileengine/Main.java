package ua.agileengine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import ua.agileengine.entities.ImageComparator;
import ua.agileengine.entities.ImageReader;
import ua.agileengine.entities.ImageWriter;
import ua.agileengine.entities.UserInterface;

/**
 * The main class.
 * The application was designed to compare two images. During comparison, pixles by pixel
 * will be compared and all mismatches will be wrapped into red rectangles.
 */
public class Main {
    
    final static float CRITICAL_PERCENT_DIFFERENCE = 10.0f;

    public static void main(String[] args) {
        // UI to communicate with the user.
        UserInterface userInterface = new UserInterface();
        // create needed objects
        ImageReader imageReader = new ImageReader();
        ImageComparator comparator = new ImageComparator();
        ImageWriter imageWriter = new ImageWriter();
        try {
            // read images
            BufferedImage firstBufferedImage = imageReader.getImage(userInterface.askPathToFile());
            BufferedImage secondBufferedImage = imageReader.getImage(userInterface.askPathToFile());
            // compare images
            BufferedImage resultBufferedImage = comparator.getImageWithDifferences(firstBufferedImage, secondBufferedImage,
                                            CRITICAL_PERCENT_DIFFERENCE);
            // save results
            imageWriter.saveImage(resultBufferedImage);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Exit.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exit.");
        }
    }
}
