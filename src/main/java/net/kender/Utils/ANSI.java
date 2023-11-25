package net.kender.Utils;

public class ANSI{
    private String Code;
    private ANSI(String code){
        Code = code;
    }
    public String toString(){
        return Code;
    }
    static public ANSI of(String code){
        return new ANSI(code);
    }
}