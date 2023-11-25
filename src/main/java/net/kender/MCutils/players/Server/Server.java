package net.kender.MCutils.players.Server;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.Kjson.Json;
import net.kender.core.sample.VMC;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

public class Server {
    public String ip;
    public String Name;


    public URI ImageIcon;
    public URI ImageBackground;
    public String ID;
    public boolean isFeatured;
    public String imageId;
    public String altImage;
    public String shortDescription;
    public boolean gamerSaferService;
    public String idbackgroundid;
    public String altbackground;
    public int onlinecurrentPlayers;
    public boolean isOnline;
    public int Maxplayers;
    public List<ServerTag> tags;
    public List<ServerBadges> badges;
    public List<serverLang> langs;
    public ServerLocation locat;
    public List<ServerMedia> media;
    public String longDescription;
    public String currentMoth;
    public String rawMotd;
    public URI presentationVideo;
    public List<ServerScreenshot> Screenshots;
    public String javaIp;
    public long port;
    public URI codeOfConduct;
    public VMC vers;
    public int votes;


    public Server(String ip,String name){
        this.ip = ip;
        this.Name = name;
        load();
    }
    private void load(){
        try {
            URI e = new URI("https://api.mcsrvstat.us/3/" + ip);
            
            JsonNode main = Json.ContentOf(e);

            //* separation

            isOnline = main.get("online").asBoolean();
            if(isOnline){
                Maxplayers = main.get("players").get("max").asInt();
                onlinecurrentPlayers = main.get("players").get("online").asInt();
                rawMotd = main.get("motd").get("html").asText();
                ImageIcon = new URI(main.get("icon").asText());
            }
            ////ImageIcon = new URI(main.get("iconImage").get("url").asText());
            ////ImageBackground = new URI(main.get("backgroundImage").get("url").asText());
            ////ID = ID.of(main.get("id").asText());
            ////isFeatured = main.get("isFeatured").asBoolean();
            ////imageId = ID.of(main.get("iconImage").get("id").asText());
            ////altImage = main.get("iconImage").get("altText").asText();
            ////shortDescription = main.get("shortDescription").asText();
            ////gamerSaferService = main.get("gamerSaferService").asBoolean();
            ////idbackgroundid = ID.of(main.get("backgroundImage").get("id").asText());
            ////altbackground = main.get("backgroundImage").get("altText").asText();
            ////onlinecurrentPlayers = main.get("currentOnlinePlayers").asInt();
            ////isOnline = main.get("isOnline").asBoolean();
            ////Maxplayers = main.get("currentMaxPlayers").asInt();
            ////tags = Servertag(main.get("serverTags"));
            ////badges = Badges(main.get("serverBadges"));
            ////langs = langs(main.get("serverLanguage"));
            ////////locat = new ServerLocation(ID.of(main.get("serverLocation").get("0").get("id").asText()), main.get("serverLocation").get("0").get("name").asText());
            ////media = media(main.get("serverMedias"));
            ////longDescription = main.get("longDescription").asText();
            ////currentMoth = main.get("currentMotd").asText();
            ////rawMotd = main.get("rawMotd").asText();
            ////presentationVideo = new URI(main.get("presentationVideoUrl").asText());
            ////Screenshots = screenshotImages(main.get("screenshotImages"));
            ////javaIp = ServerIp.of(main.get("javaAddress").asText());
            ////port = ServerPort.of(main.get("javaPort").asInt());
            ////codeOfConduct = new URI(main.get("codeOfConductUrl").asText());
            ////////vers = new Version(main.get("version").get("0").get("name").asText());
            ////votes = main.get("votes").asInt();



        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    ////private List<ServerTag> Servertag(JsonNode sevr){
    ////    List<ServerTag> list = new ArrayList<ServerTag>();
    ////    for(JsonNode l : sevr){
    ////        ID id = ID.of(l.get("id").asText());
    ////        String name = l.get("name").asText();
    ////        String description = l.get("description").asText();
    ////        list.add(new ServerTag(id.id, name, description,"KEYWORLD", false));
    ////    }
    ////    return list;
    ////}
    ////private List<ServerBadges> Badges(JsonNode n) throws URISyntaxException{
    ////    List<ServerBadges> list = new ArrayList<ServerBadges>();
    ////    for(JsonNode l:n){
    ////        ID id = ID.of(l.get("id").asText());
    ////        String name = l.get("name").asText();
    ////        String desc = l.get("description").asText();
    ////        URI ing = new URI(l.get("icon_url").asText());
////
    ////        list.add(new ServerBadges(id, name, desc, ing.toString()));
    ////    }  
    ////    return list;
    ////}
    ////private List<serverLang> langs(JsonNode lang){
    ////    List<serverLang> list = new ArrayList<serverLang>();
    ////    for(JsonNode l:lang){
    ////        ID id = ID.of(l.get("id").asText());
    ////        String Lang = l.get("name").asText();
////
    ////        list.add(new serverLang(id, Lang));
    ////    }
    ////    return list;
    ////        
    ////}
    ////private List<ServerMedia> media(JsonNode m) throws URISyntaxException{
    ////    List<ServerMedia> list = new ArrayList<ServerMedia>();
    ////    for(JsonNode l:m){
    ////        String url = l.get("url").asText();
    ////        String desc = l.get("description").asText();
    ////        String socialMedia = l.get("social_media").asText();
////
    ////        list.add(new ServerMedia(url, desc, socialMedia));
    ////    }
    ////    return list;
    ////}
    ////private List<ServerScreenshot> screenshotImages(JsonNode m) throws URISyntaxException{
    ////    List<ServerScreenshot> list = new ArrayList<ServerScreenshot>();
    ////    for(JsonNode l:m){
    ////        ID id = ID.of(l.get("id").asText());
    ////        String url = l.get("url").asText();
    ////        String altText = l.get("altText").asText();
    ////        list.add(new ServerScreenshot(id, new URI(url), altText));
    ////    }
    ////    return list;
////
    ////}
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
                    list.add(new Server(l.get("ip").get("value").asText(),l.get("name").get("value").asText()));
                }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// prints error (i think) if a problem goes wrong with file io (i think)
			e.printStackTrace();
		}
        return list;
    }

    // TODO añadir mas methodos
}
