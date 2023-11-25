package net.kender.core.sample.Quikplays;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;


import net.kender.Kjson.Json;
import net.kender.Utils.resolution;
import net.kender.core.sample.Manifest;
import net.kender.core.sample.VMC;
import net.kender.core.sample.VTC;
import net.kender.core.sample.VType;
import static net.kender.core.sample.MC.MINECRAFT_PATH;

public class profile {
    private VMC __VERSION__ = new Manifest().getLastVersion();
    private Path __GAMEDIR__ = Path.of(MINECRAFT_PATH);
    private Path __JRE__ = Path.of("default");
    private resolution __RESOLUTION__ = null;
    private String __JAVA_ARGS__ = null;
    private String name = "def";
    private String key;
    private VType type;
    
    private profile(Map<String,String> map){
        // registra los argumentos en las variables si estan en el mapa
        if(map.containsKey("javaArgs")) __JAVA_ARGS__ = map.get("javaArgs");
        if(map.containsKey("lastVersionId")) __VERSION__ = new Manifest().getVersion(map.get("lastVersionId"));
        if(map.containsKey("name")) name = map.get("name");
        if(map.containsKey("type")) type = VTC.toVType(map.get("type"));
        if(map.containsKey("width")) __RESOLUTION__ = resolution.of(Double.parseDouble(map.get("width")),Double.parseDouble(map.get("height")));
        if(map.containsKey("gameDir")) __GAMEDIR__ = Path.of(map.get("gameDir"));
        if(map.containsKey("javaDir")) __JRE__ = Path.of(map.get("javaDir"));
        key = map.get("key");
    }
    /**
     * gets a list of all profiles in a @param a
     * @return a list of profiles
     */
    public static List<profile> readAll(File a){
        try {
            // list es la lista que va a contener los profile
            List<profile> list = new ArrayList<profile>();
            JsonNode root = new Json(a).getRootNode();
            // obten en nodo profiles
            JsonNode profiles = root.get("profiles");

            // verifica si profiles es un objeto
            if (profiles.isObject()) {
                // si es asi inera por cada uno de las keys en el
                profiles.fieldNames().forEachRemaining(fieldName -> {
                    // obten el nodo de el profile
                    JsonNode l = profiles.get(fieldName);
                    // crea el mapa que contiene los datos
                    Map<String,String> mp = new HashMap<String,String>();
                    // registra en el mapa la key
                    mp.put("key",fieldName);

                    // verifica si l es un objeto
                    if (l.isObject()) {
                        // itera sobre cada undo de las keys
                        l.fieldNames().forEachRemaining(fieldName2 -> {
                            // verifica si existe resolution
                            if(fieldName2.equals("resolution")){
                                //si es asi regitra en el mapa las resoluciones
                                mp.put("height",l.get(fieldName2).get("height").asText());
                                mp.put("width", l.get(fieldName2).get("width").asText());
                            }else{
                                // si no registra los datos
                                mp.put(fieldName2, l.get(fieldName2).asText());
                            }
                        });
                    }
                    //crea el objeto profile con el mapa
                    profile pr = new profile(mp);
                    // añade el objeto pr a la lista
                    list.add(pr);
                });
            }
            
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @return name of profile
     */
    public String getName(){
        return name;
    }
    /**
     * @return javaArgs
     */
    public String getJavaArgs(){
        return __JAVA_ARGS__;
    }
    /**
     * @return object {@link resolution}
     */
    public resolution getResolution(){
        return __RESOLUTION__;
    }
    /**
     * @return path of the jre
     */
    public Path getJre(){
        return __JRE__;
    }
    /**
     * @return GameDir
     */
    public Path getGameDir(){
        return __GAMEDIR__;
    }
    /**
     * @return mc version on object {@link VMC}
     */
    public VMC getVersion(){
        return __VERSION__;
    }
    /**
     * @return key of the objeto in a json
     */
    public String getKey(){
        return key;
    }
    /**
     * @return enum {@link VType} get type
     */
    public VType getType(){
        return type;
    }

}
