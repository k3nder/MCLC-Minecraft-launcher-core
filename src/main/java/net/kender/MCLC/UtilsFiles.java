/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kender.MCLC;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author krist
 */
public class UtilsFiles {
    public static void createFile(String Path,String extension ,String FileName,String FileContent){
        
       FileName = FileName + "." + extension;

        try {
            // Crea un objeto File que representa el archivo en el directorio especificado
            File file = new File(Path, FileName);

            // Crea el archivo con el contenido especificado
            FileUtils.writeStringToFile(file, FileContent, "UTF-8");
            System.out.println("----------");
            System.out.println("the arch is creted: " + file.getAbsolutePath());
            System.out.println("----------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void download(String url,String path,String name, String exten){

        try {
            // Crear una URL a partir de la dirección del archivo .jar
            URL urlR = new URL(url);

            // Descargar el archivo .jar y guardarlo en el directorio destino con el nuevo nombre
            FileUtils.copyURLToFile(urlR, new File(path, name + exten ));

            System.out.println("the dowload has completed: " + url);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void run(String command){
        System.out.println("inatlizin the command: " + command);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true); // Redirige la salida de error al mismo flujo de entrada

            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            int codigoSalida = process.waitFor();
            System.out.println("exit code: " + codigoSalida);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void createDirectory(String Path,String name){
        if(name == null){
        try {
            Path path = Paths.get(Path);
            // Crea el directorio si no existe
            Files.createDirectories(path);
            System.out.println("Directorio directory: " + Path + " created");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("directory arledy exist");
        }
        }else{
            File carpeta = new File(Path + name);

            if (!carpeta.exists()) {
                if (carpeta.mkdir()) {
                    System.out.println("carpet: " + name + " created");
                } else {
                    System.out.println("Error in create carpet: " + name);
                }
            } else {
                System.out.println("the carpet arledy exist");
            }
        }
    }
    public static void unZIP(String zipFilePath, String destDirectory) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + fileName);

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
            System.out.println("----------");
            System.out.println("the unzip has completed: " + zipFilePath);
            System.out.println("----------");
        }
    }
    public static void getNatives(String Directory, String NativesDesti, String extend) throws IOException {
        File carpeta = new File(Directory);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().contains("natives")) {
                        Path NativesSources = Paths.get(Directory + "\\" + archivo.getName());
                        Path NativesDest = Paths.get(NativesDesti + "\\" + archivo.getName() + extend);
                        try{
                            Files.copy(NativesSources, NativesDest);
                            unZIP(NativesDesti + "\\" + archivo.getName() + extend, NativesDesti + "\\");
                        } catch (FileAlreadyExistsException e) {
                            System.out.println("----------");
                            System.out.println("the ach arledy exist" );
                            System.out.println("----------");
                        }

                    }
                }
            }
        } else {
            System.err.println("El directorio especificado no existe o no es válido.");
        }


    }
    public static String[] getListOfCarpets(String Directory) {
        // Creamos un objeto File con la ruta del directorio
        File directorioActual = new File(Directory);

        String[] nombresCarpetas = null;

        // Verificamos si el objeto File representa un directorio válido
        if (directorioActual.isDirectory()) {
            // Obtenemos un array de objetos File que representan las carpetas dentro del directorio
            File[] carpetas = directorioActual.listFiles(File::isDirectory);

            // Verificamos si el array no es nulo y contiene elementos
            if (carpetas != null && carpetas.length > 0) {
                // Creamos un array de String para almacenar los nombres de las carpetas
                nombresCarpetas = new String[carpetas.length];

                // Iteramos sobre el array de carpetas y almacenamos sus nombres en el array de String
                for (int i = 0; i < carpetas.length; i++) {
                    nombresCarpetas[i] = carpetas[i].getName();
                }

                // Ahora el array "nombresCarpetas" contiene los nombres de todas las carpetas
                // dentro del directorio y puedes utilizarlo según tus necesidades.
                for (String nombreCarpeta : nombresCarpetas) {

                }
            } else {
                System.out.println("No hay carpetas dentro del directorio.");
            }
        } else {
            System.out.println("La ruta proporcionada no corresponde a un directorio válido.");
        }

        return nombresCarpetas;
    }    
}


