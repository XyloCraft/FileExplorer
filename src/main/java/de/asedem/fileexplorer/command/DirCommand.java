package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class DirCommand implements CommandExecutor {

    private final FileExplorer plugin;

    public DirCommand(@NotNull FileExplorer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Optional.ofNullable(plugin.getFileManager().get(sender).subFiles())
                .ifPresentOrElse(files -> Arrays.stream(files)
                                .filter(Objects::nonNull)
                                .sorted(this::compareFiles)
                                .map(current -> ChatColor.translateAlternateColorCodes('&',
                                        (current.isFile() ? "&a▤ " : "&d▥ ") + current.getName()))
                                .forEach(sender::sendMessage),
                        () -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&cThe directory is empty!")));
        return true;
    }

    private int compareFiles(@NotNull File file1, @NotNull File file2) {
        if (file1.isDirectory() == file2.isDirectory()) return 0;
        if (file1.isDirectory() && file2.isFile()) return -1;
        return 1;
    }
}
