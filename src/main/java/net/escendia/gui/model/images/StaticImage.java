package net.escendia.gui.model.images;

import net.escendia.gui.controll.ImageService;
import org.newdawn.slick.opengl.Texture;

/**
 * Static image data
 */
public class StaticImage extends Image {

    public StaticImage(String imageName, byte[] imageData) {
        super(imageName, imageData);
    }

}
