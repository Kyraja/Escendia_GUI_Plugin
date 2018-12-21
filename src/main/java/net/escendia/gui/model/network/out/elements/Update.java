package net.escendia.gui.model.network.out.elements;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.components.Element;

/**
 * Packet: Updates all values of the Element and its children elements
 */
public class Update extends  PacketElement{
    public Element element;

    public Update(Element element){
        super();
        this.element = element;
        getJsonObject().add(GlobalScope.ELEMENT, element.toJson());
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_COMPONENT_UPDATE;
    }
}