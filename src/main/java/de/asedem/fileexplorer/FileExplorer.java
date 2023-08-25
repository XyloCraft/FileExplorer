package de.asedem.fileexplorer;

import de.asedem.fileexplorer.manager.FileManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class FileExplorer extends JavaPlugin {

    private FileManager fileManager;

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        this.fileManager = new FileManager(this.getDataFolder().toURI());
    }

    @Override
    public void onDisable() {
    }

    @NotNull
    public FileManager getFileManager() {
        return fileManager;
    }
}
