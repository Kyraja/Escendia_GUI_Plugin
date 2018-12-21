package net.escendia.gui.model.network.out.elements;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.components.Element;

/**
 * Packet: Send a element which will be added to the gui
 */
public class Add extends  PacketElement{
    public Element element;

    public Add(Element element){
        super();
        this.element = element;
        getJsonObject().add(GlobalScope.ELEMENT, element.toJson());
    }


    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_COMPONENT_ADD;
    }
}
