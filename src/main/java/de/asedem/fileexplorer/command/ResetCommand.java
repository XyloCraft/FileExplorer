package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResetCommand extends CLICommand {

    public ResetCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        this.plugin.getFileManager().reset(sender);
        this.plugin.language().send(sender, "cli.reset.success");
    }
}
