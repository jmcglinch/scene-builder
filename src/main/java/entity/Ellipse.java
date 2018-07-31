package entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends BaseEntity{

    private float opacity;

    private Color color;

    private float radius;

    public Ellipse(float x, float y, float opacity, Color color, float radius) {
        super(x, y);
        this.opacity = opacity;
        this.color = color;
        this.radius = radius;
    }

    public Ellipse copy(Ellipse ellipse) {
        return new Ellipse(
                ellipse.getX(),
                ellipse.getY(),
                ellipse.getOpacity(),
                ellipse.getColor(),
                ellipse.getRadius());
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getOpacity() {
        return opacity;
    }

    private Color getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.SrcOver.derive(this.opacity));
        g2d.setColor(this.color);
        g2d.fill(new Ellipse2D.Float(this.x - this.radius, this.y - this.radius, 2 * radius,
                2 * radius));
        g2d.dispose();
    }
}
