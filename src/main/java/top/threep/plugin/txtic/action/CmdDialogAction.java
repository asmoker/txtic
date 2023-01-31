package top.threep.plugin.txtic.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.Strings;
import org.jetbrains.annotations.NotNull;
import top.threep.plugin.txtic.cmd.Cmd;
import top.threep.plugin.txtic.cmd.CmdFactory;
import top.threep.plugin.txtic.ui.CustomMessagesImpl;

import java.util.List;

public class CmdDialogAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        CustomMessagesImpl msg = new CustomMessagesImpl();
        String input = msg.showInputDialog(project, "Input txtic command:", "Txtic", null);
        if (Strings.isEmpty(input)) {
            return;
        }

        // Get process cmd instance
        Cmd cmd = CmdFactory.get(input);
        if (cmd == null) {
            return;
        }

        // Append cmd result
        final Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        final CaretModel caretModel = editor.getCaretModel();
        Document document = editor.getDocument();
        WriteCommandAction.runWriteCommandAction(project, () -> {
            List<Caret> carets = caretModel.getAllCarets();
            for (Caret caret : carets) {
                int start = caret.getSelectionStart();
                int end = caret.getSelectionEnd();
                String text = caret.getSelectedText();
                if (Strings.isEmpty(text)) {
                    text = "";
                }
                String result = cmd.run(text);
                document.replaceString(start, end, result);
                caret.removeSelection();
                caret.moveToOffset(start + result.length());
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        // Get required data keys
        Project project = event.getProject();
        Editor editor = event.getData(CommonDataKeys.EDITOR);

        // Set visibility only in the case of
        // existing project editor
        event.getPresentation().setEnabledAndVisible(project != null && editor != null);
    }
}
