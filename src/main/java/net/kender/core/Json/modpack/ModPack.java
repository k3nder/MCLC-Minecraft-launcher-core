package net.kender.core.Json.modpack;
import static net.kender.core.sample.EXTRAS.EXTRAS.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import net.kender.core.Json.modpack.mrpack.mod;

public interface ModPack {
    public Path dest = null;
    public ArrayList<mod> modList = null;
    public default void DOwnload(){
        for(mod l:modList){
            for(String r:l.downloads){
                try {
                    download(new URI(r),new File(dest + "\\" + l.path));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
}