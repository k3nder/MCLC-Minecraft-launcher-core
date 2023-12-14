package net.kender.core.Json.modpack.mrpack;

import com.fasterxml.jackson.annotation.JsonProperty;

public class dependences {
    public String minecraft;
    @JsonProperty("fabric-loader")
    public String fabricLoader;
}
