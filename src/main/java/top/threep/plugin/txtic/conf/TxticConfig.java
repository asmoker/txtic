package top.threep.plugin.txtic.conf;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "TxticConfig",
        storages = {@Storage("TxticConfig.xml")}
)
@Service
public final class TxticConfig implements PersistentStateComponent<TxticConfig.State> {
    private State txticState = new State();
    public static final String DEFAULT_CARBON_URL = "https://carbon.now.sh";

    public static class State {
        public String carbonURL = "";
    }

    public static TxticConfig getInstance() {
        return ApplicationManager.getApplication().getService(TxticConfig.class);
    }

    @Nullable
    @Override
    public TxticConfig.State getState() {
        return txticState;
    }

    @Override
    public void loadState(@NotNull TxticConfig.State state) {
        this.txticState = state;
    }

    public void setCarbonURL(String carbonURL) {
        txticState.carbonURL = carbonURL;
    }

    public String getCarbonURL() {
        return txticState.carbonURL;
    }
}