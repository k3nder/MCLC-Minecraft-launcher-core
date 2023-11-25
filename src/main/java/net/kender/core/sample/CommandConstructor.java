package net.kender.core.sample;

import java.nio.file.Path;
import java.util.UUID;

import net.kender.core.sample.Quikplays.Quikplay;
import net.kender.core.sample.Quikplays.profile;

public class CommandConstructor {
    public String command;

    public CommandConstructor(String user, int maxRam, int minRam, String assetsIndex, String libs, String mainClass,
            String LastVersionId, String gameDir, String javaBin, Path __DEST__,UUID UUID,String accesToken, Path AssetsDir,String XUID,String clientID,String userType,String versionType, Path natives) {
        command = javaBin + "\\bin\\java -Djna.tmpdir=" + natives.toString() + " -Dio.netty.native.workdir=" + natives.toString() + " -Dorg.lwjgl.system.SharedLibraryExtractPath=" + natives.toString() + " -Djava.library.path=" + natives.toString() + " -Xmx" + maxRam + "G -Xms" + minRam + "G " + "-cp \".;" + __DEST__ + "\\versions\\"
                + LastVersionId + "\\" + LastVersionId + ".jar;" + libs + "\" " + mainClass
                + " --gameDir " + gameDir
                + " --username " + user +
                " --assetIndex " + assetsIndex +
                " --accessToken " + accesToken +
                " --version " + LastVersionId +
                " --uuid " + UUID.toString() + 
                " --assetsDir " + AssetsDir + 
                " --xuid " + XUID + 
                " --clientId " + clientID +
                " --userType " + userType + 
                " --versionType " + versionType +
                "";
    }
    public CommandConstructor(String user, int maxRam, int minRam, String assetsIndex, String libs, String mainClass,
            String LastVersionId, String gameDir, String javaBin, Path __DEST__,UUID UUID,String accesToken, Path AssetsDir,String XUID,String clientID,String userType,String versionType,Quikplay r,Path natives) {
        command = javaBin + "\\bin\\java -Xmx" + maxRam + "G -Xms" + minRam + "G " + "-cp \".;" + __DEST__ + "\\versions\\"
                + LastVersionId + "\\" + LastVersionId + ".jar;" + libs + "\" " + mainClass
                + " --gameDir " + gameDir
                + " --username " + user +
                " --assetIndex " + assetsIndex +
                " --accessToken " + accesToken +
                " --version " + LastVersionId +
                " --uuid " + UUID.toString() + 
                " --assetsDir " + AssetsDir + 
                " --xuid " + XUID + 
                " --clientId " + clientID +
                " --userType " + userType + 
                " --versionType " + versionType;
                if(r.s != null){
                    command += " --quickPlayMultiplayer " + r.s.ip;
                    if(r.sp != 0){
                        command += ":" + r.sp;
                    }
                }else if(r.w != null){
                    command += " --quickPlaySingleplayer " + r.w.world.getFileName();
                }
    }
    public CommandConstructor(profile a){

    }
    public String asText(){
        return command;
    }
    public void setJavaArgs(String[] args){
        
    }
    public void setMinRam(int x){

    }
    public void setMaxRam(int x){

    }
    public void setGameDir(Path x){

    }
    public void setUser(String x){

    }
    public void setAssetsIndex(String x){

    }
    public void setLibsPath(Path x){

    }
    public void setMainClass(String x){

    }
    public void setVersionId(String x){

    }
    public void setJavaHome(Path x){
        
    }

}