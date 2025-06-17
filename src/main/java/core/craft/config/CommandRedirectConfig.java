package core.craft.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandRedirectConfig {
    private final Map<String, String> redirectMap = new LinkedHashMap<>();

    public CommandRedirectConfig(FileConfiguration config) {
        if (config.isConfigurationSection("commands")) {
            for (String key : config.getConfigurationSection("commands").getKeys(false)) {
                String value = config.getString("commands." + key);
                if (value != null && !value.isEmpty()) {
                    redirectMap.put(key.toLowerCase().trim(), value.trim());
                }
            }
        }
    }

    public String getRedirect(String inputCommand) {
        String normalized = inputCommand.toLowerCase();
        for (String key : redirectMap.keySet()) {
            if (normalized.startsWith(key)) return redirectMap.get(key);
        }
        return null;
    }

    public int size() {
        return redirectMap.size();
    }
}
