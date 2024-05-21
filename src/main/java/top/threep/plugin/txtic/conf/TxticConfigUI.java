package top.threep.plugin.txtic.conf;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TxticConfigUI implements Configurable {
    private JTextField carbonURLField = new JTextField();
    private JPanel panel;

    public TxticConfigUI() {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        carbonURLField.setColumns(32);
        panel.add(new JLabel("Carbon URL:"));
        panel.add(carbonURLField);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Txtic Settings";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return panel;
    }

    @Override
    public boolean isModified() {
        TxticConfig settings = TxticConfig.getInstance();
        return !carbonURLField.getText().equals(settings.getCarbonURL());
    }

    @Override
    public void apply() throws ConfigurationException {
        TxticConfig settings = TxticConfig.getInstance();
        settings.setCarbonURL(carbonURLField.getText());
    }

    @Override
    public void reset() {
        TxticConfig settings = TxticConfig.getInstance();
        carbonURLField.setText(settings.getCarbonURL());
    }

    @Override
    public void disposeUIResources() {
        panel = null;
        carbonURLField = null;
    }
}
