package net.escendia.gui.model.network.out.gui;

import net.escendia.gui.GlobalScope;

/**
 * Packet: Sends a packet to the client to delete the current gui
 */
public class Delete extends PacketGUI{

    public Delete() {
        super();
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_GUI_DELETE;
    }
}