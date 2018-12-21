package net.escendia.gui.model.form;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.factories.ElementDeserializer;
import net.escendia.gui.model.factories.FactoryElement;
import net.escendia.gui.model.factories.FormDeserializer;
import net.escendia.gui.model.form.style.FormStyle;

public class Form implements FactoryElement<Form> {


    private FormStyle formStyle;
    private String formClass = getClass().toString();


    public Form(){
        this.formStyle = new FormStyle();
    }

    public Form(FormStyle formStyle){
        this.formStyle = formStyle;
    }

    public FormStyle getFormStyle() {
        return formStyle;
    }

    public void setFormStyle(FormStyle formStyle) {
        this.formStyle = formStyle;
    }

    public String getFormClass() {
        return formClass;
    }

    @Override
    public JsonElement toJson() {
        return new GsonBuilder().create().toJsonTree(this);
    }

    @Override
    public Form fromJson(String jsonString) {
        return new GsonBuilder().registerTypeAdapter(Form.class, new FormDeserializer()).create().fromJson(jsonString, Form.class);
    }


}
