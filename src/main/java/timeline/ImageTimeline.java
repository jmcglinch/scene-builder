package timeline;

import entity.Image;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.ease.Spline;

class ImageTimeline extends BaseTimeline {

    public ImageTimeline(Image image) {
        Timeline timeline = new Timeline(image);
        timeline.addPropertyToInterpolate("x", image.getX(), image.getX() + 200);
        timeline.addPropertyToInterpolate("y", image.getY(), image.getY() - 10);
        timeline.addPropertyToInterpolate("alpha", image.getAlpha(), image.getAlpha() - 1);
        timeline.setDuration(3000);
        timeline.setEase(new Spline(0.4f));
        this.timeline = timeline;

    }
}
