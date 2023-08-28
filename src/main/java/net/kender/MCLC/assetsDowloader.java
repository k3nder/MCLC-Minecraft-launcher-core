package net.kender.MCLC;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        int pb = indexOfUassets/10000;
        System.out.println("pb: " + pb);

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
            System.out.println("----------");
            System.out.println("resource: " + keysList.get(i));
            System.out.println("hash is: " + hash.asText());
            System.out.println("----------");

            String hashS = hash.asText();

            String block = hashS.substring(0,2);

            String url = URL_ + block + "/" + hashS;

            System.out.println("url is: " + url);

            String path = keysList.get(i);

            String Path = PathDest + path;

            String name = JsonUtils.quitarUltimaParteRuta(path);
            int indexExtend = name.lastIndexOf(".");
            int indesul = name.length();
            System.out.println(indexExtend);
            System.out.println(name);

            if (indexExtend == -1){
                System.out.println("dot found '.' in name");

            }else{
                // Construir el nuevo String sin la parte que queremos quitar
                String newString = name.substring(0, indexExtend) + name.substring(indesul);
                System.out.println(newString);

                System.out.println("dowloading...");

                String PathO = PathObjects + "\\" + block + "\\" + hashS;

                Path pats = java.nio.file.Path.of(Path);
                Path pats0 = java.nio.file.Path.of(PathO);
                if(Files.exists(pats)){
                    System.out.println("the asset " + path + " alredy exist");
                }else{
                    UtilsFiles.download(url, Path,"","");
                }
                if(Files.exists(pats0)){
                    System.out.println("the asset " + PathO + " alredy exist");
                }else{
                    UtilsFiles.download(url, PathO, "", "");
                }
                pb = pb + pb;


            }

            i++;
        }
        return indexOfUassets;
    }
}
