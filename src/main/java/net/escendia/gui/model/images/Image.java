package net.escendia.gui.model.images;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.escendia.gui.model.factories.FactoryElement;
import org.newdawn.slick.opengl.Texture;

public abstract class Image implements FactoryElement<Image> {

    public abstract Texture getTexture();

    @Override
    public JsonElement toJson() {
        return new GsonBuilder().serializeSpecialFloatingPointValues().create().toJsonTree(this);
    }

    @Override
    public Image fromJson(String jsonString) {
        return new GsonBuilder()
                .create().fromJson(jsonString, Image.class);
    }
}
