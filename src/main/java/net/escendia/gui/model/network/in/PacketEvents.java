package net.escendia.gui.model.network.in;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.gui.Mouse;
import net.escendia.gui.model.listeners.impl.*;
import org.spongepowered.api.Sponge;

import java.util.ArrayList;
import java.util.UUID;

public abstract class PacketEvents extends PacketIn{

    private UUID uuid;
    private Element element;
    private String eventClassName;

    public PacketEvents(UUID uuid, JsonObject jsonObject) {
        super(jsonObject);
        this.uuid = uuid;
        this.element = new Element().fromJson(jsonObject.getAsJsonObject(GlobalScope.ELEMENT).toString());
        this.eventClassName = jsonObject.get(GlobalScope.EVENT).getAsString();
    }

    public Element getElement() {
        return element;
    }

    public UUID getUuid() {
        return uuid;
    }

    public static abstract class Post extends PacketEvents{

        public Post(UUID uuid, JsonObject jsonObject) {
            super(uuid, jsonObject);
        }

        public static class OnClick extends Post{
            public OnClick(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnClickEvent(getUuid(), getElement()));
            }
        }
        public static class OnMouseLeave extends Post{
            public OnMouseLeave(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnMouseLeaveEvent(getUuid(), getElement()));
            }
        }
        public static class OnMouseEnter extends Post{
            public OnMouseEnter(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnMouseEnterEvent(getUuid(), getElement()));
            }
        }
        public static class OnMouseButtonUp extends Post{
            public OnMouseButtonUp(UUID uuid, JsonObject body) {
                super(uuid, body);
                Mouse.Button clickedButton = Mouse.Button.valueOf(jsonObject.get(GlobalScope.BUTTON).getAsString());


                Sponge.getEventManager().post(new OnMouseButtonUpEvent(getUuid(), getElement(),clickedButton));

            }
        }
        public static class OnMouseButtonDown extends Post{
            public OnMouseButtonDown(UUID uuid, JsonObject body) {
                super(uuid, body);
                Mouse.Button clickedButton = Mouse.Button.valueOf(jsonObject.get(GlobalScope.BUTTON).getAsString());
                Sponge.getEventManager().post(new OnMouseButtonDownEvent(getUuid(), getElement(),clickedButton));
            }
        }
        public static class OnDoubleClick extends Post{
            public OnDoubleClick(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnDoubleClickEvent(getUuid(), getElement()));

            }
        }
        public static class OnKeyPressed extends Post{
            public OnKeyPressed(UUID uuid, JsonObject body) {
                super(uuid, body);
                JsonArray jsonArray = body.get(GlobalScope.KEY_PRESSED_LIST).getAsJsonArray();

                ArrayList<Integer> listdata = new ArrayList<Integer>();
                if (jsonArray != null) {
                    for (int i=0;i<jsonArray.size();i++){
                        listdata.add(jsonArray.get(i).getAsInt());
                    }
                }

                Sponge.getEventManager().post(new OnKeyPressedEvent(getUuid(), getElement(), listdata));

            }
        }
        public static class OnInput extends Post{
            public OnInput(UUID uuid, JsonObject body) {
                super(uuid, body);
                char input = body.get(GlobalScope.INPUT).getAsCharacter();
                Sponge.getEventManager().post(new OnInputEvent(getUuid(), getElement(), input));

            }
        }
        public static class OnPaste extends Post{
            public OnPaste(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnPasteEvent(getUuid(), getElement()));

            }
        }
        public static class OnCopy extends Post{
            public OnCopy(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnCopyEvent(getUuid(), getElement()));

            }
        }
        public static class OnRemove extends Post{
            public OnRemove(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnRemoveEvent(getUuid(), getElement()));

            }
        }

        public static class OnValueChange extends Post{
            public OnValueChange(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnValueChangeEvent(getUuid(), getElement()));

            }
        }

        public static class OnFocus extends Post{
            public OnFocus(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnFocusEvent(getUuid(), getElement()));

            }
        }

        public static class OnBlur extends Post{
            public OnBlur(UUID uuid, JsonObject body) {
                super(uuid, body);
                Sponge.getEventManager().post(new OnBlurEvent(getUuid(), getElement()));

            }
        }
    }

}
