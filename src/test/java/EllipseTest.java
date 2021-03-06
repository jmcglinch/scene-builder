/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import entity.Ellipse;
import junit.framework.TestCase;

import java.awt.*;


public class EllipseTest extends TestCase {
    private Ellipse ellipse;
    @Override
    protected void setUp() throws Exception {
        ellipse = new Ellipse(
                1.0f, 1.0f, 1.0f, Color.WHITE, 1.15f
        );
    }
    public void testEllipseGetRadius() {
        assertEquals(ellipse.getRadius(), 1.15f);
    }
}
