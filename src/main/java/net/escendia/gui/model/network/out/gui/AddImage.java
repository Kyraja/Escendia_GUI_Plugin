package net.escendia.gui.model.network.out.gui;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.images.Image;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

/**
 * Packet: Sends a packet to the client to create a new gui
 */
public class AddImage extends PacketGUI{

    public AddImage(String name, Image image) {
        super();
        getJsonObject().addProperty(GlobalScope.IMAGE_NAME,name);
        getJsonObject().addProperty(GlobalScope.IMAGE, DatatypeConverter.printBase64Binary(image.getImageData()));
    }

    public AddImage(String name, String imageurl) {
        super();
        getJsonObject().addProperty(GlobalScope.IMAGE_NAME,name);
        getJsonObject().addProperty(GlobalScope.IMAGE_URL, imageurl);
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_GUI_ADDIMAGE;
    }
}