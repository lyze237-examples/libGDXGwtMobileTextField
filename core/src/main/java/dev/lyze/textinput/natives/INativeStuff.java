package dev.lyze.textinput.natives;

public interface INativeStuff {
    void showKeyboard(String text, KeyboardCallback callback);
    boolean isMobileDevice();
    float getDensity();
}
