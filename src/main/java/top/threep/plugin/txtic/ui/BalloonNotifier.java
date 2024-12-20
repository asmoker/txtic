package top.threep.plugin.txtic.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;


public class BalloonNotifier {
    private static void notify(@Nullable Project project, String title, String content, NotificationType type) {
        new Notification("Txtic Notifier Group", title, content, type).notify(project);
    }

    public static void notifyInfo(@Nullable Project project, String content) {
        notify(project, "Txtic Info", content, NotificationType.INFORMATION);
    }

    public static void notifyWarn(@Nullable Project project, String content) {
        notify(project, "Txtic Warn", content, NotificationType.WARNING);
    }

    public static void notifyError(@Nullable Project project, String content) {
        notify(project, "Txtic Error", content, NotificationType.ERROR);
    }
}
