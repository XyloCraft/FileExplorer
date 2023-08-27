package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileException extends Exception {

    private final File file;

    public FileException(@NotNull String message, @NotNull File file) {
        super(message);
        this.file = file;
    }

    @NotNull
    public File getFile() {
        return file;
    }
}
