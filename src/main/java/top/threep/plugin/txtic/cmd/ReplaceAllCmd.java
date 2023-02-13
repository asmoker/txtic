package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

public class ReplaceAllCmd implements Cmd {
    private String regex;
    private String replacement;
    private final boolean optionsIsValid;

    public ReplaceAllCmd(String options) {
        if (Strings.isEmpty(options)) {
            this.optionsIsValid = false;
            return;
        }
        int idx = options.indexOf(",");
        if (idx < 0) {
            this.optionsIsValid = false;
            return;
        }
        this.optionsIsValid = true;
        this.regex = options.substring(0, idx);
        this.replacement = options.substring(idx + 1);
    }

    @Override
    public String run(String text) {
        if (!this.optionsIsValid || Strings.isEmpty(this.regex)) {
            return text;
        }
        return text.replaceAll(this.regex, this.replacement);
    }
}
