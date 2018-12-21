package net.escendia.gui.model.factories;

import com.google.gson.JsonElement;

public interface FactoryElement<T> {

    public JsonElement toJson();
    public T fromJson(String jsonString);


}
