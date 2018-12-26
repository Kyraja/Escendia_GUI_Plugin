package net.escendia.gui.model.images;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.escendia.gui.controll.ImageService;
import net.escendia.gui.model.factories.FactoryElement;
import org.newdawn.slick.opengl.Texture;

public class Image implements FactoryElement<Image> {


    private String imageName;
    private byte[] imageData;

    public Image(String imageName, byte[] imageData){
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageName() {
        return imageName;
    }

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
