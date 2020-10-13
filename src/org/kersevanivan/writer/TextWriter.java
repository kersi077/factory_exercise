package org.kersevanivan.writer;

import org.kersevanivan.vag.responseStationSearch.HaltestellenAPIResponse;
import org.kersevanivan.vag.responseStationSearch.Json;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Title: TextWriter</p>
 * <p>Description: </p>
 * <p>$LastChangedRevision: $</p>
 * <p>$Id: $</p>
 * <p>$LastChangedDate: $</p>
 * <p>$HeadURL: $</p>
 *
 * @author ivan
 * @version 13.10.20 12:38
 */
public class TextWriter implements Writer {
    private final String filepath;

    public TextWriter(String path) {
        this.filepath = path;
    }

    @Override
    public void write(String stringToWrite) {
        try {
            Json json = new Json();
            HaltestellenAPIResponse stations = json.getStation(stringToWrite);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(stations.getHaltestellenAsString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Oh my god! Could not write stations to file.");
            e.printStackTrace();
        }
    }
}
