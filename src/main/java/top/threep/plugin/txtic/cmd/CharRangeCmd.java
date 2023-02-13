package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;
import org.jetbrains.annotations.Nullable;

public class CharRangeCmd implements Cmd {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int start = 1;
    private int step = 1;
    private boolean isUpperCase;

    private @Nullable Integer toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }

    public CharRangeCmd(boolean isUpperCase, String options) {
        if (Strings.isEmpty(options)) {
            return;
        }
        String[] optionsList = options.split(",");
        if (optionsList.length >= 1) {
            Integer value = toInt(optionsList[0]);
            if (value != null) {
                this.start = value;
            }
        }
        if (optionsList.length >= 2) {
            Integer value = toInt(optionsList[1]);
            if (value != null) {
                this.step = value;
            }
        }
        this.isUpperCase = isUpperCase;
    }

    private String next() {
        int value = this.start;
        this.start = this.start + this.step;
        String ch = String.valueOf(alphabet.charAt(value % alphabet.length()));
        if (this.isUpperCase) {
            return ch.toUpperCase();
        }
        return ch;
    }

    @Override
    public String run(String text) {
        return text + this.next();
    }
}
