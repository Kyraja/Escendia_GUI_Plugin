package net.escendia.gui.model.network.out.elements;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.components.Element;

/**
 * Packet: Send a packet which will be removed (by UUID)
 */
public class Remove extends  PacketElement{
    public Element element;

    public Remove(Element element){
        super();
        this.element = element;
        getJsonObject().add(GlobalScope.ELEMENT, element.toJson());

    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_COMPONENT_REMOVE;
    }
}