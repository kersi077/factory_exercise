package org.kersevanivan.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Title: JsonWriter</p>
 * <p>Description: </p>
 * <p>$LastChangedRevision: $</p>
 * <p>$Id: $</p>
 * <p>$LastChangedDate: $</p>
 * <p>$HeadURL: $</p>
 *
 * @author ivan
 * @version 13.10.20 12:37
 */
public class JsonWriter implements  Writer {
    private final String filepath;

    public JsonWriter(String path) {
        this.filepath = path;
    }

    @Override
    public void write(String stringToWrite) {
       // TODO: YOUR CODE HERE!
    }
}
