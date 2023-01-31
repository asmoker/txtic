package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;
import org.jetbrains.annotations.Nullable;

public class NumberRangeCmd implements Cmd {
    private int start = 1;
    private int step = 1;
    private Integer padding;

    private @Nullable Integer toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }

    public NumberRangeCmd(String options) {
        if (Strings.isEmpty(options)) {
            return;
        }
        String[] optionsList = options.split(",");
        if (optionsList.length >= 1) {
            Integer value = toInt(optionsList[0]);
            if (value != null) {
                this.start = value.intValue();
            }
        }
        if (optionsList.length >= 2) {
            Integer value = toInt(optionsList[1]);
            if (value != null) {
                this.step = value.intValue();
            }
        }
        if (optionsList.length >= 3) {
            this.padding = toInt(optionsList[2]);
        }
    }

    private String next() {
        int value = this.start;
        this.start = this.start + this.step;
        if (this.padding != null && this.padding.intValue() > 1) {
            return String.format("%0" + this.padding.intValue() + "d", value);
        }
        return String.valueOf(value);
    }

    @Override
    public String run(String text) {
        return text + this.next();
    }
}
