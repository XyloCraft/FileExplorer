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
        if (!this.fileMap.containsKey(uuid)) this.fileMap.put(uuid, new VirtualFile(Paths.get(this.defaultURI)));
        return this.fileMap.getOrDefault(uuid, new VirtualFile(Paths.get(this.defaultURI)));
    }

    @NotNull
    public VirtualFile get(@NotNull CommandSender sender) {
        final UUID uuid = sender instanceof Player player ? player.getUniqueId() : new UUID(0, 0);
        if (!this.fileMap.containsKey(uuid)) this.fileMap.put(uuid, new VirtualFile(Paths.get(this.defaultURI)));
        return this.fileMap.getOrDefault(uuid, new VirtualFile(Paths.get(this.defaultURI)));
    }

    public void reset(@NotNull UUID uuid) {
        this.fileMap.remove(uuid);
    }
}
