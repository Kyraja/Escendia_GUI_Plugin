package net.escendia.gui.model.network.in;

import com.google.gson.JsonObject;

public abstract class PacketIn {

    protected final JsonObject jsonObject;

    public PacketIn(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }

}
