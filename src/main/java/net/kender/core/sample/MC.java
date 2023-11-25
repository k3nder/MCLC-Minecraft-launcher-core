package net.kender.core.sample;

/**
 * @author kristian
 * @param __VERSION__ select a version for playing, local or not local
 * @param __DEST__ dest of the donwnloads,assets,libs,etc
 * @param Run run mc
 */

import static net.kender.core.sample.EXTRAS.EXTRAS.*;
import static net.kender.core.sample.EXTRAS.LIBS.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.Utils.utilsExtras;
import net.kender.core.JsonUtils;
import net.kender.core.assetsDowloader;
import net.kender.core.sample.EXTRAS.EXTRAS;
import net.kender.core.sample.Quikplays.Quikplay;
import net.kender.core.sample.Quikplays.profile;

/**
 * instance to run mc
 * 
 * @param __DEST__    to set destination to download
 * @param __VERSION__ a {@link VMC} version
 * @see {@link Manifest}
 * @see {@link profiles}
 */
public class MC {
    public static final String USER_NAME = System.getProperty("user.name");
    public static final String DISC = "C:\\Users\\";

    public static final String MINECRAFT_PATH = DISC + USER_NAME + "\\AppData\\Roaming\\.minecraft";
    private Path __DEST__;
    private VMC __VERSION__;
    private Path __LIBS__;
    private String __USER__ = "testName";
    private Path __GAMEDIR__ = Path.of(MINECRAFT_PATH);
    private Path __JRE__ = Path
            .of("C:\\Users\\krist\\Documents\\.LT\\LT-2.0\\LT\\MC\\versions\\1.20.2\\jre\\bin\\java");
    private UUID __UUID__ = UUID.fromString("d0db8a3d-c392-4ae7-96e5-9365de33ab52");
    private String XUID = "0";
    private Path __ASSETSDIR__ = Path.of(MINECRAFT_PATH + "\\assets\\");
    private String __AccesToken__ = "0";
    private String __CLIENTID__ = "";
    private String __USERTYPE__ = "";
    /** @param __STOP__ a param for stop the donwnloading */
    public boolean __STOP__ = false;

    private int MaxRam = 4;
    private int minRam = 2;
    private Path natives_path;

