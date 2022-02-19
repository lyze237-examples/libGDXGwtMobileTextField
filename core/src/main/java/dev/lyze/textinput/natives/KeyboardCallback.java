package dev.lyze.textinput.natives;

@FunctionalInterface
public interface KeyboardCallback {
    /**
     * Sets the text in the libgdx text field and returns the actual text set (=> Can be different if filters have been used)
     */
    String setText(String text);
}
