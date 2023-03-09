package top.threep.plugin.txtic.cmd;

import ws.vinta.pangu.Pangu;

public class PanguCmd implements Cmd {
    public PanguCmd() {
    }

    @Override
    public String run(String text) {
        return new Pangu().spacingText(text);
    }
}
