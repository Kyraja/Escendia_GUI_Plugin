package net.escendia.gui.model.network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.Message;

public class Packet implements Message {


    private JsonObject jsonObject;

    public Packet(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public Packet(){
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public void readFrom(ChannelBuf buf) {

        String message = "";
        if(buf.hasArray())message = new String(buf.array());
        else {
            message = buf.readUTF();
        }
        jsonObject = new JsonParser().parse(message).getAsJsonObject();

    }

    @Override
    public void writeTo(ChannelBuf buf) {
        buf.writeUTF(jsonObject.toString());
    }
}
