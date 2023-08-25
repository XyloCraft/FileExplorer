package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.exception.NotADirectoryException;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class CdCommand implements TabExecutor {

    private final FileExplorer plugin;

    public CdCommand(@NotNull FileExplorer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return true;
        final String fileName = String.join(" ", args);
        try {
            this.plugin.getFileManager().get(sender).up(fileName);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&aSuccessfully moved to %s", this.plugin.getFileManager().get(sender).getPath().toFile().getName())));
        } catch (NotADirectoryException exception) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&c%s: No such file or directory", fileName)));
            this.plugin.getLogger().warning(exception.getLocalizedMessage());
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        return Stream.concat(Arrays.stream(Optional.ofNullable(this.plugin.getFileManager().get(sender).subFiles())
                                .orElse(new File[]{}))
                        .filter(Objects::nonNull)
                        .filter(File::isDirectory)
                        .map(File::getName), Stream.of(".."))
                .toList();
    }
}
