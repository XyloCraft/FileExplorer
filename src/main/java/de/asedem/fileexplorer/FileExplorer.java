package de.asedem.fileexplorer;

import de.asedem.fileexplorer.command.*;
import de.asedem.fileexplorer.config.Language;
import de.asedem.fileexplorer.config.model.Config;
import de.asedem.fileexplorer.manager.FileManager;
import de.rytrox.spicy.config.ConfigCreator;
import de.rytrox.spicy.config.UTFConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public final class FileExplorer extends JavaPlugin {

    private FileManager fileManager;
    private ConfigCreator configCreator;
    private Config config;
    private Language language;

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        this.fileManager = new FileManager(this.getDataFolder().toURI());
        this.configCreator = new ConfigCreator(this.getDataFolder());

        this.reloadConfig();

        Objects.requireNonNull(this.getCommand("cd")).setExecutor(new CdCommand(this));
        Objects.requireNonNull(this.getCommand("rm")).setExecutor(new RmCommand(this));
        Objects.requireNonNull(this.getCommand("dir")).setExecutor(new DirCommand(this));
        Objects.requireNonNull(this.getCommand("pwd")).setExecutor(new PwdCommand(this));
        Objects.requireNonNull(this.getCommand("mkdir")).setExecutor(new MkdirCommand(this));
        Objects.requireNonNull(this.getCommand("reset")).setExecutor(new ResetCommand(this));
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void reloadConfig() {
        try {
            final File configFile = configCreator.copyDefaultFile(Paths.get("config.yml"));
            this.config = new Config(new UTFConfig(configFile));
        } catch (IOException exception) {
            this.getLogger().severe("config.yml could not be loaded!");
        }
        final File languageFile;
        try {
            languageFile = configCreator.copyDefaultFile(Paths.get("language.yml"));
            this.language = new Language(new UTFConfig(languageFile));
        } catch (IOException exception) {
            this.getLogger().severe("language.yml could not be loaded!");
        }
    }

    @NotNull
    public Config config() {
        return config;
    }

    @NotNull
    public Language language() {
        return language;
    }

    @NotNull
    public FileManager getFileManager() {
        return fileManager;
    }
}
