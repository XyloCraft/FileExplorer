package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import de.asedem.fileexplorer.util.Pair;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class DirCommand extends CLICommand {

    public DirCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        Optional.ofNullable(plugin.getFileManager().get(sender).subFiles())
                .ifPresentOrElse(files -> Arrays.stream(files)
                                .filter(Objects::nonNull)
                                .sorted(this::compareFiles)
                                .forEach(current -> {
                                    if (current.isFile()) this.plugin.language().send(sender, "cli.dir.file",
                                            Pair.of("name", current.getName()));
                                    else this.plugin.language().send(sender, "cli.dir.directory",
                                            Pair.of("name", current.getName()));
                                }),
                        () -> this.plugin.language().send(sender, "cli.dir.empty"));
    }

    private int compareFiles(@NotNull File file1, @NotNull File file2) {
        if (file1.isDirectory() == file2.isDirectory()) return 0;
        if (file1.isDirectory() && file2.isFile()) return -1;
        return 1;
    }
}
