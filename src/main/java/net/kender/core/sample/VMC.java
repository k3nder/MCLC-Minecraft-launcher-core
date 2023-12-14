package net.kender.core.sample;
import java.net.URI;
import java.util.ArrayList;

import net.kender.core.sample.custom.CType;
import net.kender.core.sample.custom.mod.mod;


public class VMC {
    private String id;
    private VType type;
    private URI url;
    private String Versionid;
    private CType loader;
    public CType getLoader() {
		return loader;
	}
	public void setLoader(CType loader) {
		this.loader = loader;
	}
	public String getVersionid() {
		return Versionid;
	}
	public void setVersionid(String versionid) {
		Versionid = versionid;
	}

    /**
     * @param id of the version
     * @param x
     * @param url
     */
    public VMC(String name,VType x,URI url,String id){
        this.id = name;
        this.Versionid = id;
        this.type = x;
        this.url = url;
    }
    public VMC(String name,VType x,URI url,String id,CType s){
        this.id = name;
        this.Versionid = id;
        this.type = x;
        this.url = url;
        this.loader = s;
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
    public boolean equals(VMC a) {
    	return id.equals(a.getID()) && type.equals(a.getType()) && url.equals(a.getUrl());
    }
    
}
