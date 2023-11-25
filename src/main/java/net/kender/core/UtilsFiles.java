package net.kender.core;


import java.io.File;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


import org.apache.commons.io.FileUtils;


/**
 *
 * @author krist
 */
public class UtilsFiles {

    private UtilsFiles() {
    }

    public static void createFile(String path, String extension, String fileName, String fileContent) {

        fileName = fileName + "." + extension;

        try {
            // Crea un objeto File que representa el archivo en el directorio especificado
            File file = new File(path, fileName);

            // Crea el archivo con el contenido especificado
            FileUtils.writeStringToFile(file, fileContent, "UTF-8");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(String url, String path, String name, String exten) {

        try {
            // Crear una URL a partir de la dirección del archivo .jar
            URI uri = new URI(url);
            URL urlR = uri.toURL();

            // Descargar el archivo .jar y guardarlo en el directorio destino con el nuevo
            // nombre
            FileUtils.copyURLToFile(urlR, new File(path, name + exten));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    
}
