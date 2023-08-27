package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CannotDeleteFileException extends FileException {

    public CannotDeleteFileException(@NotNull File file) {
        super("File or Directory cannot be deleted!", file);
    }
}
