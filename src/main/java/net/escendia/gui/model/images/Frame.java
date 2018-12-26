package net.escendia.gui.model.images;

import org.newdawn.slick.opengl.Texture;

public class Frame {

    private final long time;
    private final byte[] bytes;

    public Frame(long time, byte[] bytes) {
        this.time = time;
        this.bytes = bytes;
    }


    public long getTime() {
        return time;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
