package de.asedem.fileexplorer.util.exception;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileNotExistsException extends FileException {


    public FileNotExistsException(@NotNull File file) {
        super("A user tried to access a File, that not exists!", file);
    }
}
