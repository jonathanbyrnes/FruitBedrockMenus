package core.craft.commands;

import core.craft.FruitBedrockMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BedrockMenusCommand implements CommandExecutor {
    private final FruitBedrockMenus plugin;

    public BedrockMenusCommand(FruitBedrockMenus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.loadRedirectConfig();
            sender.sendMessage("§a[BedrockMenus] Configuration reloaded successfully.");
        } else {
            sender.sendMessage("§eUsage: /bedrockmenus reload");
        }
        return true;
    }
}
