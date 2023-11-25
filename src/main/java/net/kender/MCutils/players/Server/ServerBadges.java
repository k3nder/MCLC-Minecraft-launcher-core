package net.kender.MCutils.players.Server;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerBadges{
    public String ID;
    public String NAME;
    public String description;
    public URI iconURI;
    public ServerBadges(String id,String name,String Description,String iconUrl){
        ID = id;
        NAME = name;
        description = Description;
        try {
            iconURI = new URI(iconUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
