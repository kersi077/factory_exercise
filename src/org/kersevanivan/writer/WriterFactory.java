package org.kersevanivan.writer;

import java.util.Optional;

/**
 * <p>Title: WriterFactory</p>
 * <p>Description: </p>
 * <p>$LastChangedRevision: $</p>
 * <p>$Id: $</p>
 * <p>$LastChangedDate: $</p>
 * <p>$HeadURL: $</p>
 *
 * @author ivan
 * @version 13.10.20 13:12
 */
public class WriterFactory {

    private String JSON_SUFFIX = ".json";
    private String TXT_SUFFIX = ".txt";

    public Optional<Writer> getWriter(String path) {
        if( path.endsWith(JSON_SUFFIX)) {
            return Optional.of(new JsonWriter(path));
        } else if( path.endsWith(TXT_SUFFIX)) {
            return Optional.of(new TextWriter(path));
        } else
            return Optional.empty();
    }
}
