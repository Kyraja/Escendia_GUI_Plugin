package net.escendia.gui.model.form.style;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.escendia.gui.model.factories.FactoryElement;

import java.awt.*;


public class FormStyle implements FactoryElement<FormStyle> {

    private Margin margin = new Margin();
    private Border border = new Border();
    private Padding padding = new Padding();
    private double xPosition = 0;
    private double yPosition = 0;
    private double xPositionPercentage = 0;
    private double yPositionPercentage = 0;
    private double xOffset = 0;
    private double yOffset = 0;
    private double heightPercentage = 0;
    private double widthPercentage = 0;
    private double height = 0;
    private double width = 0;

    //---- Color -----
    private int backgroundColorRed = 119;
    private int backgroundColorGreen = 136;
    private int backgroundColorBlue = 153;
    private int backgroundColorAlpha = 255;

    private String backgroundImage;

    //-- For Text Elements --
    private double fontSize = 1.0;

    private int fontColorRed = 0;
    private int fontColorGreen= 0;
    private int fontColorBlue= 0;
    private int fontColorAlpha= 0;

    //private Font font;
    private String font = "minecraft normal";

    private Alignment alignment = Alignment.LEFT;
    private Alignment verticalAlignment = Alignment.TOP;

    private int cursorColorRed = 0;
    private int cursorColorGreen = 0;
    private int cursorColorBlue = 0;
    private int cursorColorAlpha = 0;
    private double fontScale = 0;

    public FormStyle(){
    }

    public FormStyle(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    /*
    -------- Getter Methods --------
     */

    public Margin getMargin() {
        return margin;
    }

    public Border getBorder() {
        return border;
    }

    public Padding getPadding() {
        return padding;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getxPositionPercentage() {
        return xPositionPercentage;
    }

    public double getyPositionPercentage() {
        return yPositionPercentage;
    }

    public double getxOffset() {
        return xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public double getWidthPercentage() {
        return widthPercentage;
    }

    public double getHeightPercentage() {
        return heightPercentage;
    }

    public Color getBackgroundColor() {
        return new Color(backgroundColorRed,backgroundColorGreen,backgroundColorBlue,backgroundColorAlpha);
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getFont() {
        return this.font;
    }

    public double getFontSize() {
        return fontSize;
    }

    public Color getFontColor() {
        return new Color(fontColorRed,fontColorGreen,fontColorBlue,fontColorAlpha);
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Color getCursorColor() {
        return new Color(cursorColorRed,cursorColorGreen,cursorColorBlue,cursorColorAlpha);
    }

    /**
     * Return font scale to draw strings
     * @return
     */
    public double getFontScale() {
        return this.fontScale;
    }

    /**
     * Get the vertical alignment for texts
     * @return
     */
    public Alignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public int getBackgroundColorRed() {
        return backgroundColorRed;
    }

    public int getBackgroundColorGreen() {
        return backgroundColorGreen;
    }

    public int getBackgroundColorBlue() {
        return backgroundColorBlue;
    }

    public int getBackgroundColorAlpha() {
        return backgroundColorAlpha;
    }

    public int getFontColorRed() {
        return fontColorRed;
    }

    public int getFontColorGreen() {
        return fontColorGreen;
    }

    public int getFontColorBlue() {
        return fontColorBlue;
    }

    public int getFontColorAlpha() {
        return fontColorAlpha;
    }

    public int getCursorColorRed() {
        return cursorColorRed;
    }

    public int getCursorColorGreen() {
        return cursorColorGreen;
    }

    public int getCursorColorBlue() {
        return cursorColorBlue;
    }

    public int getCursorColorAlpha() {
        return cursorColorAlpha;
    }


    /*
    -------- Setter Methods --------
     */

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }



    public void setBackgroundColor(int r, int g, int b, int a){
        this.backgroundColorRed = r;
        this.backgroundColorGreen = g;
        this.backgroundColorBlue = b;
        this.backgroundColorAlpha = a;
    }

    public void setBackgroundColor(Color backgroundColor) {
        setBackgroundColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(int r, int g, int b, int a){
        this.fontColorRed = r;
        this.fontColorGreen = g;
        this.fontColorBlue = b;
        this.fontColorAlpha = a;
    }
    public void setFontColor(Color fontColor) {

        setBackgroundColor(fontColor.getRed(), fontColor.getGreen(), fontColor.getBlue(), fontColor.getAlpha());
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setCursorColor(int r, int g, int b, int a){
        this.cursorColorRed = r;
        this.cursorColorGreen = g;
        this.cursorColorBlue = b;
        this.cursorColorAlpha = a;
    }

    public void setCursorColor(Color cursorColor){
        setBackgroundColor(cursorColor.getRed(), cursorColor.getGreen(), cursorColor.getBlue(), cursorColor.getAlpha());
    }

    /**
     * Set font scale for texts
     * @param fontScale
     */
    public void setFontScale(double fontScale) {
        this.fontScale = fontScale;
    }

    /**
     * Set the vertical alignment for texts
     * @param verticalAlignment
     */
    public void setVerticalAlignment(Alignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public void setxPositionPercentage(double xPositionPercentage) {
        this.xPositionPercentage = xPositionPercentage;
    }

    public void setyPositionPercentage(double yPositionPercentage) {
        this.yPositionPercentage = yPositionPercentage;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public void setHeightPercentage(double heightPercentage) {
        this.heightPercentage = heightPercentage;
    }

    public void setWidthPercentage(double widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public void setBackgroundColorRed(int backgroundColorRed) {
        this.backgroundColorRed = backgroundColorRed;
    }

    public void setBackgroundColorGreen(int backgroundColorGreen) {
        this.backgroundColorGreen = backgroundColorGreen;
    }

    public void setBackgroundColorBlue(int backgroundColorBlue) {
        this.backgroundColorBlue = backgroundColorBlue;
    }

    public void setBackgroundColorAlpha(int backgroundColorAlpha) {
        this.backgroundColorAlpha = backgroundColorAlpha;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColorRed(int fontColorRed) {
        this.fontColorRed = fontColorRed;
    }

    public void setFontColorGreen(int fontColorGreen) {
        this.fontColorGreen = fontColorGreen;
    }

    public void setFontColorBlue(int fontColorBlue) {
        this.fontColorBlue = fontColorBlue;
    }

    public void setFontColorAlpha(int fontColorAlpha) {
        this.fontColorAlpha = fontColorAlpha;
    }

    public void setCursorColorRed(int cursorColorRed) {
        this.cursorColorRed = cursorColorRed;
    }

    public void setCursorColorGreen(int cursorColorGreen) {
        this.cursorColorGreen = cursorColorGreen;
    }

    public void setCursorColorBlue(int cursorColorBlue) {
        this.cursorColorBlue = cursorColorBlue;
    }

    public void setCursorColorAlpha(int cursorColorAlpha) {
        this.cursorColorAlpha = cursorColorAlpha;
    }

    @Override
    public JsonElement toJson() {
        return new GsonBuilder().create().toJsonTree(this);
    }

    @Override
    public FormStyle fromJson(String jsonString) {
        return new GsonBuilder().create().fromJson(jsonString, this.getClass());
    }



}
