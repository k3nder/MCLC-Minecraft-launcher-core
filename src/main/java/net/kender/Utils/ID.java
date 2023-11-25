package net.kender.Utils;

public class ID {
    public String id;
    private ID(String ID){
        id = ID;
    }
    public String toString(){
        return id;
    }
    public static ID of(String a){
        return new ID(a);
    }
}
