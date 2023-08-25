package de.asedem.fileexplorer.util;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VirtualFile {

    private Path path;
    private File clipboard;

    public VirtualFile(@NotNull Path path) {
        this.path = path;
        this.clipboard = null;
    }

    public void navigate(@NotNull Path path) {
        if (path.toFile().exists() && path.toFile().isDirectory()) this.setPath(path);
    }

    public void up(@NotNull String name) {
        this.navigate(Paths.get(this.getPath().toString(), name));
    }

    public void down() {
        this.navigate(this.getPath().getParent());
    }

    public void copy(@NotNull String name) {
        final File file = Paths.get(this.getPath().toString(), name).toFile();
        if (file.exists()) this.setClipboard(file);
    }

    public void paste() throws IOException {
        if (this.getClipboard() != null) return;
        final File to = Paths.get(this.getPath().toString(), this.getClipboard().getName()).toFile();
        if (this.getClipboard().isDirectory()) FileUtils.copyDirectory(to, this.getClipboard());
        else FileUtils.copyFile(to, this.getClipboard());
    }

    public void createFile(@NotNull String name) throws IOException {
        final File file = Paths.get(this.getPath().toString(), name).toFile();
        if (!file.exists()) file.createNewFile();
    }

    public void createDirectory(@NotNull String name) {
        final File file = Paths.get(this.getPath().toString(), name).toFile();
        if (!file.exists()) file.mkdirs();
    }

    @Nullable
    public File[] subFiles() {
        final File[] files = this.getPath().toFile().listFiles();
        if (files == null || files.length == 0) return null;
        return files;
    }

    @Nullable
    public File getFile(@NotNull String name) {
        final File file = Paths.get(this.getPath().toString(), name).toFile();
        if (file.exists() && file.isFile()) return file;
        return null;
    }

    @NotNull
    public Path getPath() {
        return path;
    }

    private void setPath(@NotNull Path path) {
        this.path = path;
    }

    @Nullable
    public File getClipboard() {
        return clipboard;
    }

    public void setClipboard(@Nullable File clipboard) {
        this.clipboard = clipboard;
    }
}
