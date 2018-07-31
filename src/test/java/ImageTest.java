/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import junit.framework.TestCase;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import entity.Ellipse;
import entity.Image;


public class ImageTest extends TestCase {
    private Image image;
    @Override
    protected void setUp() throws Exception {
        image = new Image(
                1.0f, 1.0f, "blue-marlin", 1.0f, .15
        );
    }
    public void testImageHasRelated() {
        Set<Ellipse> bubbles = new HashSet<>();
        image.setEllipses(bubbles);
        assertNotSame(bubbles, image.getEllipses());//because copied
    }
    public void testImageLoadImage() {
        BufferedImage img = Image.loadImage(image.getName());
        image.setImg(img);
        assertEquals(img, image.getImg());
    }
}