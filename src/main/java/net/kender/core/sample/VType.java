package net.kender.core.sample;
/**
 * @author Kristian
 * @see {@link VTC} vo convert vtype to string and string to vtype
 * type of versions ex:
 * custom is a modify version or profile
 * realse a version further recient
 * snapshot a bet further recient
 * def a unknown default
 */
public enum VType {
    release,
    snapshot,
    def,
    custom
}
