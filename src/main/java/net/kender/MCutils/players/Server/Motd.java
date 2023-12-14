
package net.kender.MCutils.players.Server;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "raw",
    "clean",
    "html"
})

public class Motd {

    @JsonProperty("raw")
    private List<String> raw;
    @JsonProperty("clean")
    private List<String> clean;
    @JsonProperty("html")
    private List<String> html;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("raw")
    public List<String> getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(List<String> raw) {
        this.raw = raw;
    }

    @JsonProperty("clean")
    public List<String> getClean() {
        return clean;
    }

    @JsonProperty("clean")
    public void setClean(List<String> clean) {
        this.clean = clean;
    }

    @JsonProperty("html")
    public List<String> getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(List<String> html) {
        this.html = html;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
