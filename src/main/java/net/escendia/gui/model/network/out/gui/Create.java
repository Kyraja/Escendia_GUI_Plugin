package net.escendia.gui.model.network.out.gui;

import net.escendia.gui.GlobalScope;

/**
 * Packet: Sends a packet to the client to create a new gui
 */
public class Create extends PacketGUI{

    public Create() {
        super();
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_GUI_CREATE;
    }
}