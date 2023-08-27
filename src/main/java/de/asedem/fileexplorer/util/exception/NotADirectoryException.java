package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class NotADirectoryException extends FileException {

    public NotADirectoryException(@NotNull File file) {
        super("A user tried to navigate into a file, that is not a Directory!", file);
    }
}
