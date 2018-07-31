package timeline;

import constants.Entity;
import entity.BaseEntity;
import entity.Ellipse;
import entity.Image;
import org.pushingpixels.trident.Timeline;

public class TimelineFactory {
    public static Timeline getTimeline(BaseEntity entity, String tagName) {
        Timeline timeline;

        Entity entityEnum = Entity.valueOf(tagName.toUpperCase());

        switch (entityEnum) {
            case IMAGE:
                timeline = new ImageTimeline((Image) entity).getTimeline();
                break;
            case ELLIPSE:
                timeline = new EllipseTimeline((Ellipse) entity).getTimeline();
                break;
            default:
                timeline = new BaseTimeline().getTimeline();
        }
        return timeline;
    }
}
