package net.kender;

import static net.kender.core.sample.MC.*;

import java.nio.file.Path;
import java.util.List;

import net.kender.core.sample.MC;
import net.kender.core.sample.Manifest;
import net.kender.core.sample.VMC;

/**
 * Unit test for simple App.
 */
public class ManifestExample {
    public static void main(String[] args) {
        //*The Manifest is used to obtain an official non-local version, which means that only non-nodified versions will be able to be obtained.
        Manifest mani = new Manifest();
        VMC last_Version = mani.getLastVersion();
        VMC last_snapshot = mani.getLastSnapshot();

        //1.20.1 is a example
        VMC version = mani.getVersion("1.20.2");

        List<VMC> list_of_versions = mani.getList();

        MC a = new MC(Path.of(MINECRAFT_PATH), version);
        a.Run();
    }
}
