package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

public class DeleteAllCmd implements Cmd {
    private final String regex;

    public DeleteAllCmd(String regex) {
        this.regex = regex;
    }

    @Override
    public String run(String text) {
        if (Strings.isEmpty(this.regex)) {
            return text;
        }
        return text.replaceAll(regex, "");
    }
}
