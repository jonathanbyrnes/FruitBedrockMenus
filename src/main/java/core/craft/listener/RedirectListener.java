package core.craft.listener;

import core.craft.config.CommandRedirectConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RedirectListener implements Listener {
    private final CommandRedirectConfig config;

    public RedirectListener(CommandRedirectConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!isBedrock(player)) return;

        String commandWithoutSlash = event.getMessage().substring(1).trim();
        String replacement = config.getRedirect(commandWithoutSlash);

        if (replacement != null) {
            event.setCancelled(true);
            player.performCommand(replacement);
        }
    }

    private boolean isBedrock(Player player) {
        return player.getName().startsWith("*");
    }
}
