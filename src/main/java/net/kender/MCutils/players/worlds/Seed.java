package net.kender.MCutils.players.worlds;

import java.net.URI;
import java.awt.Desktop;

public class Seed {
    private String Seed;
    public Seed(String seed){
        Seed = seed;
    }
    /**
     * open seed on chunkbase https://www.chunkbase.com/apps/
     */
    public void openOnChunckBase(){
        opneOnWeb("https://www.chunkbase.com/apps/biome-finder#" + Seed);
    }
    /**
     * @return seed to string
     */
    public String toString(){
        return Seed;
    }
    private static void opneOnWeb(String url) {
        try {
            // Verifica si el sistema soporta la clase Desktop
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                // Abre la URL en el navegador predeterminado
                desktop.browse(new URI(url));
            } else {
                // Si Desktop no es compatible, puedes imprimir un mensaje o manejar la situación de otra manera
                System.out.println("El sistema no admite la apertura automática del navegador.");
            }
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al intentar abrir el navegador
            e.printStackTrace();
        }
    }

}
