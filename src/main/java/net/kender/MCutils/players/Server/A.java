
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
    "name",
    "type",
    "class",
    "ttl",
    "rdlength",
    "rdata",
    "cname",
    "typecovered",
    "algorithm",
    "labels",
    "origttl",
    "sigexp",
    "sigincep",
    "keytag",
    "signname",
    "signature",
    "address"
})

public class A {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("rdlength")
    private Integer rdlength;
    @JsonProperty("rdata")
    private String rdata;
    @JsonProperty("cname")
    private String cname;
    @JsonProperty("typecovered")
    private String typecovered;
    @JsonProperty("algorithm")
    private Integer algorithm;
    @JsonProperty("labels")
    private Integer labels;
    @JsonProperty("origttl")
    private Integer origttl;
    @JsonProperty("sigexp")
    private String sigexp;
    @JsonProperty("sigincep")
    private String sigincep;
    @JsonProperty("keytag")
    private Integer keytag;
    @JsonProperty("signname")
    private String signname;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("address")
    private String address;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("ttl")
    public Integer getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("rdlength")
    public Integer getRdlength() {
        return rdlength;
    }

    @JsonProperty("rdlength")
    public void setRdlength(Integer rdlength) {
        this.rdlength = rdlength;
    }

    @JsonProperty("rdata")
    public String getRdata() {
        return rdata;
    }

    @JsonProperty("rdata")
    public void setRdata(String rdata) {
        this.rdata = rdata;
    }

    @JsonProperty("cname")
    public String getCname() {
        return cname;
    }

    @JsonProperty("cname")
    public void setCname(String cname) {
        this.cname = cname;
    }

    @JsonProperty("typecovered")
    public String getTypecovered() {
        return typecovered;
    }

    @JsonProperty("typecovered")
    public void setTypecovered(String typecovered) {
        this.typecovered = typecovered;
    }

    @JsonProperty("algorithm")
    public Integer getAlgorithm() {
        return algorithm;
    }

    @JsonProperty("algorithm")
    public void setAlgorithm(Integer algorithm) {
        this.algorithm = algorithm;
    }

    @JsonProperty("labels")
    public Integer getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(Integer labels) {
        this.labels = labels;
    }

    @JsonProperty("origttl")
    public Integer getOrigttl() {
        return origttl;
    }

    @JsonProperty("origttl")
    public void setOrigttl(Integer origttl) {
        this.origttl = origttl;
    }

    @JsonProperty("sigexp")
    public String getSigexp() {
        return sigexp;
    }

    @JsonProperty("sigexp")
    public void setSigexp(String sigexp) {
        this.sigexp = sigexp;
    }

    @JsonProperty("sigincep")
    public String getSigincep() {
        return sigincep;
    }

    @JsonProperty("sigincep")
    public void setSigincep(String sigincep) {
        this.sigincep = sigincep;
    }

    @JsonProperty("keytag")
    public Integer getKeytag() {
        return keytag;
    }

    @JsonProperty("keytag")
    public void setKeytag(Integer keytag) {
        this.keytag = keytag;
    }

    @JsonProperty("signname")
    public String getSignname() {
        return signname;
    }

    @JsonProperty("signname")
    public void setSignname(String signname) {
        this.signname = signname;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
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
