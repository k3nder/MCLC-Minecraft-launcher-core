package net.kender.core.sample.EXTRAS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

import net.kender.core.JsonUtils;
import net.kender.core.UtilsFiles;
import net.kender.core.sample.CommandConstructor;
import net.kender.core.sample.VMC;

public class EXTRAS {

    public static void createJsonVersion(Path __DEST__, VMC __VERSION__) {
        UtilsFiles.createFile(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\", "json", __VERSION__.toString(),
                JsonUtils.getJsonVersion(__VERSION__.getUrl().toString()));
    }

    public static void natives(Path libs, Path jdk) {
        // Utiliza el objeto Files para obtener un Stream de Paths
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(libs)) {
            // Itera sobre los Paths e imprime sus nombres
            for (Path archivo : stream) {
                if (archivo.toString().contains("natives")) {
                    unZIP(archivo.toFile(), Path.of(jdk + "\\"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String quitarUltimaParteRuta(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            return ruta;
        }

        int ultimoSeparador = ruta.lastIndexOf(File.separator);
        if (ultimoSeparador != -1) {
            return ruta.substring(0, ultimoSeparador);
        } else {
            return ruta;
        }
    }

    public static void createFile(File d, String fileContent) {
        // Crea el archivo con el contenido especificado
        try {
            FileUtils.writeStringToFile(d, fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // log( SEPARATOR);
        // log( "the arch is creted: " + d.getAbsolutePath());
        // log( SEPARATOR);
    }

    public static void download(URI url, File h) {
        try {
            FileUtils.copyURLToFile(url.toURL(), h);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run(CommandConstructor command, File Dyrectory) {
        try {
            System.out.println(command.toString());
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command.toString());
            processBuilder.directory(Dyrectory);
            processBuilder.redirectErrorStream(true); // Redirige la salida de ERROR al mismo flujo de entrada

            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void unZIP(File zipFilePath, Path destDirectory) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + "\\" + zipEntry.getName());

                // Si el nombre termina con "/" es un directorio
                if (fileName.endsWith("/")) {
                    newFile.mkdirs();
                } else {
                    // Si no, es un archivo y se descomprime
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }

                zipEntry = zis.getNextEntry();

            }
        }
    }

    public static void unZIP(File zipFilePath, Path destDirectory, String rename) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + "\\" + zipEntry.getName());

                // Si el nombre termina con "/" es un directorio
                if (fileName.endsWith("/")) {
                    newFile.mkdirs();
                } else {
                    // Si no, es un archivo y se descomprime
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }

                zipEntry = zis.getNextEntry();
                File d = new File(zipFilePath.toString());
                d.renameTo(new File(zipFilePath.toString().replace(zipFilePath.getName(), rename)));
            }
        }
    }

    public static String read(File e) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(e));
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea); // Imprime cada línea del archivo
            }
            lector.close();
            return linea;
        } catch (IOException e1) {
            e1.printStackTrace();
            return "";
        }
    }
}
