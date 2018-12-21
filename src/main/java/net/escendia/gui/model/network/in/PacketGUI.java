package net.escendia.gui.model.network.in;

import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.network.out.PacketOut;

public abstract class PacketGUI extends PacketOut {

    public static class Create extends PacketGUI{

        public Create() {
            super();
        }

        @Override
        public int getID() {
            return GlobalScope.PACKET_SERVER_GUI_CREATE;
        }
    }

    public static class Delete extends PacketGUI{

        public Delete() {
            super();
        }

        @Override
        public int getID() {
            return GlobalScope.PACKET_SERVER_GUI_DELETE;
        }
    }

}
