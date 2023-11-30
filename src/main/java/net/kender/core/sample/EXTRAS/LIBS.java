package net.kender.core.sample.EXTRAS;

import static net.kender.core.sample.EXTRAS.EXTRAS.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;

import net.kender.core.Json.libreries.librarie;
import net.kender.core.Json.libreries.os;
import net.kender.core.Json.libreries.rule;

import com.fasterxml.jackson.databind.JsonNode;


public class LIBS {


    public static String getlibs(Path __DEST__, ArrayList<librarie> libs) {


        String pathLibs = "";

        for (librarie libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto
            // "libraries"
            

            // Verificar si "downloads" no es nulo

            if (libraryNode.downloads != null) {
                try {
                    dons(__DEST__, libs);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
            } else if (libraryNode.url != null) {
                try {
                    
                    int indexOfp = libraryNode.name.lastIndexOf(":");
                    String subversion = libraryNode.name.substring(indexOfp + 1,
                            libraryNode.name.length());
                    String etc = libraryNode.name.substring(0, indexOfp);
                    int indexOf = libraryNode.name.indexOf(":");
                    String nana = libraryNode.name.substring(indexOf + 1, indexOfp);
                    download(new URI(libraryNode.url + etc.replace(":", "/").replace(".", "/") + "/" + subversion + "/"
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

    public static void dons(Path dest, ArrayList<librarie> a) throws URISyntaxException {

        // Iterar a través de las librerías
        for (librarie libNode : a) {
            if (libNode.rules == null) {
                if (libNode.downloads.artifact != null) {
                    String libraryName = libNode.downloads.artifact.path;
                    download(new URI(libNode.downloads.artifact.url),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));

                }
                continue;
            }

            // Verificar reglas para la compatibilidad del sistema operativo
            boolean allowDownload = true;
            for (rule ruleNode : libNode.rules) {
                String action = ruleNode.action;
                os osNode = ruleNode.os;

                if (osNode != null) {
                    String osName = osNode.name;
                    if ("allow".equals(action) && !isCurrentOSCompatible(osName)) {
                        allowDownload = false;
                        break;
                    } else if ("disallow".equals(action) && isCurrentOSCompatible(osName)) {
                        allowDownload = false;
                        break;
                    }
                }
            }
            if (libNode.natives != null) {
                if (libNode.natives.windows != null) {
                    String libraryName = libNode.downloads.classifiers.windows.path;
                    if (libNode.downloads.classifiers.windows == null) {
                        libraryName = libNode.downloads.classifiers.windows64.path;
                        if(libNode.downloads.classifiers.windows64 == null){
                            continue;
                        }
                    download(
                            new URI(libNode.downloads.classifiers.windows64.url),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));
                    } else {
                        libraryName = libNode.downloads.classifiers.windows.path;
                        download(
                            new URI(libNode.downloads.classifiers.windows.url),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));
                    }
                    
                }
            }
            if (libNode.downloads.classifiers != null) {
                if (libNode.downloads.classifiers.windows64 != null) {
                    String libraryName = libNode.downloads.classifiers.windows64.path;
                    download(
                            new URI(libNode.downloads.classifiers.windows64.url),
                            new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));
                }
            }

            // Descargar la librería si es compatible
            if (allowDownload) {
                if (libNode.downloads.artifact != null) {
                    String downloadUrl = libNode.downloads.artifact.url;
                    System.out.println("Descargando librería desde: " + downloadUrl);
                    String libraryName = libNode.downloads.artifact.path;
                    download(new URI(downloadUrl), new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));
                    if (libNode.downloads.classifiers != null) {
                        libraryName = libNode.downloads.classifiers.windows.path;
                        download(
                                new URI(libNode.downloads.classifiers.windows.url),
                                new File(dest + "\\" + quitarUltimaParteRuta(libraryName)));

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
