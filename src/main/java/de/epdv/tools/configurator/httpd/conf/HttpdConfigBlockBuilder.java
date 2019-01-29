/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author peter
 */
class HttpdConfigBlockBuilder {

    private final String previousSection;
    private final String configType;
    private final String configSection;
    private final Map<String, String> properties = new TreeMap<>();
    private String comment;

    HttpdConfigBlockBuilder(String previousSection, String configType, String configSection) {
        this.previousSection = previousSection == null ? HttpdConfigConstants.NAME_CONFIG_START : previousSection;
        this.configType = configType;
        this.configSection = configSection;
    }

    public String getConfigType() {
        return configType;
    }

    public String getConfigSection() {
        return configSection;
    }

    public void put(String skey, String val) {
        if ("header".equals(configSection)) {
            if (skey != null) {
                throw new IllegalArgumentException();
            }
            comment = val;
        } else if (skey == null) {
            throw new NullPointerException();
        } else if ("comment".equals(skey)) {
            comment = val;
        } else {
            properties.put(skey, val);
        }
    }

    public HttpdConfigBlock buildConfig() {
        String settingName = properties.get("name");
        final HttpdDefaultsContainer httpdDefaults = buildDefaultsConfig("httpd");
        final HttpdDefaultsContainer archDefaults = buildDefaultsConfig("arch");
        return new HttpdConfigDataDefinition(configType, settingName, previousSection, comment, httpdDefaults, archDefaults);
    }

    private HttpdDefaultsContainer buildDefaultsConfig(String defaultsType) {
        return new HttpdSingleDefaultContainer(properties.get(defaultsType + "-default"));
    }
}
