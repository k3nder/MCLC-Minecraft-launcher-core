package net.kender;

import static net.kender.core.sample.MC.*;

import java.nio.file.Path;

import net.kender.core.sample.MC;
import net.kender.core.sample.Manifest;

public class MC_Example {
    public static void main(String[] args) {
        //*MC is an object to launch the game and set the arguments
        //MC m = new MC(Path to destination of the donwloads,version in object VMC); example 
        MC m = new MC(Path.of(MINECRAFT_PATH),new Manifest().getLastVersion());
        
        System.out.println(new Manifest().getLastVersion().getID());
        
        ////System.exit(0);

        //use Run to run MC
        m.Run();

        //These are the configuration methods
        
        //m.setAccesToken(DISC);
        //m.setAssetsDir(null);
        //m.setClientId(DISC);
        //m.setJavaJRE(null);
        //m.setNativesPath(null);
        //m.setRam(0, 0);
        //m.setUUID(null);
        //m.setUser(USER_NAME);
        //m.setUserType(DISC);
        //m.setXUID(DISC);
    }
}
