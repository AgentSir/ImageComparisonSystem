package ua.agileengine.entities;

/**
 * The class represents an object which compares two RGB values.
 *
 */
public class RGBComparator {
    
    private final int RGB_MAX_SATURATIONS = 255;
    
    /** 
     * The method compares two pixels by RGB values. 
     * The pixels are different if the difference between RGB values of them 
     * more then a value passed as param  - "criticalPercentDifference".
     * 
     * The RGB value consists of: 
     *     - red part that integrated into bits from 16 to 23
     *     - green part that integrated into bits from 8 to 15
     *     - blue part that integrated into bits from 0 to 7
     * 
     * @param firstPixel - The integer RGB value of the pixel
     * @param secondPixel - The integer RGB value of the pixel
     * @param criticalPercentDifference - The float value represents max difference in percentage
     * @return - TRUE or FALSE
     */
    public boolean isPixelsDifferent(int firstPixel, int secondPixel, float criticalPercentDifference) {
        
        int diffRed   = Math.abs((firstPixel >> 16 & 0xff) - (secondPixel >> 16 & 0xff));
        int diffGreen = Math.abs((firstPixel >> 8 & 0xff) - (secondPixel >> 8 & 0xff));
        int diffBlue  = Math.abs((firstPixel & 0xff) - (secondPixel & 0xff));
        
        float percentDiffRed = (float) diffRed / RGB_MAX_SATURATIONS * 100;
        float percentDiffGreen = (float) diffGreen / RGB_MAX_SATURATIONS * 100;
        float percentDiffBlue = (float) diffBlue / RGB_MAX_SATURATIONS * 100;
        
        float commonPercentDiff = (percentDiffRed + percentDiffGreen + percentDiffBlue) / 3;
        
        if (commonPercentDiff <= criticalPercentDifference) {
            return false;
        }
        return true;
    }
}
