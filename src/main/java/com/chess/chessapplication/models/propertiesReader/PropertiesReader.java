package com.chess.chessapplication.models.propertiesReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {
    private static final Logger logger = Logger.getLogger(PropertiesReader.class.getName());

    public static Optional<Properties> readProperties(String path){
        Optional<Properties> properties;
        try(FileReader reader = new FileReader(path)){
            Properties readProperties = new Properties();
            readProperties.load(reader);
            properties = Optional.of(readProperties);
        } catch(IOException exception){
            properties = Optional.empty();
            logger.log(Level.WARNING,"could not read properties from file",exception);
        }
        return properties;
    }
}
