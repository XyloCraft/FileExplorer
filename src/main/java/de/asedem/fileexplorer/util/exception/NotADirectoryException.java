package de.asedem.fileexplorer.util.exception;

public class NotADirectoryException extends Exception {

    public NotADirectoryException() {
        super("A user tried to navigate into a file, that is not a Directory!");
    }
}
