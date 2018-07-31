The aquarium app demos a generic approach to building visual scenes with an XML file. The idea would be to stream an XML-based configuration to an app in order to create and update data visualizations or other animations. The key components include:

 * the XML resource
 * Entity objects with Graphics2D paint method
 * Timeline and scenario generators using Trident library
 * the scene component which creates the JPanel object and invokes scenario
 