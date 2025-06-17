package core.craft;

import core.craft.commands.BedrockMenusCommand;
import core.craft.config.CommandRedirectConfig;
import core.craft.listener.RedirectListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FruitBedrockMenus extends JavaPlugin {

    private CommandRedirectConfig redirectConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadRedirectConfig();

        getCommand("bedrockmenus").setExecutor(new BedrockMenusCommand(this));
        getServer().getPluginManager().registerEvents(new RedirectListener(redirectConfig), this);
    }

    public void loadRedirectConfig() {
        reloadConfig();
        this.redirectConfig = new CommandRedirectConfig(getConfig());
    }

    public CommandRedirectConfig getRedirectConfig() {
        return redirectConfig;
    }

}
