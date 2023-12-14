package net.kender.core.Json.modpack.mrpack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.core.Json.Version.versionJson;

/**
 * mrIndex
 */
public class mrIndex{
    public int formatVersion;
    public String game;
    public String versionId;
    public String name;
    public String summary;
    public ArrayList<mod> files;
    public dependences dependencies;
    public static mrIndex load(File a){
        try (ZipFile zipFile = new ZipFile(a)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().equals("modrinth.index.json")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        return new ObjectMapper().readValue(inputStream, mrIndex.class);
                    }
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}