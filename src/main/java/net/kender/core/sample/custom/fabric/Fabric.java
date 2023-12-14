package net.kender.core.sample.custom.fabric;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.Kjson.Json;
import net.kender.core.sample.VMC;
import net.kender.core.sample.VType;
import net.kender.core.sample.custom.CType;

public class Fabric extends VMC{
	private static String API_VERSION_LOADER = "https://meta.fabricmc.net/v2/versions/loader/%s";
	private static String API_VERSION_JSON = "https://meta.fabricmc.net/v2/versions/loader/%s/%s/profile/json";
	
	public Fabric(String version, String loader) throws URISyntaxException {
        super(generateId(version, loader), VType.custom, new URI(generateUrl(version, loader)),generateIdV(version, loader),CType.fabric);
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
