package top.threep.plugin.txtic.cmd;

import com.intellij.openapi.util.text.Strings;

public class CmdFactory {
    private static Cmd getCmd(String cmd, String options) {
        switch (cmd) {
            case "a":
                return new CharRangeCmd(false, options);
            case "A":
                return new CharRangeCmd(true, options);
            case "UUID":
                return new UUIDCmd(true, options);
            case "uuid":
                return new UUIDCmd(false, options);
            case "l":
            case "lower":
                return new LowerCmd();
            case "u":
            case "upper":
                return new UpperCmd();
            case "df":
            case "del_first":
                return new DeleteFirstCmd(options);
            case "da":
            case "del_all":
                return new DeleteAllCmd(options);
            case "rf":
            case "rep_first":
                return new ReplaceFirstCmd(options);
            case "ra":
            case "rep_all":
                return new ReplaceAllCmd(options);
            case "r":
            case "range":
                return new NumberRangeCmd(options);
            case "p":
            case "paste":
                return new PasteCmd(options);
            case "rp":
            case "repeat":
                return new RepeatCmd(options);
            case "pangu":
                return new PanguCmd();
            case "p2m":
                return new Param2MapCmd(options);
            default:
                return null;
        }
    }

    public static Cmd get(String input) {
        if (Strings.isEmpty(input)) {
            return null;
        }

        int idx = input.indexOf(",");
        final String cmd;
        final String options;
        if (idx < 0) {
            cmd = input;
            options = null;
        } else {
            cmd = input.substring(0, idx);
            options = input.substring(idx + 1);
        }

        return getCmd(cmd, options);
    }
}
