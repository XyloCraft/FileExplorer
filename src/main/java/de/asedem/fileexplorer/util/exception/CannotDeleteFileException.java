package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CannotDeleteFileException extends Exception {

    private final File file;

    public CannotDeleteFileException(@NotNull File file) {
        super("File or Directory cannot be deleted!");
        this.file = file;
    }

    @NotNull
    public File getFile() {
        return file;
    }
}
