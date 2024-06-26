package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

import static top.threep.plugin.txtic.util.NumberUtil.toInt;

public class CharRangeCmd implements Cmd {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int start = 0;
    private int step = 1;
    private final boolean isUpperCase;

    public static int getIdx(int value) {
        if (value < 0) {
            value = (Math.abs(value) / 26 + 1) * 26 + value;
        }
        return value % 26;
    }

    public CharRangeCmd(boolean isUpperCase, String options) {
        this.isUpperCase = isUpperCase;
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
    }

    private String next() {
        int value = this.start;
        this.start = this.start + this.step;
        String ch = String.valueOf(alphabet.charAt(getIdx(value)));
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
