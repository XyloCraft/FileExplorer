package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import de.asedem.fileexplorer.util.exception.CannotDeleteFileException;
import de.asedem.fileexplorer.util.exception.FileNotExistsException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class RmCommand extends CLICommand {

    public RmCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length < 1) return;
        final String fileName = String.join(" ", args).replace("/ ", "/");
        try {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&aSuccessfully removed %s",
                            this.plugin.getFileManager().get(sender).deleteFile(fileName))));
        } catch (CannotDeleteFileException exception) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&cCannot remove %s: File is occupied", exception.getFile().getName())));
            this.plugin.getLogger().warning(exception.getLocalizedMessage());
        } catch (FileNotExistsException exception) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&cCannot remove %s: No such file", exception.getFile().getName())));
            this.plugin.getLogger().warning(exception.getLocalizedMessage());
        }
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return Stream.concat(Arrays.stream(Optional.ofNullable(this.plugin.getFileManager()
                                        .get(sender)
                                        .subFiles(String.join("", args)))
                                .orElse(new File[]{}))
                        .filter(Objects::nonNull)
                        .map(File::getName)
                        .map(string -> String.format("%s/", string)), Stream.of("../"))
                .toList();
    }
}
