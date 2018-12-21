package net.escendia.gui.view;

import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.network.out.PacketElement;
import net.escendia.ioc.InversionOfControl;

import java.util.HashMap;
import java.util.UUID;

/**
 * EscendiaGui the open a gui to work with
 */
public class EscendiaGui {

    private final UUID guiUUID;
    private final UUID playerUUID;
    private HashMap<UUID, Element> elementList;
    private Element hoveredElement;
    private Element focusedElement;


    public EscendiaGui(UUID playerUUID){
        super();
        this.guiUUID =  UUID.fromString("00000000-0000-0000-0000-000000000000");
        this.playerUUID = playerUUID;
        this.elementList = new HashMap<>();
    }


    /*
    -------------- Getter Methods ---------------
     */


    public UUID getGuiUUID() {
        return guiUUID;
    }


    public HashMap<UUID, Element> getElementList() {
        return elementList;
    }

    public Element getHoverElement() {
        return hoveredElement;
    }

    public Element getFocusedElement() {
        return this.focusedElement;
    }
    /*
    -------------- Setter Methods ---------------
     */


    public void addElement(Element element) {
        elementList.put(element.getElementUUID(), element);
        InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new PacketElement.Added(element));
    }

    public void remove(Element element){
        if(elementList.get(element)!=null){
            elementList.remove(element);
            InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new PacketElement.Removed(element));
        }
    }

    public void update(Element element){
        InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new PacketElement.Updated(element));
    }


    /**
     * Set the hovered Element
     * @param hoveredElement
     */
    public void setHoveredElement(Element hoveredElement){
        this.hoveredElement = hoveredElement;
    }

    /**
     * Method to set the focused Element
     * @param focusedElement
     */
    public void setFocusedElement(Element focusedElement) {
        this.focusedElement = focusedElement;
    }
    /*
        ------------- Update Methods ------------
    */

    public Element getElement(UUID elementUUID) {

        for(UUID element : elementList.keySet()){
            if(element == elementUUID){
                return elementList.get(element);
            }else{
                Element childElement = elementList.get(element).getChildrenElement(elementUUID);
                if(childElement!=null)return childElement;
            }
        }
        return null;
    }

}
