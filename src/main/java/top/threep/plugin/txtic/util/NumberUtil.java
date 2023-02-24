package top.threep.plugin.txtic.util;

import org.jetbrains.annotations.Nullable;

public class NumberUtil {
    public static @Nullable Integer toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }
}
