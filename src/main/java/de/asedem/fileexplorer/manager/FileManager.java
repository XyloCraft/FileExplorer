package de.asedem.fileexplorer.manager;

import de.asedem.fileexplorer.util.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileManager {

    public static final UUID CONSOLE = new UUID(0, 0);

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

    public void reset(@NotNull UUID uuid) {
        this.fileMap.remove(uuid);
    }
}
