
package net.kender.MCutils.players.Server;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.kender.nbt.tag.StringTag;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.Kjson.Json;
import net.kender.nbt.io.NBTUtil;
import net.kender.nbt.io.NamedTag;
import net.kender.nbt.tag.CompoundTag;
import net.kender.nbt.tag.ListTag;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ip",
    "port",
    "debug",
    "motd",
    "players",
    "version",
    "online",
    "protocol",
    "hostname",
    "icon",
    "eula_blocked"
})

public class Server {
	@JsonIgnore
	public String name;

    @JsonProperty("ip")
	public String ip;
    @JsonProperty("port")
    private Integer port;
    @JsonProperty("debug")
    private Debug debug;
    @JsonProperty("motd")
    private Motd motd;
    @JsonProperty("players")
    private Players players;
    @JsonProperty("version")
    private String version;
    @JsonProperty("online")
    private Boolean online;
    @JsonProperty("protocol")
    private Protocol protocol;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("eula_blocked")
    private Boolean eulaBlocked;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Server(String ip) {
    	this.ip = ip;
	}
    
    public Server() {
    	
    }

	@JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty("port")
    public Integer getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty("debug")
    public Debug getDebug() {
        return debug;
    }

    @JsonProperty("debug")
    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    @JsonProperty("motd")
    public Motd getMotd() {
        return motd;
    }

    @JsonProperty("motd")
    public void setMotd(Motd motd) {
        this.motd = motd;
    }

    @JsonProperty("players")
    public Players getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(Players players) {
        this.players = players;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("online")
    public Boolean getOnline() {
        return online;
    }

    @JsonProperty("online")
    public void setOnline(Boolean online) {
        this.online = online;
    }

    @JsonProperty("protocol")
    public Protocol getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("eula_blocked")
    public Boolean getEulaBlocked() {
        return eulaBlocked;
    }

    @JsonProperty("eula_blocked")
    public void setEulaBlocked(Boolean eulaBlocked) {
        this.eulaBlocked = eulaBlocked;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    

    public static List<Server> serversOnFile(File a){
        List<Server> list = new ArrayList<Server>();
        NamedTag levelData = new NamedTag("Test", new StringTag("This is Test"));
		
		// create generic compound tag
		CompoundTag levelDataTag = new CompoundTag();
		
		
		// try catch to handle io errors
		try {
			levelData = NBTUtil.read(a);

			
			// double check the tag in main tag is a compound tag (not all nbt files have a top level compound tag, and if they dont this will go very badly
			if (levelData.getTag() instanceof CompoundTag) {
				// cast the tag in named tag to a compound tag (if we are in the if block this should succeed)
				levelDataTag = (CompoundTag) levelData.getTag();
				for(JsonNode l:new ObjectMapper().readTree(levelDataTag.get("servers").toString()).get("value").get("list")){
                    ////System.out.println("----------");
                    ////System.out.println(l.get("ip").get("value").asText());
                    ////System.out.println(l.get("name").get("value").asText());
					
					
					
					ObjectMapper objectMapper = new ObjectMapper();

			        // Deserializar el JSON a un objeto de la clase Persona
			        Server srv;
					try {
						srv = objectMapper.readValue(Json.SContentOf(new URI("https://api.mcsrvstat.us/3/" + l.get("ip").get("value").asText())), Server.class);
						srv.name = l.get("name").get("value").asText();
						list.add(srv);

					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
                                    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// prints error (i think) if a problem goes wrong with file io (i think)
			e.printStackTrace();
		}
        return list;
    }
    @SuppressWarnings("unchecked")
	public static void write(File aWrite,String name,String ip) {
    	// Create Generic Named Tag
    			NamedTag levelData = new NamedTag("Test", new StringTag("This is Test"));
    			
    			// create generic compound tag
    			CompoundTag levelDataTag = new CompoundTag();
    			
    			// generate new seed

    			
    			// try catch to handle io errors
    			try {
    				// read in level.dat file (returns named tag with a blank name and a compound tag)
    				levelData = NBTUtil.read(aWrite);
    				
    				// double check the tag in main tag is a compound tag (not all nbt files have a top level compound tag, and if they dont this will go very badly
    				if (levelData.getTag() instanceof CompoundTag) {
    					// cast the tag in named tag to a compound tag (if we are in the if block this should succeed)
    					levelDataTag = (CompoundTag) levelData.getTag();
    					System.out.println(levelDataTag.toString());
    					
    					// work our way down through the tree of compound tags until we got the compound tag that holds the compound tag with settings we need
    					
    					
    					ListTag<CompoundTag> s = (ListTag<CompoundTag>) levelDataTag.getListTag("servers");
    					
    					CompoundTag newServerEntry = new CompoundTag();
    					
    					newServerEntry.putString("name", name);
    					newServerEntry.putString("ip", ip);
    					
    					s.add(newServerEntry);
    					
    					NBTUtil.write(levelData,aWrite);
    				}
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				// prints error (i think) if a problem goes wrong with file io (i think)
    				e.printStackTrace();
    			}
    }

}
