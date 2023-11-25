package net.kender.Utils;

import java.net.URI;
import java.net.URISyntaxException;

public class utilsExtras {
    public static URI toURI(String x){
        try {
            return new URI(x);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
