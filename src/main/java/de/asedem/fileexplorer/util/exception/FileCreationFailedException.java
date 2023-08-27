package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileCreationFailedException extends FileException {

    public FileCreationFailedException(@NotNull File file) {
        super("A user tried create a File. This action can't be executed!", file);
    }
}
