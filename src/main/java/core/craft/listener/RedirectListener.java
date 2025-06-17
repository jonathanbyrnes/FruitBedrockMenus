package com.example.bedrockredirect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.function.Supplier;

public class RedirectListener implements Listener {
    private final Supplier<CommandRedirectConfig> configSupplier;

    public RedirectListener(Supplier<CommandRedirectConfig> configSupplier) {
        this.configSupplier = configSupplier;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!isBedrock(player)) return;

        String commandWithoutSlash = event.getMessage().substring(1).trim();
        CommandRedirectConfig config = configSupplier.get();
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
