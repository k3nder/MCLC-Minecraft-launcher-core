/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kender.MCLC;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author krist
 */
public class JsonUtils {
    public static String GetUrlOfJsonOfVersion(String url, String Version) {
        String urlFromJson = "";
        try {
            URL urlReform = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlReform.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Error al conectar a la URL. Código de respuesta: " + conn.getResponseCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(conn.getInputStream());

            Iterator<JsonNode> versionsIterator = rootNode.get("versions").iterator();

            while (versionsIterator.hasNext()) {
                JsonNode versionNode = versionsIterator.next();
                if (Version.equals(versionNode.get("id").asText())) {
                    urlFromJson = versionNode.get("url").asText();
                    System.out.println("URL encontrada: " + urlFromJson);
                    break;
                }
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlFromJson;
    }

    public static String getJsonVersion(String url) {
        StringBuilder jsonString = new StringBuilder();

        try {
            URL urlReform = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlReform.openStream()));

            jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            // Aquí tienes el contenido del JSON en la variable jsonString
            System.out.println("JSON descargado:");
            System.out.println(jsonString.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    public static String readJsonVersion(String filePath) throws IOException {
        String url = "";
        // Crear un ObjectMapper para leer el JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Leer el archivo JSON y convertirlo en un objeto JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Obtener el nodo "downloads"
        JsonNode downloadsNode = rootNode.get("downloads");
        if (downloadsNode != null) {
            // Obtener el nodo "client" dentro del nodo "downloads"
            JsonNode clientNode = downloadsNode.get("client");

            // Obtener el valor de la propiedad "url" dentro del nodo "client"
            url = clientNode.get("url").asText();

            System.out.println("URL del cliente: " + url);
        }
        return url;
    }

    public static String[] GetListOfVersions(String jsonUrl) throws MalformedURLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new URL(jsonUrl));

        JsonNode versionsNode = rootNode.get("versions");
        if (versionsNode != null && versionsNode.isArray()) {
            List<String> versionIdsList = new ArrayList<>();
            for (JsonNode versionNode : versionsNode) {
                JsonNode idNode = versionNode.get("id");
                if (idNode != null) {
                    versionIdsList.add(idNode.asText());
                }
            }
            return versionIdsList.toArray(new String[0]);
        }

        return new String[0];
    }

    public static String ReadFileAssetsIndex(String filePath) {
        String urlFromJson = "";
        try {
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null && assetsIndexNode.has("url")) {
                urlFromJson = assetsIndexNode.get("url").asText();
                System.out.println(urlFromJson);
            } else {
                System.out.println("No se encontró el nodo assetsIndex o el campo url en el JSON.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlFromJson;
    }

    public static String getMainClass(String path) throws IOException {
        File jsonFile = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        JsonNode MainClass = rootNode.get("mainClass");

        return MainClass.asText();
    }

    public static String getAssesNum(String Path) throws IOException {
        File jsonFile = new File(Path);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        JsonNode MainClass = rootNode.get("assets");
        if (MainClass != null) {
            System.out.println(MainClass.asText());
            return MainClass.asText();
        }
        return null;
    }

    public static String quitarUltimaParteRuta(String ruta) {
        String name = ruta.substring(ruta.lastIndexOf('/') + 1);
        return name;
    }

    public static int getLibs(String pathJson, String version, String pathOfLibs) throws IOException {
        int indexOfUlibs = 0;
        File json = new File(pathJson);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        System.out.println("dowloading the libs");
        System.out.println("--------");

        JsonNode libs = rootNode.get("libraries");

        for (JsonNode libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto "libraries"
            JsonNode dowloads = libraryNode.get("downloads");

            indexOfUlibs = dowloads.size();

            // Verificar si "downloads" no es nulo
            if (dowloads != null && dowloads.isObject()) {

                boolean artexi = dowloads.has("artifact");
                if (artexi) {
                    JsonNode art = dowloads.get("artifact");

                    JsonNode urlNode = art.get("url");
                    JsonNode PathNode = art.get("path");

                    String path = PathNode.asText();
                    String url = urlNode.asText();

                    String name = quitarUltimaParteRuta(path);
                    System.out.println("----------");
                    System.out.println("dowloadig: " + url);
                    System.out.println("-----");
                    Path p = Path.of(pathOfLibs + "\\" + version + "\\" + name + ".jar");
                    if(Files.exists(p)){
                        System.out.println("the dependecy alredy dowloaded");
                        System.out.println("----------");
                    }else {
                        UtilsFiles.download(url, pathOfLibs + "\\" + version + "\\", name, ".jar");

                        System.out.println("save in " + pathOfLibs + "\\" + version + "\\");

                        System.out.println("dowloaded completed");
                        System.out.println("----------");
                    }
                }


                boolean cassexist = dowloads.has("classifiers");

                if (cassexist) {
                    System.out.println("classexists");
                    JsonNode classifiersNode = dowloads.get("classifiers");
                    boolean nativesOS = classifiersNode.has("natives-windows");
                    if (nativesOS) {
                        System.out.println("nodeOS");
                        JsonNode natives = classifiersNode.get("natives-windows");
                        JsonNode nativesPathNode = natives.get("url");
                        JsonNode urlnatives = natives.get("url");
                        String urlNatives = urlnatives.asText();
                        String nativesPath = nativesPathNode.asText();

                        String nameos = quitarUltimaParteRuta(nativesPath);

                        System.out.println("dowloading: " + urlnatives);

                        UtilsFiles.download(urlNatives, pathOfLibs + "\\" + version + "\\", nameos, ".jar");

                        System.out.println("save in " + pathOfLibs + "\\" + version + "\\");

                        System.out.println("dowloaded completed");

                    }
                    boolean nativesArch = classifiersNode.has("natives-windows-64");
                    if (nativesArch) {
                        JsonNode archNode = classifiersNode.get("natives-windows-64");

                        JsonNode urlArch = archNode.get("url");
                        JsonNode PathArch = archNode.get("path");

                        String urlS = urlArch.asText();
                        String pathArch = PathArch.asText();

                        String nameArch = quitarUltimaParteRuta(pathArch);

                        System.out.println("dowloading: " + urlS);

                        UtilsFiles.download(urlS, pathOfLibs + "\\" + version + "\\", nameArch, ".jar");

                        System.out.println("save in " + pathOfLibs + "\\" + version + "\\");

                        System.out.println("dowloaded completed");
                    }
                }
            } else {
                System.out.println("no");
            }

        }
        return indexOfUlibs;

    }

    public static void registerVersionInJson(String version, String Type, String UUIDdataBin, String pathOfJson, String name, String Username, String GameDir, String loader, String[] mods,Boolean TypeR) throws IOException {
        // Cargar el archivo JSON existente
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(pathOfJson);

        try {
            JsonNode rootNode = objectMapper.readTree(archivoJson);

            // Verificar si el objeto "objeto_existente" ya existe en el JSON
            if (rootNode.has("profiles") && rootNode.get("profiles").isObject()) {
                ObjectNode newObj = (ObjectNode) rootNode.get("profiles");

                //ArrayNode arrayNode = objectMapper.valueToTree(mods);

                // Crear el nuevo objeto "nuevo_objeto" y agregar propiedades
                ObjectNode obj = objectMapper.createObjectNode();
                if(TypeR){
                    obj.put("Type", Type);
                }
                obj.put("lastVersionId", version);
                obj.put("UUIDdataBin", UUIDdataBin);
                obj.put("name", Username);
                obj.put("GameDir", GameDir);
                obj.put("loader", loader);
                //obj.put("mods", arrayNode);

                System.out.println("registring dats in json " + archivoJson);
                System.out.println("dats");
                System.out.println("----------");
                System.out.println("lastVersionId: " + version);
                System.out.println("UUIDdataBin: " + UUIDdataBin);
                System.out.println("UserName: " + Username);
                System.out.println("GameDir: " + GameDir);
                System.out.println("loader: " + loader);
                System.out.println("----------");

                // Agregar el nuevo objeto al objeto existente
                newObj.set(name, obj);

                // Guardar el JSON modificado en el archivo
                objectMapper.writeValue(archivoJson, rootNode);

                System.out.println("Se ha modificado el archivo JSON correctamente.");
            } else {
                System.out.println("El objeto 'objeto_existente' no fue encontrado en el JSON.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer/escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static List<String> getProfiles(String path) throws IOException {
        List<String> profiles = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(path);

        JsonNode rootNode = objectMapper.readTree(archivoJson);

        JsonNode pro = rootNode.get("profiles");

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = pro.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = fieldsIterator.next();
            String key = entry.getKey();
            System.out.println("version profile: " + key);
            profiles.add(key);

        }
        return profiles;
    }

    public static String GET(String path, String indexS, String GETp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(path);

        JsonNode rootNode = objectMapper.readTree(archivoJson);

        JsonNode pro = rootNode.get("profiles");

        System.out.println("----------");
        System.out.println("get method has inalinting");
        System.out.println("----------");
        System.out.println("profile: " + indexS);
        System.out.println("get: " + GETp);
        System.out.println("----------");

        JsonNode v = pro.get(indexS);

        JsonNode UU = v.get(GETp);
        if (UU != null) {
            return UU.asText();
        } else {
            return null;
        }

    }

    public static String GCVI(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(path);

        JsonNode rootNode = objectMapper.readTree(archivoJson);

        JsonNode CVI = rootNode.get("inheritsFrom");
        System.out.println("----------");
        System.out.println("version for client vanilla for loader");
        System.out.println("----------");
        System.out.println("version: " + CVI);
        System.out.println("----------");
        if(CVI != null){
            return CVI.asText();
        }
        return null;
    }

    public static void GCL(String path,String output) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(path);

        JsonNode rootNode = objectMapper.readTree(archivoJson);
        System.out.println("geting libs for loader");
        JsonNode libs = rootNode.get("libraries");

        for (JsonNode libraryNode : libs) {
            String name = libraryNode.get("name").asText();
            String url = libraryNode.get("url").asText();
            String version = extractVersion(name);

            String NameVF = name.replace(version, "");

            String transformedName = transformName(NameVF);

            NameVF = transformedName + version;

            String[] parts = name.split(":");

            String middlePart = "";

            if (parts.length >= 3) {
                middlePart = parts[1];
                System.out.println("Parte intermedia: " + middlePart);
            } else {
                System.out.println("El String no tiene el formato esperado.");
            }

            transformedName = transformedName.replaceFirst("-","/");

            int lastIndex = transformedName.lastIndexOf("-");
            if (lastIndex >= 0) {
                String firstPart = transformedName.substring(0, lastIndex);
                String secondPart = transformedName.substring(lastIndex + 1);
                String replacedStr = firstPart + "" + secondPart;

                System.out.println("String original: " + transformedName);
                System.out.println("String modificado: " + replacedStr);
                transformedName = replacedStr;
            } else {
                System.out.println("No se encontró la última ocurrencia del carácter.");
            }

            String Url = url + transformedName + "/" + version + "/" + middlePart + "-" + version + ".jar";


            // Imprimir los resultados
            System.out.println("Nombre original: " + name);
            System.out.println("Nombre transformado: " + transformedName);
            System.out.println(NameVF);
            System.out.println("Versión: " + version);
            System.out.println("Url: " + Url);
            System.out.println("-----");
            UtilsFiles.download(Url,output,NameVF + "-" + version,".jar");
            {
            }

        }
    }
    private static String extractVersion(String name) {
        int lastColonIndex = name.lastIndexOf(":");
        return name.substring(lastColonIndex + 1);
    }

    private static String transformName(String name) {
        String transformed = name.replace(":", "-");
        transformed = transformed.replace(".", "/");
        return transformed;
    }
    public static String getUrl(String url,String objToGet) throws IOException {
        URL urlReform = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlReform.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            System.out.println("Error al conectar a la URL. Código de respuesta: " + conn.getResponseCode());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(conn.getInputStream());

        JsonNode r = rootNode.get("record");

        JsonNode v = r.get("version");

        JsonNode g = v.get(objToGet);
        return g.toString();

    }
}



    

