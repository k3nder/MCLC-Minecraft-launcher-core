package net.kender.MCLC.test;

import net.kender.MCLC.JsonUtils;
import net.kender.MCLC.UtilsFiles;
import net.kender.MCLC.argsGenerator;

import java.io.IOException;

import static net.kender.MCLC.Constants.MinecraftPath;
import static net.kender.MCLC.JsonUtils.*;
import static net.kender.MCLC.UtilsFiles.*;
import static net.kender.MCLC.argsGenerator.genrateArgsCommand;
import static net.kender.MCLC.argsGenerator.getJREVersion;
import static net.kender.MCLC.assetsDowloader.AssetsDowload;

/*
* this ia a example
*
* not download assest
*
* wiki in:
*
*
*
*
* */
public class example {
    public static String version = "1.20.1";
    public static String Username = "";
    public static void main(String[] args) throws IOException {

        //get Lis of Versions dowloables
        String[] listOfVersionsDownloadble = new String[0];
        try {
            listOfVersionsDownloadble = GetListOfVersions("https://launchermeta.mojang.com/mc/game/version_manifest.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = listOfVersionsDownloadble.length;
        int it = 0;
        while(i != it){
            System.out.println(listOfVersionsDownloadble[it]);
            it++;
        }

        //get Json Of Version URL
        String UrlOfVersion = GetUrlOfJsonOfVersion("https://launchermeta.mojang.com/mc/game/version_manifest.json", "1.20.1");
        System.out.println(UrlOfVersion);

        //read the Json of version

        String ContentOfJsonOfVersion = getJsonVersion(UrlOfVersion);
        System.out.println(ContentOfJsonOfVersion);

        //create the file Json
        createFile(MinecraftPath + "\\example\\" + version,"json", version, ContentOfJsonOfVersion);

        //get the url of the client
        String UrlOfClient = readJsonVersion(  MinecraftPath + "\\example\\" + version + "\\" + version + ".json");

        //dowload the client
        download(UrlOfClient,MinecraftPath + "\\example\\" + version + "\\",version,".jar");

        //get libraries of minecraft
        getLibs(MinecraftPath + "\\example\\" + version + "\\" + version + ".json",version, MinecraftPath + "\\example\\libs\\" + version);
        //get assets
        String assetsIndexUrl = ReadFileAssetsIndex(  MinecraftPath + "\\example\\" + version + "\\" + version + ".json");

        String assestsContent = getJsonVersion(assetsIndexUrl);

        createFile( MinecraftPath + "\\assets\\indexes", "json", JsonUtils.getAssesNum(MinecraftPath + "\\example\\" + version + "/" + version + ".json"), assestsContent);

        AssetsDowload(MinecraftPath + "\\assets\\indexes\\" + JsonUtils.getAssesNum(MinecraftPath + "\\example\\" + version + "\\" + version + ".json") + ".json", MinecraftPath + "\\assets\\virtual\\legacy\\", MinecraftPath + "\\assets\\objects");
        //get te version of the jre(this is a downloader of the jre at a time)
        getJREVersion(version,MinecraftPath + "\\example\\" + version + "\\" + version + ".json", MinecraftPath + "\\example\\bin");

        //get main class
        String mainClass = getMainClass(MinecraftPath + "\\example\\" + version + "\\" + version + ".json");

        //get the command for launch minecraft                                                                                                                                                                                                  gamedir       mainclass
        String command = genrateArgsCommand(version,MinecraftPath + "\\example\\libs\\" + version + "\\" + version,MinecraftPath + "\\example\\" + version + "\\" + version + ".jar",Username,"","0",MinecraftPath,mainClass,version,false,version);

        //execute the command
        run(command);
    }
}
