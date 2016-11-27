package ua.agileengine.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.Rectangle;
import ua.agileengine.exceptions.ImagesDimensionsMismatch;

/**
 * The class represents an object which compares to images 
 * and finds and marks mismatches by red rectangles.
 */
public class ImageComparator {
    
    private final int IGNORED_DISTANCE_BETWEEN_RECTANGLES = 10;
    private List<Rectangle> rectangles;
    private RGBComparator rgbComparator;
    
    public ImageComparator() {
        rgbComparator = new RGBComparator();
        rectangles = new LinkedList<>();
    }
    
    /**
     * The method compares two images, pixel by pixel.
     * 
     * @param firstImage is the BufferedImage object
     * @param secondImage is the BufferedImage object to compare
     * @return - The BufferedImage object as result of comparison
     * @throws ImagesDimensionsMismatch - If the images has different size. 
     */
    public BufferedImage getImageWithDifferences(BufferedImage firstImage, BufferedImage secondImage, 
                                                float criticalPixelsPercentDiff) throws ImagesDimensionsMismatch {
        int firstImageWidht = firstImage.getWidth();
        int firstImageHeight = firstImage.getHeight();
        int secondImageWidht = secondImage.getWidth();
        int secondImageHeight = secondImage.getHeight(); 
        
        if (firstImageWidht == secondImageWidht 
                || firstImageHeight == secondImageHeight) {
            BufferedImage outImage = secondImage;
            for (int x = 0; x < firstImageWidht; x++) {
                for (int y = 0; y < firstImageHeight; y++) {
                    if (rgbComparator.isPixelsDifferent(firstImage.getRGB(x, y), secondImage.getRGB(x, y),
                                                        criticalPixelsPercentDiff)) {
                        applyChangesToRectangles(x, y);
                    }
                } 
            }
            wrapDifferencesToRectangles(outImage, rectangles);
            return outImage;
        } else {
            throw new ImagesDimensionsMismatch("The images has different size (count of pixels).");
        }
    }
   
    /**
     * The method modifies List<Rectangle> rectangles. 
     * The new Rectangle object is created if there isn't any rectangles aroud
     * passed coordinates. Otherwise the existing rectangle is expanded.
     * 
     * @param x
     * @param y 
     */
    private void applyChangesToRectangles (int x, int y) {
        Rectangle rectangle = findRectangleAround(x, y);
        if (rectangle == null) {
            rectangles.add(new Rectangle(x, y, 1, 1));
        } else {
           expandRectangle(rectangle, x, y);    
        }
    }
    /**
     * The Method finds any rectangle in the list which has distance from passed
     * params not more then allowed ignored distance between two mismaches.
     * 
     * @param x
     * @param y
     * @return - Rectangle
     */
    private Rectangle findRectangleAround (int x, int y) {
        for (Rectangle rectangle: rectangles) {
            if (x > rectangle.x - IGNORED_DISTANCE_BETWEEN_RECTANGLES 
                    && y > rectangle.y - IGNORED_DISTANCE_BETWEEN_RECTANGLES 
                    && x < rectangle.x + rectangle.width + IGNORED_DISTANCE_BETWEEN_RECTANGLES 
                    && y < rectangle.y + rectangle.height + IGNORED_DISTANCE_BETWEEN_RECTANGLES) {
                return rectangle;
            }
        }
        return null;
    }
    
    /**
     * This is an algorithm to increase the width and height of the rectangle
     * to wrap passed x and y coordinates as params.
     * 
     * @param rectangle java.awt.Rectangle
     * @param x
     * @param y 
     */
    private void expandRectangle(Rectangle rectangle, int x, int y) {
        if (x > rectangle.x + rectangle.width) {
                rectangle.width += x - rectangle.x - rectangle.width + 1;
        } else if (x < rectangle.x) {
                rectangle.width += Math.abs(rectangle.x - x) + 1;
                rectangle.x = x;
        }
        if (y > rectangle.y + rectangle.height) {
                rectangle.height += y  - rectangle.y - rectangle.height + 1;
        } else if (y < rectangle.y) {
                rectangle.height += Math.abs(rectangle.y - y) + 1;
                rectangle.y = y;
        }
    }
    
    /**
     * The method draws rectangles which are wraps mismatches.
     * 
     * @param bufferedImage - outImage
     * @param rectangles - rectangles to mark mismatches
     */
    private void wrapDifferencesToRectangles(BufferedImage bufferedImage, List<Rectangle> rectangles) {
        Graphics2D graph = bufferedImage.createGraphics();
        for (Rectangle rectangle : rectangles) {
                graph.setColor(Color.RED);
                graph.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        graph.dispose();
    }
}
