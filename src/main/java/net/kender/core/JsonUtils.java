package net.kender.core;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.net.ConnectException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author krist
 */
public class JsonUtils {

    public static String getUrlOfJsonOfVersion(String url, String version) {
        String urlFromJson = "";
        try {
            URI a = null;
            try {
                a = new URI(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            HttpURLConnection conn = (HttpURLConnection) a.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new ConnectException("Error al conectar a la URL. Código de respuesta: " + conn.getResponseCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(conn.getInputStream());

            Iterator<JsonNode> versionsIterator = rootNode.get("versions").iterator();

            while (versionsIterator.hasNext()) {
                JsonNode versionNode = versionsIterator.next();
                if (version.equals(versionNode.get("id").asText())) {
                    urlFromJson = versionNode.get("url").asText();
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
            URI uri = new URI(url);
            URL urlReform = uri.toURL();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlReform.openStream()));
            jsonString = new StringBuilder();

            jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    public static String[] getListOfVersions(String jsonUrl) throws URISyntaxException ,IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        URI uri = new URI(jsonUrl);
        URL urlReform = uri.toURL();

        JsonNode rootNode = objectMapper.readTree(urlReform);

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

    public static String readAssetsIndexUrlFromJson(String filePath) {
        String urlFromJson = "";
        try {
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null && assetsIndexNode.has("url")) {
                urlFromJson = assetsIndexNode.get("url").asText();
            } else {
                throw new IOException("not found node AssetsIndex");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlFromJson;
    }

}



    

