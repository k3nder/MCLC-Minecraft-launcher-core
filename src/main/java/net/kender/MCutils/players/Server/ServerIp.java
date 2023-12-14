package net.kender.MCutils.players.Server;

public class ServerIp {
    public String IP;
    private ServerIp(String ip){
        IP = ip;
    }
    public static ServerIp of(String a){
        return new ServerIp(a);
    }
    // TODO añadir mas metdos
}
