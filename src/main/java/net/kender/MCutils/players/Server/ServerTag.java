package net.kender.MCutils.players.Server;

public class ServerTag {
    public String ID;
    public String NAME;
    public String DESC;
    public String TYPE;
    public boolean ISH;
    public ServerTag(String id,String name,String description,String type,boolean is_highlighted){
        ID=id;
        NAME=name;
        DESC=description;
        TYPE=type;
        ISH=is_highlighted;
    }
}
