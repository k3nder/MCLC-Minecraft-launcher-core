package net.kender.core.sample.custom.quilt;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.Kjson.Json;
import net.kender.core.sample.VMC;
import net.kender.core.sample.VType;
import net.kender.core.sample.custom.CType;
import net.kender.core.sample.custom.fabric.Fabric;
import net.kender.core.sample.custom.mod.mod;

public class Quilt extends VMC{
	
	private static final String API_VERSION_JSON = "https://meta.quiltmc.org/v3/versions/loader/%s/%s/profile/json";
	private static final String API_VERSION_LOADER = "https://meta.quiltmc.org/v3/versions/loader/%s";

	
	public Quilt(String version, String loader) throws URISyntaxException {
        super(generateId(version, loader), VType.custom, new URI(generateUrl(version, loader)),generateIdV(version, loader),CType.quilt);
    }
	

    private static String generateId(String version, String loader) throws URISyntaxException {
        String URL = generateUrl(version,loader);
        return Json.JContentOf(new URI(URL)).get("id").asText();
    }
    
    private static String generateIdV(String version, String loader) throws URISyntaxException {
        String URL = generateUrl(version,loader);
        return Json.JContentOf(new URI(URL)).get("inheritsFrom").asText();
    }

    private static String generateUrl(String version, String loader) {
        // Lógica para generar la URL según tus necesidades
		return String.format(API_VERSION_JSON, version,loader);
		
    }

	
	public static ArrayList<String> getLoadersVersions(String version) throws URISyntaxException{
		ArrayList<String> l = new ArrayList<>();
		
		JsonNode root = Json.JContentOf(new URI(String.format(API_VERSION_LOADER, version)));
		
		for(JsonNode s:root) {
			l.add(s.get("loader").get("version").asText());
		}
		return l;
	}
	
	

}
