package net.escendia.gui.model.network.in.gui;

import com.google.gson.JsonObject;
import net.escendia.gui.model.network.in.PacketIn;

/**
 * Abstract class to handle packet input from the gui when the gui is created or deleted
 */
public abstract class PacketGUI extends PacketIn {
    public PacketGUI(JsonObject jsonObject) {
        super(jsonObject);
    }
}
