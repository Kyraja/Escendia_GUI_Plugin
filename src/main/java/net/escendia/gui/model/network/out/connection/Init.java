package net.escendia.gui.model.network.out.connection;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.controll.FileService;
import net.escendia.gui.model.gui.GeneralGUIData;
import net.escendia.ioc.InversionOfControl;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Packet: Sends the inital information like images or fonts to the client
 */
public class Init extends PacketConnection {

    public Init() {
        super(new JsonObject());
        FileService fileService = InversionOfControl.get().build(FileService.class);
        try {
            InputStream stream = fileService.getFile("GeneralData.json");
            getJsonObject().add(GlobalScope.INITDATA, new GeneralGUIData().fromJson(CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8))).toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_CONNECTION_INIT;
    }
}
