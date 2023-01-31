package top.threep.plugin.txtic.cmd;

public class UpperCmd implements Cmd {
    public UpperCmd() {
    }

    @Override
    public String run(String text) {
        return text.toUpperCase();
    }
}
