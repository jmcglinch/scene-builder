package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Image extends BaseEntity {
    private final String name;

    private float alpha;

    private double scale;

    private BufferedImage img = null;

    private final Set<Ellipse> ellipses = new HashSet<>();

    public void setScale(double scale) {
        this.scale = scale;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Set<Ellipse> getEllipses() {
        return this.ellipses;
    }

    public void setEllipses(Set<Ellipse> ellipses) {
        for (Ellipse ellipse: ellipses
             ) {
            this.ellipses.add(ellipse.copy(ellipse));
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public Image(float x, float y, String name, float alpha, double scale) {
        super(x, y);
        this.name = name;

        this.alpha = alpha;
        this.scale = scale;
        this.setImg(loadImage(this.name));

    }

    private double getScale() {
        return scale;
    }

    public static BufferedImage loadImage(String name) {
        String imgFileName = "images/" + name + ".png";
        URL url = Image.class.getClassLoader().getResource(imgFileName);
        BufferedImage img = null;
        try {
            img =  ImageIO.read(Objects.requireNonNull(url));
        } catch (Exception ignored) {
        }
        return img;

    }


    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        if (this.img != null) {
            int w = this.img.getWidth(null);
            int h = this.img.getHeight(null);
            double scale = this.scale;
            g2d.drawImage(this.img,
                    (int) this.x,
                    (int) this.y,
                    (int) (w * scale),
                    (int) (h * scale),
                    null);
            g2d.dispose();
        }
    }

    public Image copy(Image image) {
        return new Image(getX(),
                getY(),
                getName(),
                getAlpha(),
                getScale());
    }
}
