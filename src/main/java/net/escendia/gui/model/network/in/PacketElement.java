package net.escendia.gui.model.network.in;

import com.google.gson.JsonObject;

public abstract class PacketElement extends PacketIn{

    public PacketElement(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static class Add extends PacketElement{

        public Add(JsonObject jsonObject) {
            super(jsonObject);
            //TODO Added Event
        }
    }

    public static class Remove extends  PacketElement{

        public Remove(JsonObject jsonObject) {
            super(jsonObject);
            //TODO Removed Event
        }
    }

    public static class Update extends  PacketElement{

        public Update(JsonObject jsonObject) {
            super(jsonObject);
            //TODO UPDATED Event
        }
    }

}
