package de.asedem.fileexplorer;

import de.asedem.fileexplorer.command.*;
import de.asedem.fileexplorer.manager.FileManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class FileExplorer extends JavaPlugin {

    private FileManager fileManager;

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        this.fileManager = new FileManager(this.getDataFolder().toURI());

        Objects.requireNonNull(this.getCommand("cd")).setExecutor(new CdCommand(this));
        Objects.requireNonNull(this.getCommand("rm")).setExecutor(new RmCommand(this));
        Objects.requireNonNull(this.getCommand("dir")).setExecutor(new DirCommand(this));
        Objects.requireNonNull(this.getCommand("pwd")).setExecutor(new PwdCommand(this));
        Objects.requireNonNull(this.getCommand("mkdir")).setExecutor(new MkdirCommand(this));
    }

    @Override
    public void onDisable() {
    }

    @NotNull
    public FileManager getFileManager() {
        return fileManager;
    }
}
