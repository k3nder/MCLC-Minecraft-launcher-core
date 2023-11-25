package net.kender.core.sample.Quikplays;

import net.kender.MCutils.players.Server.Server;

public class Multiplayer extends Quikplay {
    public Multiplayer(Server r){
        s = r;
    }
    public Multiplayer(Server r,long port){
        s = r;
        sp = port;
    }
    // TODO añadir mas  Methodos
}
