package net.kender.core.sample;
import java.net.URI;


public class VMC {
    private String id;
    private VType type;
    private URI url;

    /**
     * @param id {@link ID} of the version
     * @param x
     * @param url
     */
    public VMC(String id,VType x,URI url){
        this.id = id;
        this.type = x;
        this.url = url;
    }
    /**
     * @return {@link ID} get id version
     */
    public String getID(){
        return id;
    }
    /**
     * @return {@link VType} enum for type
     */
    public VType getType(){
        return type;
    }
    /**
     * @return uri of the json of version
     */
    public URI getUrl(){
        return url;
    }
    /**
     * @return id to string
     */
    public String toString(){
        return id;
    }
}
