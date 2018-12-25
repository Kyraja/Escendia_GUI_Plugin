package net.escendia.gui.model.network.out.gui;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.images.Image;

/**
 * Packet: Sends a packet to the client to create a new gui
 */
public class AddImage extends PacketGUI{

    public AddImage(String name, Image image) {
        super();
        getJsonObject().addProperty(GlobalScope.IMAGE_NAME,name);
        getJsonObject().addProperty(GlobalScope.IMAGE, image.toJson().toString());
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_GUI_ADDIMAGE;
    }
}