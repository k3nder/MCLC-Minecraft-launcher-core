package net.kender;

import static net.kender.core.sample.MC.MINECRAFT_PATH;

import net.kender.MCutils.players.Server.Server;
import net.kender.core.sample.MC;
import net.kender.core.sample.Manifest;
import net.kender.core.sample.Quikplays.Multiplayer;

import java.nio.file.Path;

public class Quikplay_example {
    public static void main(String[] args) {
        MC m = new MC(Path.of(MINECRAFT_PATH),new Manifest().getLastVersion());
    
        //* quikplays are used to open servers and worlds without going through the home screen, they only open the world or server directly
    
        // example of multiplayer
        Server a = new Server("play.minemagic.club","mine magic");
        Multiplayer mul = new Multiplayer(a);
        m.Run(mul);
    }
}