package net.kender.core;
import java.lang.*;

import static net.kender.core.sample.EXTRAS.EXTRAS.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.Utils.utilsExtras;

public class assetsDowloader {

    public static String URL_ = "https://resources.download.minecraft.net/";
    public static String IndexJsonPath;

    public static int AssetsDowload(String pathJson, String PathDest, String PathObjects) throws IOException {
        IndexJsonPath = pathJson;
        int indexOfUassets = 0;
        File json = new File(IndexJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode obj = rootNode.get("objects");

        int i = 0;
        indexOfUassets = obj.size();


        List<String> keysList = new ArrayList<>();
        Iterator<String> fieldNames = obj.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            keysList.add(key);
            System.out.println(key);
        }

        int size = keysList.size();

        while(i != size){
            JsonNode resource = obj.get(keysList.get(i));

            JsonNode hash = resource.get("hash");
           

            String hashS = hash.asText();

            String block = hashS.substring(0,2);

            String url = URL_ + block + "/" + hashS;


            String path = keysList.get(i);

            String Path = PathDest + path;

            String name = quitarUltimaParteRuta(path);
            int indexExtend = name.lastIndexOf(".");

            if (indexExtend == -1){

            }else{
                String PathO = PathObjects + "\\" + block + "\\" + hashS;
                System.out.println(Path);

                Path pats = java.nio.file.Path.of(Path);
                Path pats0 = java.nio.file.Path.of(PathO);
                if(Files.exists(pats)){
                    
                }else{
                    System.out.println(pats);
                    download(utilsExtras.toURI(url), pats.toFile());
                }
                if(Files.exists(pats0)){
                    
                }else{
                    System.out.println(pats0);
                    download(utilsExtras.toURI(url), pats0.toFile());
                }

            }

            i++;
        }
        return indexOfUassets;
    }
    public static String quitarUltimaParteRuta(String ruta) {
        if (ruta == null) {
            return null;
        }

        int ultimaBarra = ruta.lastIndexOf("/");
        if (ultimaBarra != -1) {
            return ruta.substring(ultimaBarra + 1);
        } else {
            return ruta; // La ruta es el nombre de archivo
        }
    }

}
