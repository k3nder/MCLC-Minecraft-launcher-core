/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kender.MCLC;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static net.kender.MCLC.Constants.MinecraftPath;


/**
 *
 * @author krist
 */
public class argsGenerator {
    public static String RutaJava;
    public static String genrateArgsCommand(String version,String pathOfLibsCarpet, String pathOfTheJar, String username, String ram, String accesstoken, String gamedir, String MainClass, String lastVersionID,boolean Custom,String vers) throws IOException {
        if(Custom) {
            Constants.command = RutaJava + "bin\\java -cp \".;" + pathOfTheJar + ";" + pathOfLibsCarpet + "\\*\" " + MainClass + " --username " + username + " --accessToken " + accesstoken + " --version " + version + " --assetIndex " + JsonUtils.getAssesNum(MinecraftPath + "\\versions\\" + version + "\\" + vers + ".json") + " -Xmx" + ram + " -Xms2G --gameDir " + gamedir + " -WorkingDirectory " + MinecraftPath + "\\versions\\" + version + "\\" + version + " -DFabricMcEmu= net.minecraft.client.main.Main --assetsDir \"C:\\Users\\" + Constants.User + "\\AppData\\Roaming\\.minecraft\\assets\"";
            System.out.println(Constants.command);
            return Constants.command;
        }else{
            Constants.command = RutaJava + "bin\\java -cp \".;" + MinecraftPath + "\\versions" + "\\" + lastVersionID + "\\" + lastVersionID + ".jar;C:\\Users\\" + Constants.User + "\\Documents\\LauncherTrinity\\libs\\" + version + "\\*\" " + MainClass + " --username " + username + " --accessToken " + accesstoken + " --version " + version + " --assetIndex " + JsonUtils.getAssesNum(MinecraftPath + "\\versions\\" + version + "\\" + version + ".json") + " -Xmx" + ram + " -Xms2G --gameDir " + gamedir + " -DFabricMcEmu= net.minecraft.client.main.Main --assetsDir \"C:\\Users\\" + Constants.User + "\\AppData\\Roaming\\.minecraft\\assets\"";
            System.out.println(Constants.command);
            return Constants.command;
        }
        
    }
    public static String getJREVersion(String version,String JsonPath, String DirectoyOfBin) throws IOException {
        // Crear un ObjectMapper para leer el JSON
        ObjectMapper objectMapper = new ObjectMapper();
        int versionJava = 0;
        // Leer el archivo JSON y convertirlo en un objeto JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(JsonPath));
        JsonNode JVersionNode = null;
        JsonNode JavaJsonNode = rootNode.get("javaVersion");
        if(JavaJsonNode == null){
            versionJava = 0;
        }else{
            JVersionNode = JavaJsonNode.get("majorVersion");
            versionJava = JVersionNode.asInt();
        }
        Path bin = Path.of(DirectoyOfBin);

        if(Files.exists(bin)){
            System.out.println("bin exist");
        }else{
            UtilsFiles.createDirectory(DirectoyOfBin, "");
        }


        String UUIDB;
        if(versionJava <= 8){
            UUID JreUUID8 = UUID.randomUUID();
            UtilsFiles.download("https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u382-b05/OpenJDK8U-jre_x64_windows_hotspot_8u382b05.zip", Constants.JRE8w, JreUUID8.toString() , ".zip");
            UtilsFiles.createDirectory(Constants.JRE8w + "/", JreUUID8.toString());
            UtilsFiles.unZIP(Constants.JRE8w + "/" + JreUUID8.toString() + ".zip", Constants.JRE8w + "/" + JreUUID8.toString() + "/");
            Path binSource = Paths.get(Constants.JRE8w + "/" + JreUUID8.toString() + "/" + "jdk8u382-b05-jre");
            Path binDest = Paths.get( DirectoyOfBin + "\\" + JreUUID8.toString());
            Files.move(binSource,binDest);
            System.out.println("is java 8");
            RutaJava = DirectoyOfBin + "\\" + JreUUID8.toString()+ "\\";
            UUIDB =  DirectoyOfBin + "\\" + JreUUID8.toString()+ "\\";
            Constants.JavaV = 8;
            UtilsFiles.getNatives("C:\\Users\\" + Constants.User + "\\Documents\\LauncherTrinity\\libs\\" + version + "\\",  DirectoyOfBin + "\\" + JreUUID8.toString(), ".zip");

        }else{
            UUID JreUUID20 = UUID.randomUUID();
            UtilsFiles.download("https://github.com/adoptium/temurin20-binaries/releases/download/jdk-20.0.1%2B9/OpenJDK20U-jre_x64_windows_hotspot_20.0.1_9.zip", Constants.JRE20w, JreUUID20.toString() , ".zip");
            UtilsFiles.createDirectory(Constants.JRE20w + "/", JreUUID20.toString());
            UtilsFiles.unZIP(Constants.JRE20w + "/" + JreUUID20.toString() + ".zip", Constants.JRE20w + "/" + JreUUID20.toString() + "/");
            Path binSource = Paths.get(Constants.JRE20w + "/" + JreUUID20.toString() + "/" + "jdk-20.0.1+9-jre");
            Path binDest = Paths.get( DirectoyOfBin +  "\\" + JreUUID20.toString());
            Files.move(binSource,binDest);
            System.out.println("is java 20");
            RutaJava =  DirectoyOfBin + "\\" + JreUUID20.toString()+ "\\";
            UUIDB = DirectoyOfBin + "\\" + JreUUID20.toString()+ "\\";

            Constants.JavaV = 20;

        }

        return UUIDB;
    }

}
