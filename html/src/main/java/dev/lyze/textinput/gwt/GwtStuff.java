package dev.lyze.textinput.gwt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import dev.lyze.textinput.natives.INativeStuff;
import dev.lyze.textinput.natives.KeyboardCallback;

public class GwtStuff implements INativeStuff {
    private KeyboardCallback callback;

    private native static void setupTextField(String text, GwtStuff stuff) /*-{
        var body = $doc.getElementsByTagName("body")[0];

        // background element the user can click on to hide the input field and pass the value over to libgdx
        var background = $doc.createElement("div");
        background.setAttribute("style", "position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: 998; background-color: rgba(0, 0, 0, 0.3)");

        var textField = $doc.createElement("input");
        textField.value = text;
        textField.setAttribute("type", "text");
        textField.setAttribute("id", "veryCoolNativeTextField");
        textField.setAttribute("style", "position: absolute; top: 100vh; transform: translateY(-100%); width: 100%; z-index: 999; font-size: 24px; padding-top: 8px; padding-bottom: 8px;");

        body.appendChild(background);
        body.appendChild(textField);
        textField.focus();

        function runCallback() {
            body.removeChild(background);
            body.removeChild(textField);

            stuff.@GwtStuff::textFieldFocusLost(*)(textField.value);
        }

        background.addEventListener("click", function() {
            runCallback();
        });

        textField.addEventListener("blur", function () {
            runCallback();
        });

        textField.addEventListener("keyup", function (evt) {
            stuff.@GwtStuff::textFieldFocusLost(*)(textField.value);

           if (evt.keyCode === 13)
               runCallback();
        });
    }-*/;

    public void textFieldFocusLost(String text) {
        String newText = callback.setText(text);
        updateKeyboardValueNative(newText);
    }

    private native static void updateKeyboardValueNative(String text) /*-{
        var textField = $doc.getElementById("veryCoolNativeTextField");
        if (textField != null)
            textField.value = text;
    }-*/;

    // just to keep the ui density across all screens
    @Override
    public float getDensity() {
        if (GwtApplication.isMobileDevice())
            return 1 / Gdx.graphics.getDensity();

        return 1;
    }

    @Override
    public boolean isMobileDevice() {
        return GwtApplication.isMobileDevice();
    }

    @Override
    public void showKeyboard(String text, KeyboardCallback callback) {
        this.callback = callback;

        setupTextField(text, this);
    }
}
