package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import de.asedem.fileexplorer.util.Pair;
import de.asedem.fileexplorer.util.exception.FileCreationFailedException;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public class MkdirCommand extends CLICommand {

    public MkdirCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        this.folderNames(args)
                .forEach(name -> {
                    try {
                        this.plugin.getFileManager().get(sender).createDirectory(name);
                        this.plugin.language().send(sender, "cli.mkdir.success", Pair.of("name", name));
                    } catch (FileCreationFailedException exception) {
                        this.plugin.language().send(sender, "cli.mkdir.failed", Pair.of("name", exception.getFile().getName()));
                        this.plugin.getLogger().warning(exception.getLocalizedMessage());
                    }
                });
    }

    private Stream<String> folderNames(String[] rawNames) {
        return Arrays.stream(String.join(" ", rawNames)
                        .replace("\\ ", "~")
                        .split(" "))
                .map(name -> name.replace("~", " "));
    }
}
