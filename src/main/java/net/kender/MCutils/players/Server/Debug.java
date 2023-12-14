
package net.kender.MCutils.players.Server;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ping",
    "query",
    "srv",
    "querymismatch",
    "ipinsrv",
    "cnameinsrv",
    "animatedmotd",
    "cachehit",
    "cachetime",
    "cacheexpire",
    "apiversion",
    "dns",
    "error"
})

public class Debug {

    @JsonProperty("ping")
    private Boolean ping;
    @JsonProperty("query")
    private Boolean query;
    @JsonProperty("srv")
    private Boolean srv;
    @JsonProperty("querymismatch")
    private Boolean querymismatch;
    @JsonProperty("ipinsrv")
    private Boolean ipinsrv;
    @JsonProperty("cnameinsrv")
    private Boolean cnameinsrv;
    @JsonProperty("animatedmotd")
    private Boolean animatedmotd;
    @JsonProperty("cachehit")
    private Boolean cachehit;
    @JsonProperty("cachetime")
    private Integer cachetime;
    @JsonProperty("cacheexpire")
    private Integer cacheexpire;
    @JsonProperty("apiversion")
    private Integer apiversion;
    @JsonProperty("dns")
    private Dns dns;
    @JsonProperty("error")
    private Error error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("ping")
    public Boolean getPing() {
        return ping;
    }

    @JsonProperty("ping")
    public void setPing(Boolean ping) {
        this.ping = ping;
    }

    @JsonProperty("query")
    public Boolean getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Boolean query) {
        this.query = query;
    }

    @JsonProperty("srv")
    public Boolean getSrv() {
        return srv;
    }

    @JsonProperty("srv")
    public void setSrv(Boolean srv) {
        this.srv = srv;
    }

    @JsonProperty("querymismatch")
    public Boolean getQuerymismatch() {
        return querymismatch;
    }

    @JsonProperty("querymismatch")
    public void setQuerymismatch(Boolean querymismatch) {
        this.querymismatch = querymismatch;
    }

    @JsonProperty("ipinsrv")
    public Boolean getIpinsrv() {
        return ipinsrv;
    }

    @JsonProperty("ipinsrv")
    public void setIpinsrv(Boolean ipinsrv) {
        this.ipinsrv = ipinsrv;
    }

    @JsonProperty("cnameinsrv")
    public Boolean getCnameinsrv() {
        return cnameinsrv;
    }

    @JsonProperty("cnameinsrv")
    public void setCnameinsrv(Boolean cnameinsrv) {
        this.cnameinsrv = cnameinsrv;
    }

    @JsonProperty("animatedmotd")
    public Boolean getAnimatedmotd() {
        return animatedmotd;
    }

    @JsonProperty("animatedmotd")
    public void setAnimatedmotd(Boolean animatedmotd) {
        this.animatedmotd = animatedmotd;
    }

    @JsonProperty("cachehit")
    public Boolean getCachehit() {
        return cachehit;
    }

    @JsonProperty("cachehit")
    public void setCachehit(Boolean cachehit) {
        this.cachehit = cachehit;
    }

    @JsonProperty("cachetime")
    public Integer getCachetime() {
        return cachetime;
    }

    @JsonProperty("cachetime")
    public void setCachetime(Integer cachetime) {
        this.cachetime = cachetime;
    }

    @JsonProperty("cacheexpire")
    public Integer getCacheexpire() {
        return cacheexpire;
    }

    @JsonProperty("cacheexpire")
    public void setCacheexpire(Integer cacheexpire) {
        this.cacheexpire = cacheexpire;
    }

    @JsonProperty("apiversion")
    public Integer getApiversion() {
        return apiversion;
    }

    @JsonProperty("apiversion")
    public void setApiversion(Integer apiversion) {
        this.apiversion = apiversion;
    }

    @JsonProperty("dns")
    public Dns getDns() {
        return dns;
    }

    @JsonProperty("dns")
    public void setDns(Dns dns) {
        this.dns = dns;
    }

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
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