    /**
     * @param __DEST__    destination
     * @param __VERSION__ a {@link VMC} version
     */
    public MC(Path __DEST__, VMC __VERSION__) {
        this.__DEST__ = __DEST__;
        this.__VERSION__ = __VERSION__;
        natives_path = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\bin\\");

        try {
            download(new URI(
                    "https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u382-b05/OpenJDK8U-jre_x64_windows_hotspot_8u382b05.zip"),
                    new File(__DEST__ + "\\JRE\\8\\jre.zip"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            unZIP(new File(__DEST__ + "\\JRE\\8\\jre.zip"),
                    Path.of(__DEST__ + "\\JRE\\8\\jre\\"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            download(new URI(
                    "https://github.com/adoptium/temurin20-binaries/releases/download/jdk-20.0.1%2B9/OpenJDK20U-jre_x64_windows_hotspot_20.0.1_9.zip"),
                    new File(__DEST__ + "\\JRE\\20\\jre.zip"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            unZIP(new File(__DEST__ + "\\JRE\\20\\jre.zip"),
                    Path.of(__DEST__ + "\\JRE\\20\\jre\\"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param profile
     *                profle data object
     */
    /**
     * create a new MC object to run the game
     * 
     * @param profile
     *                profile object to which you get the configuration
     *                {@link profile}
     */
    public MC(profile profile) {
        this.__DEST__ = profile.getGameDir();
        this.__VERSION__ = profile.getVersion();
        natives_path = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\bin\\");
        __JRE__ = profile.getJre();

        try {
            download(new URI(
                    "https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u382-b05/OpenJDK8U-jre_x64_windows_hotspot_8u382b05.zip"),
                    new File(__DEST__ + "\\JRE\\8\\jre.zip"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            unZIP(new File(__DEST__ + "\\JRE\\8\\jre.zip"),
                    Path.of(__DEST__ + "\\JRE\\8\\jre\\"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            download(new URI(
                    "https://github.com/adoptium/temurin20-binaries/releases/download/jdk-20.0.1%2B9/OpenJDK20U-jre_x64_windows_hotspot_20.0.1_9.zip"),
                    new File(__DEST__ + "\\JRE\\20\\jre.zip"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            unZIP(new File(__DEST__ + "\\JRE\\20\\jre.zip"),
                    Path.of(__DEST__ + "\\JRE\\20\\jre\\"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void createJsVe() {
        __LIBS__ = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\libraries\\");
        if (!new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json")
                .exists()) {
            // down.set(2, "preparing");
            createJsonVersion(__DEST__, __VERSION__);
        }
    }

    private void downloadAndPreparating(JsonOfVersion vanilla, File JarV) {
        download(utilsExtras.toURI(vanilla.downloads.get("client").get("url").asText()), JarV);
        getlibs(__LIBS__, vanilla.libraries);
        natives(__LIBS__, Path
                .of(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + "\\bin"));

        Assets(__DEST__, __VERSION__.toString(), vanilla.assets);
        getJRE(vanilla.javaVersion);
    }

    private void downloadAndPreparating(JsonOfVersion vanilla, JsonOfVersion a, File JarV) {
        download(utilsExtras.toURI(vanilla.downloads.get("client").get("url").asText()), JarV);
        getlibs(__LIBS__, vanilla.libraries);
        getlibs(__LIBS__, a.libraries);
        natives(__LIBS__, Path
                .of(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + "\\bin"));

        Assets(__DEST__, a.inheritsFrom, vanilla.assets);
        getJRE(vanilla.javaVersion);
    }
    /**
     * run minecraft
     */
    public void Run() {
        Thread r = new Thread(new Runnable() {

            public void run() {

                File JsonV = new File(
                        __DEST__.toString() + "\\versions\\" + __VERSION__.getID() + "\\" + __VERSION__.getID()
                                + ".json");
                File JarV = new File(__DEST__.toString() + "\\versions\\" + __VERSION__.getID() + "\\"
                        + __VERSION__.getID() + ".jar");
                createJsVe();
                JsonOfVersion a = new JsonOfVersion(
                        new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json"));

                if (a.isCustom()) {
                    if (JsonV.exists()) {
                        URI vanillaVersionJ = new Manifest().getVersion(a.inheritsFrom).getUrl();
                        JsonOfVersion vanilla = new JsonOfVersion(vanillaVersionJ);

                        downloadAndPreparating(vanilla, a, JarV);

                        CommandConstructor t = new CommandConstructor(
                                __USER__,
                                MaxRam,
                                minRam,
                                vanilla.assets,
                                __LIBS__.toString() + "\\*",
                                a.mainClass, a.lastVersionId,
                                __GAMEDIR__.toString(),
                                __JRE__.toString(),
                                __DEST__,
                                __UUID__,
                                __AccesToken__,
                                __ASSETSDIR__,
                                XUID,
                                __CLIENTID__,
                                __USERTYPE__,
                                a.type.name(), natives_path);
                        EXTRAS.run(t, null);
                    }
                } else {
                    if (JsonV.exists()) {
                        downloadAndPreparating(a, JarV);
                        
                        CommandConstructor t = new CommandConstructor(
                                __USER__,
                                MaxRam,
                                minRam,
                                a.assets,
                                __LIBS__.toString() + "\\*",
                                a.mainClass, a.lastVersionId,
                                __GAMEDIR__.toString(),
                                __JRE__.toString(),
                                __DEST__,
                                __UUID__,
                                __AccesToken__,
                                __ASSETSDIR__,
                                XUID,
                                __CLIENTID__,
                                __USERTYPE__,
                                a.type.name(), natives_path);
                        EXTRAS.run(t, null);
                    }
                }
            }
        });
        r.start();
    }


    /**
     * run minecraft
     * @param torun world or server to run
     * @see {@link Multiplayer} 
     * @see {@link Singleplayer}
     */
    public void Run(Quikplay torun) {
        Thread r = new Thread(new Runnable() {

            public void run() {

                File JsonV = new File(
                        __DEST__.toString() + "\\versions\\" + __VERSION__.getID() + "\\" + __VERSION__.getID()
                                + ".json");
                File JarV = new File(__DEST__.toString() + "\\versions\\" + __VERSION__.getID() + "\\"
                        + __VERSION__.getID() + ".jar");
                createJsVe();
                JsonOfVersion a = new JsonOfVersion(
                        new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json"));

                if (a.isCustom()) {
                    if (JsonV.exists()) {
                        URI vanillaVersionJ = new Manifest().getVersion(a.inheritsFrom).getUrl();
                        JsonOfVersion vanilla = new JsonOfVersion(vanillaVersionJ);

                        downloadAndPreparating(vanilla, a, JarV);

                        CommandConstructor t = new CommandConstructor(
                                __USER__,
                                MaxRam,
                                minRam,
                                vanilla.assets,
                                __LIBS__.toString() + "\\*",
                                a.mainClass, a.lastVersionId,
                                __GAMEDIR__.toString(),
                                __JRE__.toString(),
                                __DEST__,
                                __UUID__,
                                __AccesToken__,
                                __ASSETSDIR__,
                                XUID,
                                __CLIENTID__,
                                __USERTYPE__,
                                a.type.name(), torun,natives_path);
                        EXTRAS.run(t, null);
                    }
                } else {
                    if (JsonV.exists()) {
                        downloadAndPreparating(a, JarV);
                        
                        CommandConstructor t = new CommandConstructor(
                                __USER__,
                                MaxRam,
                                minRam,
                                a.assets,
                                __LIBS__.toString() + "\\*",
                                a.mainClass, a.lastVersionId,
                                __GAMEDIR__.toString(),
                                __JRE__.toString(),
                                __DEST__,
                                __UUID__,
                                __AccesToken__,
                                __ASSETSDIR__,
                                XUID,
                                __CLIENTID__,
                                __USERTYPE__,
                                a.type.name(),torun,natives_path);
                        EXTRAS.run(t, null);
                    }
                }
            }
        });
        r.start();
    }

    /**
     * set ram to use
     * 
     * @param maxRam maxim ram
     * @param MinRam mininum ram
     */
    public void setRam(int maxRam, int MinRam) {
        MaxRam = maxRam;
        minRam = MinRam;
    }

    /**
     * set new destination
     * 
     * @param e dest
     */
    public void setDEST(Path e) {
        __DEST__ = e;
    }

    /**
     * set user name
     * 
     * @param user username
     */
    public void setUser(String user) {
        __USER__ = user;
    }

    /**
     * set game dir
     * 
     * @param GameDir new game dir
     */
    public void setGameDir(Path GameDir) {
        __GAMEDIR__ = GameDir;
    }

    /**
     * set jre to use
     * 
     * @param bin new jre to use
     */
    public void setJavaJRE(Path bin) {
        __JRE__ = bin;
    }

    /**
     * set uuid user
     * 
     * @param e uuid user
     */
    public void setUUID(UUID e) {
        __UUID__ = e;
    }

    /**
     * set XUID
     * 
     * @param XUID new XUID
     */
    public void setXUID(String XUID) {
        this.XUID = XUID;
    }

    /**
     * set asset dir to download
     * 
     * @param ASSETSDIR new path assetsdir
     */
    public void setAssetsDir(Path ASSETSDIR) {
        __ASSETSDIR__ = ASSETSDIR;
    }

    /**
     * set acces token
     * 
     * @param AccesToken new acces token
     */
    public void setAccesToken(String AccesToken) {
        __AccesToken__ = AccesToken;
    }

    /**
     * set client id
     * 
     * @param ClientId new ClientId
     */
    public void setClientId(String ClientId) {
        __CLIENTID__ = ClientId;
    }

    /**
     * set UserType
     * 
     * @param UserType new UserType
     */
    public void setUserType(String UserType) {
        __USERTYPE__ = UserType;
    }

    /**
     * set version Type
     * 
     * @param versionType new VersionType
     */
    public void setVersionType(String versionType) {

    }

    /**
     * set natives Path
     * 
     * @param a natives Path
     */
    public void setNativesPath(Path a) {
        natives_path = a;
    }

    private void getJRE(JsonNode javaVersion) {

        int versionJava = 0;
        JsonNode JVersionNode = null;
        if (javaVersion == null) {
            versionJava = 0;
        } else {
            JVersionNode = javaVersion.get("majorVersion");
            versionJava = JVersionNode.asInt();
        }
        if (versionJava <= 8) {
            __JRE__ = Path.of(__DEST__ + "\\JRE\\8\\jre\\jdk8u382-b05-jre");
            // int JavaV = 8;
            // UtilsFiles.getnatives(__DEST__ + "\\versions\\" + __VERSION__ +
            // "\\libraries\\", DirectoyOfBin + "\\" + JreUUID8.toString(), ".zip");
        } else {
            __JRE__ = Path.of(__DEST__ + "\\JRE\\20\\jre\\jdk-20.0.1+9-jre");
            // int JavaV = 20;
        }

    }

    private void Assets(Path dest, String version, String index) {
        String url = JsonUtils.readAssetsIndexUrlFromJson(dest + "\\versions\\" + version + "\\" + version + ".json");
        String cont = JsonUtils.getJsonVersion(url);
        EXTRAS.createFile(new File(dest + "\\assets\\indexes\\" + index + ".json"), cont);
        System.out.println("asse");
        try {
            assetsDowloader.AssetsDowload(dest + "\\assets\\indexes\\" + index + ".json",
                    dest + "\\assets\\virtual\\legacy\\", dest + "\\assets\\objects\\");
        } catch (IOException e) {
            System.out.println("eeee");
            e.printStackTrace();
        }
    }
}
