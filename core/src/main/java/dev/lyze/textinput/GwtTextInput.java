package dev.lyze.textinput;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import dev.lyze.textinput.natives.INativeStuff;

public class GwtTextInput extends TextField {
    public GwtTextInput(INativeStuff nativeStuff) {
        super("", VisUI.getSkin());

        if (nativeStuff.isMobileDevice()) {
            setDisabled(true);

            addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    nativeStuff.showKeyboard(getText(), str -> {
                        setText(str);
                        return getText();
                    });

                    return false;
                }
            });
        }
    }
}
