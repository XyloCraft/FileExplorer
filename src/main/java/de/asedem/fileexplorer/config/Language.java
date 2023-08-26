package de.asedem.fileexplorer.config;

import de.asedem.fileexplorer.util.Pair;
import de.rytrox.spicy.config.UTFConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Language {

    private final UTFConfig configuration;

    public Language(@NotNull UTFConfig configuration) {
        this.configuration = configuration;
    }

    public void send(@NotNull CommandSender sender, @NotNull String key, @NotNull Pair<String, String>... replacements) {
        String message = this.configuration.getString(key, "");
        for (Pair<String, String> replacement : replacements)
            message = message.replace(replacement.getKey(), replacement.getValue());
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
