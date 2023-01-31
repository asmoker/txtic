package top.threep.plugin.txtic.cmd;

import java.util.UUID;

public class UUIDCmd implements Cmd {
    private String separator;
    private boolean isUpperCase;

    public UUIDCmd(boolean isUpperCase, String options) {
        this.isUpperCase = isUpperCase;
        this.separator = options;
    }

    @Override
    public String run(String text) {
        String result = UUID.randomUUID().toString();
        if (this.isUpperCase) {
            result = result.toUpperCase();
        }
        if (this.separator != null && !"-".equals(this.separator)) {
            result = result.replace("-", this.separator);
        }
        return result;
    }
}
