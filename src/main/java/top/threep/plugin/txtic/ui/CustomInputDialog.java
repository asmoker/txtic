package top.threep.plugin.txtic.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CustomInputDialog extends Messages.InputDialog {
    public CustomInputDialog(@Nullable Project project, @NlsContexts.DialogMessage String message, @NlsContexts.DialogTitle String title, @Nullable Icon icon, @Nullable @NonNls String initialValue, @Nullable InputValidator validator, String @NotNull @NlsContexts.Button [] options, int defaultOption, @Nullable @NlsContexts.DetailedDescription String comment) {
        super(project, message, title, icon, initialValue, validator, options, defaultOption, comment);
    }

    public @Nullable @NlsSafe String getInputString() {
        return getExitCode() == 0 ? myField.getText() : null;
    }
}

