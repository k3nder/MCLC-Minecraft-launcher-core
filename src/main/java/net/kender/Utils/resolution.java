package net.kender.Utils;

public class resolution {
    public double width;
    public double height;
    private resolution(double w,double h){
        width = w;
        height = h;
    }
    public static resolution of(double width,double height){
        return new resolution(width, height);
    }
}
