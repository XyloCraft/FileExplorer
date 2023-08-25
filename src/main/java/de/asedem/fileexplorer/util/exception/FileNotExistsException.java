package de.asedem.fileexplorer.util.exception;

public class FileNotExistsException extends Exception {

    public FileNotExistsException() {
        super("A user tried to access a File, that not exists!");
    }
}
