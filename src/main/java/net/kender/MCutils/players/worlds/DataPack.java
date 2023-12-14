package net.kender.MCutils.players.worlds;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.image.Image;

public class DataPack {
    private File file;

    public DataPack(File file){
        this.file = file;
    }
    private JsonNode readZIPJ(File a) {
        try (ZipFile zipFile = new ZipFile(a)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().equals("pack.mcmeta")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        return new ObjectMapper().readTree(inputStream);
                    }
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Image readZIPI(File a) {
        try (ZipFile zipFile = new ZipFile(a)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.getName().equals("pack.png")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        return new Image(inputStream);
                    }
                }else{
                    return null;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean isDataPack(){
        try (ZipFile zipFile = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                System.out.println(entry.getName());
                if (entry.getName().equals("pack.mcmeta")) {
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Image getIcon(){
        return readZIPI(file);
    }
    public List<Integer> getVersion(){
        JsonNode root = readZIPJ(file);
        List<Integer> list = new ArrayList<Integer>();
        if(root.get("pack").has("supported_formats")){
            if(root.get("pack").get("supported_formats").has("min_inclusive")){
                list.add(root.get("pack").get("supported_formats").get("min_inclusive").asInt());
                list.add(-0);
                list.add(root.get("pack").get("supported_formats").get("max_inclusive").asInt());
            }
            for(JsonNode l:root.get("pack").get("supported_formats")){
                list.add(l.asInt());
            }
        }
        list.add(root.get("pack").get("pack_format").asInt());
        return list;
        
    }
    public String getDescription(){
        String Return = "";
        JsonNode root = readZIPJ(file);
        if(root.get("pack").get("description").isArray()){
            for(JsonNode l:root.get("pack").get("description")){
                if(l.isTextual()){
                    Return += l.asText();
                }else{
                    if(l.has("translate")){Return += l.get("translate").asText();}
                    if(l.has("color")){Return += l.get("color").asText();}
                    if(l.has("text")){Return += l.get("text").asText();}
                }
            }
        }else{
            Return = root.get("pack").get("description").asText();
        }
        return Return;
    }

}
