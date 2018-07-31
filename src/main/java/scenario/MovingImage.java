package scenario;

import constants.Entity;
import entity.BaseEntity;
import entity.Ellipse;
import entity.EntityFactory;
import entity.Image;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.TimelineScenario;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import timeline.TimelineFactory;

import java.util.HashSet;
import java.util.Set;

public class MovingImage {
    private final Set<Image> images  =  new HashSet<>();
    private final TimelineScenario scenario = new TimelineScenario.Parallel();

    public TimelineScenario getScenario() {
        return scenario;
    }

    public Set<Image> getImages() {
        return images;
    }

    private void addTimeline(Timeline timeline) {
        this.scenario.addScenarioActor(timeline);
    }

    private Set<Ellipse> process(NodeList nodeList, String tagName, Set<Ellipse> collect) {
        Entity entityEnum = Entity.valueOf(tagName.toUpperCase());
        String ellipseConst = Entity.ELLIPSE.name().toLowerCase();
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                BaseEntity entity = EntityFactory.getEntity(eElement);

                switch(entityEnum) {
                    case IMAGE:
                        Image image = (Image) entity;
                        synchronized (this.images) {
                            this.images.add(image);
                        }
                        image.setEllipses(process(((Element) nNode).getElementsByTagName(ellipseConst),
                                ellipseConst, collect));
                        for (Ellipse ellipse: image.getEllipses()
                             ) {
                            this.addTimeline(TimelineFactory.getTimeline(ellipse, ellipseConst));
                        }
                        this.scenario.addScenarioActor(TimelineFactory.getTimeline(entity, tagName));
                        break;
                    case ELLIPSE:
                        collect.add((Ellipse) entity);
                        break;
                    default:
                        System.out.println("Entity not found.");
                }
            }
        }
        return collect;
    }

    public MovingImage(NodeList imageConfig) {
        this.process(imageConfig, "image", new HashSet<>());
    }
}
