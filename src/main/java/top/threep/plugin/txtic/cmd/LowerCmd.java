package top.threep.plugin.txtic.cmd;

public class LowerCmd implements Cmd {
    public LowerCmd() {
    }

    @Override
    public String run(String text) {
        return text.toLowerCase();
    }
}
