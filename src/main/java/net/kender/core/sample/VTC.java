package net.kender.core.sample;

public class VTC{
    /**
     * @param a {@link VType} to convert
     * @return string converted
     */
    public static String toString(VType a){
        return a.name();
    }
    /**
     * @param x a String to convert
     * @return a {@link VType} converted
     */
    public static VType toVType(String x){
        if(x.equals("release")){
            return VType.release;
        }else if(x.equals("snapshot")){
            return VType.snapshot;
        }
        return VType.def;
    }

}
