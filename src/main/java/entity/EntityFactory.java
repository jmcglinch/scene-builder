package entity;

import constants.Entity;
import org.w3c.dom.Element;

import java.awt.*;

public class EntityFactory {

    public static BaseEntity getEntity(Element eElement)  {

        String tagName = eElement.getTagName();

        BaseEntity entity;

        Entity entityEnum = Entity.valueOf(tagName.toUpperCase());

        switch (entityEnum) {
            case IMAGE:
                int x = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                int y = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
                String name =  eElement.getElementsByTagName("name").item(0).getTextContent();
                int alpha = Integer.parseInt(eElement.getElementsByTagName("alpha").item(0).getTextContent());
                int scale = Integer.parseInt(eElement.getElementsByTagName("scale").item(0).getTextContent());
                entity = new Image(x, y, name, alpha, scale);
                break;
            case ELLIPSE:
                int eX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
                int eY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
                String stringColor = eElement.getElementsByTagName("color").item(0).getTextContent();
                Color color = Color.WHITE;
                try {
                    color = (Color) Color.class.getField(stringColor.toUpperCase()).get(null);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                }
                int opacity = Integer.parseInt(eElement.getElementsByTagName("opacity").item(0).getTextContent());
                int radius = Integer.parseInt(eElement.getElementsByTagName("radius").item(0).getTextContent());
                entity = new Ellipse(eX, eY, opacity, color, radius);
                break;
            default:
                entity = new BaseEntity(0, 0) {
                    @Override
                    public void paint(Graphics g) { }
                };
        }
        return entity;
    }

}
