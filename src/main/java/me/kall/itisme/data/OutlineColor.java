package me.kall.itisme.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class OutlineColor {
    public int red;
    public int green;
    public int blue;
    public int alpha;

    public OutlineColor(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Contract(" -> new")
    public static @NotNull OutlineColor getPlaceholder() {
        return new OutlineColor(0, 0, 0, 0);
    }

    public String toString() {
        return this.red + ";" + this.green + ";" + this.blue + ";" + this.alpha;
    }
}
