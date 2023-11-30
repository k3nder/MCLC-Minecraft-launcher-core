package net.kender;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import net.kender.core.sample.MC;
import net.kender.core.sample.Quikplays.profile;

public class Profile_Example {
    public static void main(String[] args) {
        Map<Integer,profile> map = new HashMap<Integer,profile>();
        // * The profiles cannot be built directly, you need to read a json with the
        // profiles to be able to manipulate them
        // List<profile> l = profile.readAll(); example
        List<profile> l = profile.readAll(new File("src\\test\\java\\net\\kender\\launcher_profiles.json"));
        int index = 0;
        for (profile a : l) {
            System.out.println("--------" + a.getKey() + "--------");
            System.out.println("jre: " + a.getJre());
            System.out.println("game dir: " + a.getGameDir());
            // using getVersion gives you a VMC
            System.out.println("version: " + a.getVersion().getID());
            System.out.println("index to select " + index);
            map.put(index, a);
            index++;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("select profile");
        int selection = sc.nextInt();
        //you can add the profile in the MC builder to be able to add it
        MC m = new MC(map.get(selection));
        m.Run();

    }
}
