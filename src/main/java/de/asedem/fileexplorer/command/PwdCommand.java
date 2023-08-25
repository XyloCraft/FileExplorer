package de.asedem.fileexplorer.command;

import de.asedem.fileexplorer.FileExplorer;
import de.asedem.fileexplorer.util.CLICommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PwdCommand extends CLICommand {

    public PwdCommand(@NotNull FileExplorer plugin) {
        super(plugin);
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        sender.sendMessage(this.plugin.getFileManager().get(sender).getPath().toString());
    }
}
