package net.kender.core.sample.Quikplays;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.kender.Kjson.Json;
import net.kender.Utils.resolution;
import net.kender.core.Json.modpack.mrpack.mrIndex;
import net.kender.core.sample.Manifest;
import net.kender.core.sample.VMC;
import net.kender.core.sample.VTC;
import net.kender.core.sample.VType;
import static net.kender.core.sample.MC.MINECRAFT_PATH;

public class profile {
    private VMC __VERSION__;
    public VMC get__VERSION__() {
        return __VERSION__;
    }

    public void set__VERSION__(VMC __VERSION__) {
        this.__VERSION__ = __VERSION__;
    }

    public Path get__GAMEDIR__() {
        return __GAMEDIR__;
    }

    public void set__GAMEDIR__(Path __GAMEDIR__) {
        this.__GAMEDIR__ = __GAMEDIR__;
    }

    public Path get__JRE__() {
        return __JRE__;
    }

    public void set__JRE__(Path __JRE__) {
        this.__JRE__ = __JRE__;
    }

    public resolution get__RESOLUTION__() {
        return __RESOLUTION__;
    }

    public void set__RESOLUTION__(resolution __RESOLUTION__) {
        this.__RESOLUTION__ = __RESOLUTION__;
    }

    public String get__JAVA_ARGS__() {
        return __JAVA_ARGS__;
    }

    public void set__JAVA_ARGS__(String __JAVA_ARGS__) {
        this.__JAVA_ARGS__ = __JAVA_ARGS__;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(VType type) {
        this.type = type;
    }
    private Path __GAMEDIR__ = Path.of(MINECRAFT_PATH);
    private Path __JRE__ = Path.of("default");
    private resolution __RESOLUTION__ = null;
    private String __JAVA_ARGS__ = null;
    private String name = "def";
    private String key;
    private VType type;
    ///private mrIndex modPack;
    private profile(){

    }
    
    private profile(Map<String,String> map){
        // registra los argumentos en las variables si estan en el mapa
        if(map.containsKey("javaArgs")) __JAVA_ARGS__ = map.get("javaArgs");
        if(map.containsKey("lastVersionId")) __VERSION__ = (new Manifest().exist(map.get("lastVersionId")) ? new Manifest().getVersion(map.get("lastVersionId")) : new VMC(map.get("lastVersionId"),VType.custom,null,map.get("lastVersionId")));
        if(map.containsKey("name")) name = map.get("name");
        if(map.containsKey("type")) type = VTC.toVType(map.get("type"));
        if(map.containsKey("width")) __RESOLUTION__ = resolution.of(Double.parseDouble(map.get("width")),Double.parseDouble(map.get("height")));
        if(map.containsKey("gameDir")) __GAMEDIR__ = Path.of(map.get("gameDir"));
        if(map.containsKey("javaDir")) __JRE__ = Path.of(map.get("javaDir"));
        key = map.get("key");
        
        if(__VERSION__.getID().equals("latest-release")) {
        	__VERSION__ = new Manifest().getLastVersion();
        }else if(__VERSION__.getID().equals("latest-snapshot")) {
        	__VERSION__ = new Manifest().getLastSnapshot();
        }
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
    
    public static profile read(File a, String key) {
    	try {
			JsonNode root = new Json(a).getRootNode();
			JsonNode l = root.get("profiles").get(key);
			Map<String,String> mp = new HashMap<String,String>();
            // registra en el mapa la key
            mp.put("key",key);

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
            return pr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
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
    public void addModPack(mrIndex a){
        a.files.get(0).downloads.get(0);
    }
    public void createNew() throws IllegalArgumentException, CloneNotSupportedException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(MINECRAFT_PATH + "\\launcher_profiles.json"));

        // Convertir el objeto que deseas añadir a un JsonNode
        JsonNode nuevoObjeto = objectMapper.valueToTree(this.clone());

        // Añadir el nuevo objeto al JSON existente
        ((ObjectNode) jsonNode).set(name, nuevoObjeto);

        objectMapper.writeValue(new File(MINECRAFT_PATH + "\\launcher_profiles.json"), objectMapper.writeValueAsString(jsonNode));

    }

}
