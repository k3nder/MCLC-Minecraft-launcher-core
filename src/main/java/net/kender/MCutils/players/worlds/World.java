package net.kender.MCutils.players.worlds;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.image.Image;
import net.kender.core.sample.Manifest;
import net.kender.core.sample.VMC;
import net.kender.nbt.io.NBTUtil;
import net.kender.nbt.io.NamedTag;
import net.kender.nbt.tag.CompoundTag;
import net.kender.nbt.tag.StringTag;
import net.kender.nbt.tag.Tag;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;

public class World {
    private VMC compatibleVersionMinimum;
    public Path world;
    private Seed seed = null;
    private CompoundTag tree;
    public boolean corrupt = false;
    public World(Path World){
        world = World;
        // Create Generic Named Tag
		NamedTag levelData = new NamedTag("Test", new StringTag("This is Test"));
		
		// create generic compound tag
		CompoundTag levelDataTag = new CompoundTag();
		
		
		// try catch to handle io errors
		try {
			// read in level.dat file (returns named tag with a blank name and a compound tag)
			levelData = NBTUtil.read(new File(world + "\\level.dat"));
            System.out.println(new File(world + "\\level.dat"));
			//System.out.println(new File("C:\\Users\\krist\\AppData\\Roaming\\.minecraft\\saves\\redst\\level.dat").exists());
			
			// double check the tag in main tag is a compound tag (not all nbt files have a top level compound tag, and if they dont this will go very badly
			if (levelData.getTag() instanceof CompoundTag) {
				// cast the tag in named tag to a compound tag (if we are in the if block this should succeed)
				levelDataTag = (CompoundTag) levelData.getTag();
				tree = levelDataTag;
				// work our way down through the tree of compound tags until we got the compound tag that holds the compound tag with settings we need
				CompoundTag generatorTag = levelDataTag.getCompoundTag("Data").getCompoundTag("WorldGenSettings");
				CompoundTag generatorTagVersion = levelDataTag.getCompoundTag("Data").getCompoundTag("Version");
                if(generatorTag == null){
                    System.out.println("corrupt or not generated file");
                    corrupt = true;
                }else{
                    Tag<?> seeds = generatorTag.get("seed");
                    Tag<?> version = generatorTagVersion.get("Name");
				    ObjectMapper mapper = new ObjectMapper();
                    JsonNode seed = mapper.readTree(seeds.toString());
                    JsonNode vers = mapper.readTree(version.toString());
                    this.seed = new Seed(seed.get("value").asText());
                    this.compatibleVersionMinimum = new Manifest().getVersion(vers.get("value").asText());
                }
			}
		} catch (IOException e) {
			// prints error (i think) if a problem goes wrong with file io (i think)
			e.printStackTrace();
		}
    }
    public boolean isCompatible(VMC vers){
        return compatibleVersionMinimum.getID().equals(vers.getID());
    }
    public Seed getSeed(){
        return this.seed;
    }
    public Image getIcon(){
        return new Image("file:///" + world + "\\icon.png");
    }
    public String getName(){
        return world.getFileName().toString();
    }
    public CompoundTag getTree(){
        return tree;
    }
    public List<DataPack> getDataPacks(){
		Path directorio = Paths.get(world + "\\datapacks");

		List<DataPack> list = new ArrayList<DataPack>();

        // Utiliza el objeto Files para obtener un Stream de Paths
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directorio)) {
            // Itera sobre los Paths e imprime sus nombres
            for (Path archivo : stream) {
                if(archivo.toString().contains(".zip")){
                    DataPack a = new DataPack(archivo.toFile()); 
				    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	} 
    public VMC getVersion(){
        return compatibleVersionMinimum;
    }
}
