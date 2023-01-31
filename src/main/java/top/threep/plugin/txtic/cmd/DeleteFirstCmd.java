package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

public class DeleteFirstCmd implements Cmd {
    private String regex;

    public DeleteFirstCmd(String regex) {
        this.regex = regex;
    }

    @Override
    public String run(String text) {
        if (Strings.isEmpty(this.regex)) {
            return text;
        }
        return text.replaceFirst(regex, "");
    }
}
