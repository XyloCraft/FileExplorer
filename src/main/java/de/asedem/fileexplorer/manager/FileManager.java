package de.asedem.fileexplorer.manager;

import de.asedem.fileexplorer.util.VirtualFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileManager {

    private final URI defaultURI;
    private final Map<@NotNull UUID, @NotNull VirtualFile> fileMap = new HashMap<>();

    public FileManager(@NotNull URI defaultURI) {
        this.defaultURI = defaultURI;
    }

    @NotNull
    public VirtualFile get(@NotNull UUID uuid) {
        return this.fileMap.computeIfAbsent(uuid, temp -> new VirtualFile(Paths.get(this.defaultURI)));
    }

    @NotNull
    public VirtualFile get(@NotNull CommandSender sender) {
        return this.get(sender instanceof Player player ? player.getUniqueId() : new UUID(0, 0));
    }

    public void reset(@NotNull UUID uuid) {
        this.fileMap.remove(uuid);
    }

    public void reset(@NotNull CommandSender sender) {
        this.reset(sender instanceof Player player ? player.getUniqueId() : new UUID(0, 0));
    }
}
