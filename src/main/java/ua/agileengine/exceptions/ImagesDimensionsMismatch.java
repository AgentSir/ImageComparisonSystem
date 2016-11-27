package ua.agileengine.exceptions;

/**
 *  The class represents an exception object.
 *  It can be thrown out if two images has different size (count of pixels).
 */
public class ImagesDimensionsMismatch extends Exception {
    public ImagesDimensionsMismatch(String message) {
        super(message);
    }
}
