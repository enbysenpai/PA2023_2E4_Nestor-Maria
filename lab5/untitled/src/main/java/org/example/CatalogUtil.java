package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog, String path)
            throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path), catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Catalog catalog = objectMapper.readValue(
                    new File(path),
                    Catalog.class);
            return catalog;
        } catch (IOException e) {
            throw new InvalidCatalogException(e);
        }
    }

    public static void view(Document doc) {
        String location = doc.getLocation();
        if (location.startsWith("http"))
            browse(location);
        else if (location.endsWith(".txt"))
            open(location);
        else
            System.out.println("Can't open location: " + location);
    }

    private static void browse(String location) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(location));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void open(String location) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(location));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
