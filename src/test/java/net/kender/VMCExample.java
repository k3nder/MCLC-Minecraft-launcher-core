package net.kender;

import static net.kender.core.sample.MC.MINECRAFT_PATH;

import java.nio.file.Path;

import net.kender.core.sample.MC;
import net.kender.core.sample.VMC;
import net.kender.core.sample.VType;

public class VMCExample {
    public static void main(String[] args) {
        //*VMC is used to have a version of an object but it also allows the use of modified versions since their use is only local unless they are obtained through a manifest. 

        // Use VMC to load local version
        //VMC a = new VMC("id of local version ",type of version,null) example:
        VMC a = new VMC("quilt-loader-0.20.0-1.20.1",VType.custom,null);
        MC m = new MC(Path.of(MINECRAFT_PATH),a);
        m.Run();
    }
}
