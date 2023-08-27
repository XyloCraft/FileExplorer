package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import de.asedem.fileexplorer.util.Pair;
import de.asedem.fileexplorer.util.exception.NotADirectoryException;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class CdCommand extends CLICommand {

    public CdCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length < 1) return;
        try {
            this.plugin.getFileManager()
                    .get(sender)
                    .up(String
                            .join(" ", args)
                            .replace("/ ", "/"));
            this.plugin.language().send(sender, "cli.cd.success", Pair.of("name",
                    this.plugin.getFileManager().get(sender).getPath().toFile().getName()));
        } catch (NotADirectoryException exception) {
            this.plugin.language().send(sender, "cli.cd.not-found", Pair.of("name", exception.getFile().getName()));
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
                        .filter(File::isDirectory)
                        .map(File::getName)
                        .map(string -> String.format("%s/", string)), Stream.of("../"))
                .toList();
    }
}
