package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

import static top.threep.plugin.txtic.util.NumberUtil.toInt;

public class RepeatCmd implements Cmd {
    private String value;
    private int times = 2;


    public RepeatCmd(String options) {
        if (Strings.isEmpty(options)) {
            return;
        }
        String[] optionsList = options.split(",");
        if (optionsList.length >= 1 && !Strings.isEmpty(optionsList[0])) {
            this.value = optionsList[0];
        }
        if (optionsList.length >= 2) {
            Integer value = toInt(optionsList[1]);
            if (value != null) {
                this.times = value;
            }
        }
    }

    private String next() {
        if (Strings.isEmpty(this.value)) {
            return "";
        }
        if (times < 1) {
            return "";
        }
        return this.value.repeat(this.times);
    }

    @Override
    public String run(String text) {
        return text + this.next();
    }
}
