package timeline;

import entity.Ellipse;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.ease.Spline;

class EllipseTimeline extends BaseTimeline {

    public EllipseTimeline(Ellipse ellipse) {
        Timeline timeline = new Timeline(ellipse);
        timeline.addPropertyToInterpolate("x", (int) ellipse.getX(), 200);
        timeline.addPropertyToInterpolate("y", (int) ellipse.getY(), 0);
        timeline.addPropertyToInterpolate("opacity",  ellipse.getOpacity(), 0.0f);
        timeline.addPropertyToInterpolate("radius", ellipse.getRadius(), 10.0f);
        timeline.setDuration(1000);
        timeline.setEase(new Spline(0.4f));
        this.timeline = timeline;

    }
}
