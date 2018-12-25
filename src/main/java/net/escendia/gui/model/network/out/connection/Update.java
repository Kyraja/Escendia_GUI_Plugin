package net.escendia.gui.model.network.out.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.GeneralGUIData;
import net.escendia.gui.model.network.out.PacketOut;


/**
 * Packet: Update the gui data
 */
public class Update extends PacketOut {

    public Update(GeneralGUIData generalGUIData){
        super(new JsonObject());
        try {
            getJsonObject().add(GlobalScope.INITDATA, generalGUIData.toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getID() {
        return GlobalScope.PACKET_CLIENT_CONNECTION_UPDATE;
    }
}
