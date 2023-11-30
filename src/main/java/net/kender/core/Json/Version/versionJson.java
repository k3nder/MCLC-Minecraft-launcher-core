package net.kender.core.Json.Version;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.Kjson.Json;
import net.kender.core.Json.libreries.librarie;



public class versionJson {
    @JsonIgnore
    public String arguments;
    public String inheritsFrom;
    public assetIndex assetIndex;
    public String assets;
    public int complianceLevel;
    public downloadsClient downloads;
    public String id;
    public javaV javaVersion;
    public ArrayList<librarie> libraries;
    public String mainClass;
    public int minimumLauncherVersion;
    public String releaseTime;
    public String time;
    public String type; 
    public log logging;

    public boolean isCustom(){
        return inheritsFrom != null;
    }
    public static versionJson load(File a) throws IOException{
        return new ObjectMapper().readValue(a, versionJson.class);
    }
    public static versionJson load(URI a) throws IOException{
        return new ObjectMapper().readValue(Json.SContentOf(a),versionJson.class);
    }
}
