package de.asedem.fileexplorer.util.exception;

public class FileCreationFailedException extends Exception {

    public FileCreationFailedException() {
        super("A user tried create a File. This action can't be executed!");
    }
}
