package net.kender.core.sample;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;


import net.kender.Kjson.Json;

/**
 * @author kristian
 */

public class Manifest {
    private JsonNode last;
    private Map<String,VMC> list = new HashMap<>();
    /**
     * create a manifes for read
     * @param manifest uri of the json for read
     */
    public Manifest(URI manifest){
    	if(!manifest.isAbsolute()) {
    		throw new IllegalArgumentException("not found url " + manifest.toString());
    	}
        // itarar sobre cada objeto en el json en el objeto versions
        for(JsonNode l:Json.JContentOf(manifest).get("versions")){
            try {
                // añadir al mapa de versiones
                list.put(l.get("id").asText(),new VMC(l.get("id").asText(), VTC.toVType(l.get("type").asText()),new URI(l.get("url").asText()),l.get("id").asText()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        last = Json.JContentOf(manifest).get("latest");
    }
    /**
     * create new manifest
     * default json of read 
     */
    public Manifest() {
        try {
            // itarar sobre cada objeto en el json en el objeto versions
            for(JsonNode l:Json.JContentOf(new URI("https://launchermeta.mojang.com/mc/game/version_manifest.json")).get("versions")){
                try {
                    // añadir al mapa de versiones
                    list.put(l.get("id").asText(),new VMC(l.get("id").asText(), VTC.toVType(l.get("type").asText()),new URI(l.get("url").asText()),l.get("id").asText()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            last = Json.JContentOf(new URI("https://launchermeta.mojang.com/mc/game/version_manifest.json")).get("latest");
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    /**
     * verify a exist version
     * @param version version for verify
     */
    public boolean exist(String version){
    	if(version == null) {
    		throw new IllegalArgumentException("invalid argument null ");
    	}
        return list.containsKey(version);
    }
    /**
     * get a version
     * @param of version for get
     * @return version on object {@link VMC}
     */
    public VMC getVersion(String of){
        if(of.equals("latest-release")){
            return this.getLastVersion();
        }
        if(of.equals("latest-snapshot")){
            return this.getLastSnapshot();
        }
        if(!list.containsKey(of)){
            throw new IllegalArgumentException("version not exist in the manifest: " + of);
        }
        return list.get(of);
    }
    /**
     * @return list of versions in a manifest
     * @see {@link VMC}
     */
    public ArrayList<VMC> getList(){
    	ArrayList<VMC> li = new ArrayList<VMC>();
        for(Entry<String,VMC> l:list.entrySet()){
            li.add(l.getValue());
        }
        return li;
    }
    /**
     * @return last version in a manifest
     * @see {@link VMC}
     */
    public VMC getLastVersion(){
        String x = last.get("release").asText();
        return list.get(x);
    }
    /**
     * @return last snapshot in a manifest
     * @see {@link VMC}
     */
    public VMC getLastSnapshot(){
        String x = last.get("snapshot").asText();
        return list.get(x);
    }
    
}

