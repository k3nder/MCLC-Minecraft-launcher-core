package net.kender.core.sample;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.Kjson.Json;
import net.kender.core.UtilsFiles;

public class JsonOfVersion {


    /**
     * @param gameArgs list of game args of the game
     * @param jvmArgs args of java virtual machine
     * @param assetsIndex a {@link JsonNode} of assets index of the version
     * @param assets a assets num of the version
     * @param compilanceLevel compilance level num
     * @param downloads downloads {@link JsonNode} of the jars
     * @param lastVersionId id of the version
     * @param javaVersion a {@link JsonNode} of the java version
     * @param libraries a {@link JsonNode} of the libs to download
     * @param logging a {@link JsonNode} of the logging
     * @param mainClass a main class to run of mc
     * @param minimumLauncherVersion a minimum launcher version num
     * @param relaseTime a time of launch 
     * @param time a time
     * @param type a {@link VType} of the version
     * @param inheritsFrom or if custom is a id of the not custom to download
     */
    public List<String> gameArgs;
    public List<String> jvmArgs;
    public JsonNode assetsIndex;
    public String assets;
    public int compilanceLevel;
    public JsonNode downloads;
    public String lastVersionId;
    public JsonNode javaVersion;
    public JsonNode libraries;
    public JsonNode logging;
    public String mainClass;
    public int minimumLauncherVersion;
    public String relaseTime;
    public String time;
    public VType type;
    public String inheritsFrom;

    /**
     * create a new JsonOfVersion to seach
     * @param Json a file to get
     */
    public JsonOfVersion(File Json) {
        try {
            if (Json.exists() != true) {
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(Json);

            // set args variables
            JsonNode args = rootNode.get("arguments");

            if (!(args == null)) {
                JsonNode GameArgs = args.get("game");
                JsonNode JvmArgs = args.get("jvm");
                if (GameArgs != null) {
                    gameArgs = convert(GameArgs);
                }
                if (JvmArgs != null) {
                    jvmArgs = convert(JvmArgs);
                }
            }

            // set assets variables
            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null) {
                assetsIndex = assetsIndexNode;
            }

            JsonNode assetsNode = rootNode.get("assets");
            if (assetsNode != null) {
                assets = assetsNode.asText();
            }

            JsonNode compilanceLevelNode = rootNode.get("complianceLevel");
            if (compilanceLevelNode != null) {
                compilanceLevel = compilanceLevelNode.asInt();
            }

            // downloads set variables
            JsonNode downloadsNode = rootNode.get("downloads");
            if (downloadsNode == null) {
                inheritsFrom = rootNode.get("inheritsFrom").asText();
            } else {
                downloads = downloadsNode;
            }
            // last version id set variable

            JsonNode lastVersionIdNode = rootNode.get("id");
            if (lastVersionIdNode != null) {
                lastVersionId = lastVersionIdNode.asText();
            }

            // java version set variable
            javaVersion = rootNode.get("javaVersion");

            // libraries variable set
            if (rootNode.get("libraries") != null) {
                libraries = rootNode.get("libraries");
            } else {

            }

            // set logging variables
            logging = rootNode.get("logging");

            // set mainclass variable
            JsonNode mainClassNode = rootNode.get("mainClass");
            if (mainClassNode != null) {
                mainClass = mainClassNode.asText();
            }

            // set minimumLauncherVersion variable
            JsonNode minimumLauncherVersionNode = rootNode.get("minimumLauncherVersion");
            if (minimumLauncherVersionNode != null) {
                minimumLauncherVersion = minimumLauncherVersionNode.asInt();
            }

            // releaseTime set variable
            JsonNode relaseTimeNode = rootNode.get("releaseTime");
            if (relaseTimeNode != null) {
                relaseTime = relaseTimeNode.asText();
            }

            // set time variable
            JsonNode timeNode = rootNode.get("time");
            if (timeNode != null) {
                time = timeNode.asText();
            }

            // set type variable
            JsonNode typeNode = rootNode.get("type");
            if (typeNode != null) {
                type = VTC.toVType(typeNode.asText());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonOfVersion(URI url) {

            
            JsonNode rootNode = Json.ContentOf(url);

            // set args variables
            JsonNode args = rootNode.get("arguments");

            if (!(args == null)) {
                JsonNode GameArgs = args.get("game");
                JsonNode JvmArgs = args.get("jvm");
                if (GameArgs != null) {
                    gameArgs = convert(GameArgs);
                }
                if (JvmArgs != null) {
                    jvmArgs = convert(JvmArgs);
                }
            }

            // set assets variables
            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null) {
                assetsIndex = assetsIndexNode;
            }

            JsonNode assetsNode = rootNode.get("assets");
            if (assetsNode != null) {
                assets = assetsNode.asText();
            }

            JsonNode compilanceLevelNode = rootNode.get("complianceLevel");
            if (compilanceLevelNode != null) {
                compilanceLevel = compilanceLevelNode.asInt();
            }

            // downloads set variables
            JsonNode downloadsNode = rootNode.get("downloads");
            if (downloadsNode == null) {
                inheritsFrom = rootNode.get("inheritsFrom").asText();
            } else {
                downloads = downloadsNode;
            }
            // last version id set variable

            JsonNode lastVersionIdNode = rootNode.get("id");
            if (lastVersionIdNode != null) {
                lastVersionId = lastVersionIdNode.asText();
            }

            // java version set variable
            javaVersion = rootNode.get("javaVersion");

            // libraries variable set
            if (rootNode.get("libraries") != null) {
                libraries = rootNode.get("libraries");
            } else {
            }

            // set logging variables
            logging = rootNode.get("logging");

            // set mainclass variable
            JsonNode mainClassNode = rootNode.get("mainClass");
            if (mainClassNode != null) {
                mainClass = mainClassNode.asText();
            }

            // set minimumLauncherVersion variable
            JsonNode minimumLauncherVersionNode = rootNode.get("minimumLauncherVersion");
            if (minimumLauncherVersionNode != null) {
                minimumLauncherVersion = minimumLauncherVersionNode.asInt();
            }

            // releaseTime set variable
            JsonNode relaseTimeNode = rootNode.get("releaseTime");
            if (relaseTimeNode != null) {
                relaseTime = relaseTimeNode.asText();
            }

            // set time variable
            JsonNode timeNode = rootNode.get("time");
            if (timeNode != null) {
                time = timeNode.asText();
            }

            // set type variable
            JsonNode typeNode = rootNode.get("type");
            if (typeNode != null) {
                type = VTC.toVType(typeNode.asText());
            }

       
    }

    private List<String> convert(JsonNode g) {
        List<String> result = new ArrayList<String>();
        if (g.isArray()) {
            for (JsonNode element : g) {
                result.add(element.asText());
            }
        } else {
            System.out.println("El JSON no representa un array.");
        }
        return result;
    }
    public boolean isCustom(){
        return inheritsFrom != null;
    }
}