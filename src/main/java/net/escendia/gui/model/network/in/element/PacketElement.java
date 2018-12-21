package net.escendia.gui.model.network.in.element;

import com.google.gson.JsonObject;
import net.escendia.gui.model.network.in.PacketIn;

/**
 * Abstract class to handel packet elements
 */
public abstract class PacketElement extends PacketIn {

    public PacketElement(JsonObject jsonObject) {
        super(jsonObject);
    }

}
