package top.threep.plugin.txtic.cmd;


import com.intellij.openapi.util.text.Strings;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PasteCmd implements Cmd {
    private String separator = " ";
    private int idx = 0;
    private String[] contents;

    public PasteCmd(String options) {
        if (!Strings.isEmpty(options)) {
            this.separator = options;
        }
        this.initContents();
    }

    private void initContents() {
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            if (Strings.isEmpty(data)) {
                this.contents = new String[]{};
                return;
            }
            this.contents = data.split(this.separator);
        } catch (UnsupportedFlavorException | IOException ignored) {
        }
    }

    @Override
    public String run(String text) {
        if (this.contents.length == 0 || (this.idx + 1) > this.contents.length) {
            return text;
        }
        String result = text + contents[idx];
        this.idx += 1;
        return result;
    }
}
