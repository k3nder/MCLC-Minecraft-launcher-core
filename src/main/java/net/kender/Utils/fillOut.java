package net.kender.Utils;

import java.util.HashMap;
import java.util.Map;

public class fillOut {
    private Map<String, String> exports;

    public fillOut(Map<String, String> exports) {
        this.exports = exports;
    }

    public String fill(String keyConst, String input) {
        int key1 = input.indexOf(keyConst);
        int key2 = input.lastIndexOf(keyConst);
        if (exports.containsKey(input.substring(key1 + 1, key2))) {
            return input.replace(input.substring(key1, key2 + 1), exports.get(input.substring(key1 + 1, key2)));
        }
        return input.substring(key1 + 1, key2);

    }

    public String fill(String keyConst, String keyconst, String input) {
        int key1 = input.indexOf(keyConst);
        int key2 = input.lastIndexOf(keyconst);
        if (!input.contains(keyconst)) {
            return input;
        }
        if (exports.containsKey(input.substring(key1 + 1, key2))) {
            if (keyConst.length() == 2) {
                key1 = key1 + 1;
            }
            return input.replace(input.substring(key1, key2 + 1), exports.get(input.substring(key1 + 1, key2)));
        }
        return input.substring(key1 + 1, key2);

    }

    public static void main(String[] args) {
        Map<String, String> s = new HashMap<String, String>();
        s.put("launcher_name", "LT");
        fillOut a = new fillOut(s);
        System.out.println(a.fill("{", "}", "holamellamos ae{launcher_name} esto no se remplaza"));
    }
}
