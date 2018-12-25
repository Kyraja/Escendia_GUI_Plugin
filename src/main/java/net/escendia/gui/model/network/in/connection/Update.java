package net.escendia.gui.model.network.in.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.GeneralGUIData;
import net.escendia.gui.model.network.in.PacketIn;
import net.escendia.gui.model.network.out.PacketOut;

/**
 * Packet: Updated GUI Data ({@link GeneralGUIData}
 */
public class Update extends PacketIn {

    public Update(JsonObject jsonObject){
        super(jsonObject);
    }

}
