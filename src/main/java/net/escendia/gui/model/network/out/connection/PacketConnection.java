package net.escendia.gui.model.network.out.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.GeneralGUIData;
import net.escendia.gui.model.network.out.PacketOut;

import java.util.HashMap;

/**
 * Abstract class to handle connection packets
 */
public abstract class PacketConnection extends PacketOut {

    public PacketConnection(JsonObject jsonObject) {
        super(jsonObject);
    }

}
