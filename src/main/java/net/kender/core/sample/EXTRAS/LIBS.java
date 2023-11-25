package net.kender.core.sample.EXTRAS;

import static net.kender.core.sample.EXTRAS.EXTRAS.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;


public class LIBS {


    public static String getlibs(Path __DEST__, JsonNode libs) {


        String pathLibs = "";

        for (JsonNode libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto
            // "libraries"
            JsonNode dowloads = libraryNode.get("downloads");
            

            // Verificar si "downloads" no es nulo

            if (dowloads != null && dowloads.isObject()) {
                try {
                    dons(__DEST__, libs);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
            } else if (libraryNode.get("url") != null) {
                try {
                    
                    int indexOfp = libraryNode.get("name").asText().lastIndexOf(":");
                    String subversion = libraryNode.get("name").asText().substring(indexOfp + 1,
                            libraryNode.get("name").asText().length());
                    String etc = libraryNode.get("name").asText().substring(0, indexOfp);
                    int indexOf = libraryNode.get("name").asText().indexOf(":");
                    String nana = libraryNode.get("name").asText().substring(indexOf + 1, indexOfp);
                    download(new URI(libraryNode.get("url").asText() + etc.replace(":", "/").replace(".", "/") + "/" + subversion + "/"
                            + nana + "-" + subversion + ".jar"), new File(__DEST__ + "\\" + nana + ".jar"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new IOException("not found url in a json");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return pathLibs;

    }


    // private static String quitarUltimaParteRuta(String ruta) {
    // if (ruta == null || ruta.isEmpty()) {
    // return ruta;
    // }

    // int ultimoSeparador = ruta.lastIndexOf(File.separator);
    // if (ultimoSeparador != -1) {
    // return ruta.substring(0, ultimoSeparador);
    // } else {
    // return ruta;
    // }
    // }
    public static String obtenerNombreArchivo(String ruta) {
        // Crear un objeto File a partir de la ruta
        File archivo = new File(ruta);
        // Obtener el nombre del archivo
        return archivo.getName();

    }

    public static void dons(Path dest, JsonNode a) throws URISyntaxException {

        JsonNode libsNode = a;

        // Iterar a través de las librerías
        for (JsonNode libNode : libsNode) {
            JsonNode rulesNode = libNode.get("rules");
            if (rulesNode == null) {
                if (libNode.get("downloads").get("artifact") != null) {
                    JsonNode libraryName = libNode.get("downloads").get("artifact").get("path");
                    download(new URI(libNode.get("downloads").get("artifact").get("url").asText()),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName.asText())));

                }
                continue;
            }

            // Verificar reglas para la compatibilidad del sistema operativo
            boolean allowDownload = true;
            for (JsonNode ruleNode : rulesNode) {
                String action = ruleNode.get("action").asText();
                JsonNode osNode = ruleNode.get("os");

                if (osNode != null) {
                    String osName = osNode.get("name").asText();
                    if ("allow".equals(action) && !isCurrentOSCompatible(osName)) {
                        allowDownload = false;
                        break;
                    } else if ("disallow".equals(action) && isCurrentOSCompatible(osName)) {
                        allowDownload = false;
                        break;
                    }
                }
            }
            if (libNode.has("natives")) {
                if (libNode.get("natives").has("windows")) {
                    JsonNode libraryName = libNode.get("downloads").get("classifiers").get("natives-windows");
                    if (libNode.get("downloads").get("classifiers").get("natives-windows") == null) {
                        libraryName = libNode.get("downloads").get("classifiers").get("natives-windows-64");
                        if(libNode.get("downloads").get("classifiers").get("natives-windows-64") == null){
                            continue;
                        }
                    download(
                            new URI(libNode.get("downloads").get("classifiers").get("natives-windows-64").get("url")
                                    .asText()),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName.get("path").asText())));
                    } else {
                        libraryName = libNode.get("downloads").get("classifiers").get("natives-windows").get("path");
                        download(
                            new URI(libNode.get("downloads").get("classifiers").get("natives-windows").get("url")
                                    .asText()),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName.asText())));
                    }
                    
                }
            }
            if (libNode.get("downloads").get("classifiers") != null) {
                if (libNode.get("downloads").get("classifiers").get("natives-windows-64") != null) {
                    JsonNode libraryName = libNode.get("downloads").get("classifiers").get("natives-windows-64")
                            .get("path");
                    download(
                            new URI(libNode.get("downloads").get("classifiers").get("natives-windows-64").get("url")
                                    .asText()),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName.asText())));
                }
            }

            // Descargar la librería si es compatible
            if (allowDownload) {
                if (libNode.get("downloads").get("artifact") != null) {
                    String downloadUrl = libNode.get("downloads").get("artifact").get("url").asText();
                    System.out.println("Descargando librería desde: " + downloadUrl);
                    JsonNode libraryName = libNode.get("downloads").get("artifact").get("path");
                    download(new URI(downloadUrl), new File(dest + "\\" + quitarUltimaParteRuta(libraryName.asText())));
                    if (libNode.get("downloads").get("classifiers") != null) {
                        libraryName = libNode.get("downloads").get("classifiers").get("natives-windows").get("path");
                        download(
                                new URI(libNode.get("downloads").get("classifiers").get("natives-windows").get("url")
                                        .asText()),
                                new File(dest + "\\" + quitarUltimaParteRuta(libraryName.asText())));

                    }
                }

            } else {

            }
        }
    }

    private static boolean isCurrentOSCompatible(String osName) {
        // Lógica para verificar la compatibilidad con el sistema operativo actual
        String currentOS = System.getProperty("os.name").toLowerCase();
        return currentOS.contains(osName.toLowerCase());
    }

    private static String quitarUltimaParteRuta(String a) {
        Path b = Path.of(a);
        return b.getFileName().toString();
    }
}
