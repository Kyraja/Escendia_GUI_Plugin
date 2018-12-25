package net.escendia.gui.model.components;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.escendia.gui.controll.GUIService;
import net.escendia.gui.model.factories.ElementDeserializer;
import net.escendia.gui.model.factories.FactoryElement;
import net.escendia.gui.model.factories.FormDeserializer;
import net.escendia.gui.model.form.Form;
import net.escendia.gui.model.form.style.impl.RectangleStandard;
import net.escendia.gui.model.gui.Visibility;
import net.escendia.ioc.InversionOfControl;

import java.util.HashMap;
import java.util.UUID;

public class Element implements FactoryElement<Element> {

    private UUID elementUUID = UUID.randomUUID();
    private HashMap<UUID, Element> childrenElements = new HashMap<>();
    private Visibility visibility = Visibility.VISIBLE;
    private Form form = new RectangleStandard();
    private ElementStatus elementStatus = ElementStatus.NORMAL;
    private String elementClass = getClass().toString();

    private boolean mouseOverLastUpdate = false;

    public Element(){
    }


    public Element(UUID elementUUID){
        this.elementUUID = elementUUID;
    }

    public Element(Form form){
        this.form = form;
    }

    public Element(UUID elementUUID, Form form){
        this.elementUUID = elementUUID;
        this.form = form;
    }

    /*
    ---------- Getter Methods --------------
     */
    public UUID getElementUUID() {
        return elementUUID;
    }

    public HashMap<UUID, Element> getChildrenElements() {
        return childrenElements;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Form getForm(){
        return form;
    }

    public ElementStatus getElementStatus() {
        return this.elementStatus;
    }

    /**
     * Get a children element (recursive)
     * @param element
     * @return
     */
    public Element getChildrenElement(UUID element) {
        for(UUID elementUUID : childrenElements.keySet()){
            if(elementUUID.equals(element)){
                return childrenElements.get(elementUUID);
            }else{
                Element childElement = childrenElements.get(elementUUID).getChildrenElement(element);
                if(childElement!=null)return childElement;
            }
        }
        return null;
    }

    /**
     * Updates a children element (recursive)
     * @param element
     */
    public void updateChildrenElement(Element element){
        for(UUID elementUUID : childrenElements.keySet()) {
            if (element.getElementUUID().equals(elementUUID)) {
                childrenElements.remove(elementUUID);
                childrenElements.put(element.getElementUUID(), element);
                return;
            } else {
                childrenElements.get(elementUUID).updateChildrenElement(element);
            }
        }
    }

    /**
     * Removes the children element (recursive)
     * @param element
     */
    public void removeChildrenElement(Element element) {
        for(UUID elementUUID : childrenElements.keySet()) {
            if (element.getElementUUID().equals(elementUUID)) {
                childrenElements.remove(elementUUID);
                return;
            } else {
                childrenElements.get(elementUUID).removeChildrenElement(element);
            }
        }
    }

    public String getElementClassAsString() {
        return elementClass;
    }

    /*
    ---------- Setter Methods --------------
    */

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setForm(Form form){this.form = form;}

    public void addChildrenElement(Element element){
        this.childrenElements.put(element.getElementUUID(), element);
    }


    /**
     * Sets a new ElementUUID to the element
     * @param elementUUID
     */
    public void setElementUUID(UUID elementUUID) {
        this.elementUUID = elementUUID;
    }
    /*
    ---------- Meta Methods --------------
    */


    @Override
    public JsonElement toJson() {
        return new GsonBuilder().serializeSpecialFloatingPointValues().create().toJsonTree(this);
    }

    @Override
    public Element fromJson(String jsonString) {
        return new GsonBuilder()
                .registerTypeAdapter(Element.class, new ElementDeserializer())
                .registerTypeAdapter(Form.class, new FormDeserializer())
                .create().fromJson(jsonString, Element.class);
    }



    public void add(UUID playerUUID){
        InversionOfControl.get().build(GUIService.class).addElement(playerUUID, this);
    }

    public void remove(UUID playerUUID){
        InversionOfControl.get().build(GUIService.class).removeElement(playerUUID, this);
    }

    public void update(UUID playerUUID){
        InversionOfControl.get().build(GUIService.class).updateElement(playerUUID, this);
    }

}
