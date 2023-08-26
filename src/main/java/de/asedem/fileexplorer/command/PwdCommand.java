package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import de.asedem.fileexplorer.util.Pair;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PwdCommand extends CLICommand {

    public PwdCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        this.plugin.language().send(sender, "cli.pwd.message", Pair.of("path",
                this.plugin.getFileManager().get(sender).getPath().toString()));
    }
}
