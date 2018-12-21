package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.network.in.PacketIn;

import java.util.UUID;

public abstract class PacketEvents extends PacketIn {

    private UUID uuid;
    private Element element;
    private String eventClassName;

    public PacketEvents(UUID uuid, JsonObject jsonObject) {
        super(jsonObject);
        this.uuid = uuid;
        this.element = new Element().fromJson(jsonObject.getAsJsonObject(GlobalScope.ELEMENT).toString());
        this.eventClassName = jsonObject.get(GlobalScope.EVENT).getAsString();
    }

    public Element getElement() {
        return element;
    }

    public UUID getUuid() {
        return uuid;
    }

}
