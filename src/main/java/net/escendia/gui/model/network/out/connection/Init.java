package net.escendia.gui.model.network.out.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.GeneralGUIData;

import java.util.HashMap;

/**
 * Packet: Sends the inital information like images or fonts to the client
 */
public class Init extends PacketConnection {

    public Init() {
        super(new JsonObject());
        GeneralGUIData generalGUIData = new GeneralGUIData();
        HashMap<String, String> images = new HashMap<>();
        //TODO Configfile for inputdata
        images.put("ethyronimage.png", "http://ethyron.net/images/logo.png");
        images.put("button1.png", "http://cdn.pixabay.com/photo/2016/01/23/11/41/button-1157299_960_720.png");
        generalGUIData.setImages(images);
        getJsonObject().add(GlobalScope.INITDATA, generalGUIData.toJson());
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_CONNECTION_INIT;
    }
}
