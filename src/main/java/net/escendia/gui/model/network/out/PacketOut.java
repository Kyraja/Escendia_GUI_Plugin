package net.escendia.gui.model.network.out;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;

public abstract class PacketOut {

    private JsonObject jsonObject;

    public PacketOut(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public PacketOut(){
        this.jsonObject = new JsonObject();
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(GlobalScope.PACKET_ID, getID());
        jsonObject.add(GlobalScope.BODY, getJsonObject());
        return jsonObject;
    }

    public abstract int getID();

    public JsonObject getJsonObject(){
        return jsonObject;
    }

}
