package net.escendia.gui.view;

import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.network.in.element.Added;
import net.escendia.gui.model.network.out.elements.Add;
import net.escendia.gui.model.network.out.elements.PacketElement;
import net.escendia.gui.model.network.out.elements.Remove;
import net.escendia.gui.model.network.out.elements.Update;
import net.escendia.ioc.InversionOfControl;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EscendiaGui the open a gui to work with
 */
public class EscendiaGui {

    private final UUID guiUUID;
    private final UUID playerUUID;
    private ConcurrentHashMap<UUID, Element> elementList;
    private Element hoveredElement;
    private Element focusedElement;


    public EscendiaGui(UUID playerUUID){
        super();
        this.guiUUID =  UUID.fromString("00000000-0000-0000-0000-000000000000");
        this.playerUUID = playerUUID;
        this.elementList = new ConcurrentHashMap<>();
    }


    /*
    -------------- Getter Methods ---------------
     */


    public UUID getGuiUUID() {
        return guiUUID;
    }


    public ConcurrentHashMap<UUID, Element> getElementList() {
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
        InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new Add(element));
    }

    /**
     * removes the current gui
     * @param element
     */
    public void remove(Element element){
        if(elementList.get(element)==null){
            removeElement(element);
        }
        InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new Remove(element));
    }

    /**
     * Updates a element of the gui and sends it to the client.
     * If the element is new it will be send as "Add" and not as "updated"
     * @param element
     */
    public void update(Element element){
        if(getElement(element.getElementUUID())!=null){
            updateElement(element);
            InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(playerUUID, new Update(element));
        }else {
            addElement(element);
        }
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
        ------------- Updated Methods ------------
    */

    /**
     * Get an element from the elementList or the childrenelement of an element
     * @param element
     * @return
     */
    public Element getElement(UUID element) {
        for(UUID elementUUID : elementList.keySet()){
            if(element.equals(elementUUID)){
                return elementList.get(elementUUID);
            }else{
                Element childElement = elementList.get(elementUUID).getChildrenElement(element);
                if(childElement!=null)return childElement;
            }
        }
        return null;
    }

    /**
     * Removes the old element add puts the new one to the list or childrenlist
     * @param element
     */
    public void updateElement(Element element){
        for(UUID elementUUID : elementList.keySet()){
            if(element.getElementUUID().equals(elementUUID)){
                elementList.remove(elementUUID);
                elementList.put(element.getElementUUID(), element);
            }else{
               elementList.get(elementUUID).updateChildrenElement(element);
            }
        }
    }


    /**
     * Removes the old element add puts the new one to the list or childrenlist
     * @param element
     */
    public void removeElement(Element element){
        for(UUID elementUUID : elementList.keySet()){
            if(element.getElementUUID().equals(elementUUID)){
                elementList.remove(element);
            }else{
                elementList.get(elementUUID).removeChildrenElement(element);
            }
        }
    }

}
