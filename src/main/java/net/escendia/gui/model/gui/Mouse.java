package net.escendia.gui.model.gui;

import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;

@Singleton
public class Mouse {

    @Inject
    public EscendiaLogger logger;

    private CursorType currentCursorType = CursorType.NORMAL;

    //Current values
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean middlePressed = false;

    private int mouseXPosition = 0;
    private int mouseYPosition = 0;

    private long timeLastClick = Long.MAX_VALUE;

    private final long timeDoubleClick = 255;

    //Art der Clicks
    private boolean click = false;
    private boolean doubleClick = false;

    //Previous Values

    private boolean leftPressedPrevious = false;
    private boolean rightPressedPrevious = false;
    private boolean middlePressedPrevious = false;

    private int mouseXPositionPrevious = 0;
    private int mouseYPositionPrevious = 0;



    public Mouse() {
        logger = InversionOfControl.get().build(EscendiaLogger.class);
    }


    //------- Getter Methods -------


    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isLeftPressedPrevious() {
        return leftPressedPrevious;
    }

    public int getMouseXPosition() {
        return mouseXPosition;
    }

    public int getMouseYPosition() {
        return mouseYPosition;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isRightPressedPrevious() {
        return rightPressedPrevious;
    }

    public boolean isMiddlePressed() {
        return middlePressed;
    }

    public boolean isMiddlePressedPrevious() {
        return middlePressedPrevious;
    }

    public boolean isClick() {
        return click;
    }

    public boolean isDoubleClick() {
        return doubleClick;
    }

    //----- Methods ------


    //----- load Methods ----


    public enum CursorType{NORMAL,HAND,TEXT}

    public enum Button {LEFT, MIDDLE, RIGHT}
}
