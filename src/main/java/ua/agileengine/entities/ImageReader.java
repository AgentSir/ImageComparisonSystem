package ua.agileengine.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;

/**
 * The class represents an object which reads images.
 */
public class ImageReader {
    /**
     * The method read an image and returns BufferedImage object.
     * 
     * @param pathToImage - full path to the file.
     * @return BufferedImage object
     * @throws IOException 
     */    
    public BufferedImage getImage(String pathToImage) throws IOException {
        Path path = Paths.get(pathToImage);
        String mimeType = Files.probeContentType(path);
        BufferedImage bufferedImage = null;
        if (Files.exists(path) && isImage(mimeType)) {
            bufferedImage = ImageIO.read(Files.newInputStream(path,  StandardOpenOption.READ));
            System.out.println("The file uploaded successfully.");
        } else {
            throw new IOException("The path to the file is incorrect.");
        }
        return bufferedImage;
    }
    /**
     * The method checks mime type of file and returns true if it is an image. 
     * Otherwise throws an IOException.
     * 
     * @param mimeType 
     * @return TRUE or throws an exception
     * @throws IOException 
     */
    private boolean isImage(String mimeType) throws IOException {
        if (mimeType.substring(0,5).equalsIgnoreCase("image")) {
            return true;
        } else {
            throw new IOException("The file isn't an image.");
        }
    }
}
