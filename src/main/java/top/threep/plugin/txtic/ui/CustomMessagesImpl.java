package top.threep.plugin.txtic.ui;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapperPeer;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.TestDialogManager;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.TextRange;
import com.intellij.ui.messages.MessagesServiceImpl;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

import static com.intellij.openapi.ui.Messages.getCancelButton;
import static com.intellij.openapi.ui.Messages.getOkButton;

public class CustomMessagesImpl extends MessagesServiceImpl {
    private static boolean isApplicationInUnitTestOrHeadless() {
        Application app = ApplicationManager.getApplication();
        return app != null && (app.isUnitTestMode() || app.isHeadlessEnvironment());
    }

    @Override
    public String showInputDialog(@Nullable Project project,
                                  Component parentComponent, String message,
                                  String title,
                                  @Nullable Icon icon,
                                  @Nullable String initialValue,
                                  @Nullable InputValidator validator,
                                  @Nullable TextRange selection,
                                  @Nullable @NlsContexts.DetailedDescription String comment) {
        if (isApplicationInUnitTestOrHeadless()) {
            return TestDialogManager.getTestInputImplementation().show(message, validator);
        }

        CustomInputDialog dialog = new CustomInputDialog(project, message, title, icon, initialValue, validator,
                new String[]{getOkButton(), getCancelButton()},
                0, comment);

        final JTextComponent field = dialog.getTextField();
        if (selection != null) {
            // set custom selection
            field.select(selection.getStartOffset(), selection.getEndOffset());
            field.putClientProperty(DialogWrapperPeer.HAVE_INITIAL_SELECTION, true);
        }

        dialog.show();
        return dialog.getInputString();
    }

    public String showInputDialog(@Nullable Project project, String message, String title, @Nullable Icon icon) {
        return this.showInputDialog(project, null, message, title, icon, null, null, null, null);
    }
}
