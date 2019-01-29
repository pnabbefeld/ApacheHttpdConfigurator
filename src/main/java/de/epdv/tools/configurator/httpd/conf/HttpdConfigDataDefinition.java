/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

/**
 *
 * @author peter
 */
public class HttpdConfigDataDefinition implements HttpdConfigBlock {

    private final String preferredFileId;
    private final String settingName;
    private final String preferredPredecessor;
    private final String description;
    private final HttpdDefaultsContainer httpdDefaults;
    private final HttpdDefaultsContainer archDefaults;
    private String preferredSuccessor;

    public HttpdConfigDataDefinition(
            String preferredFileId,
            String settingName,
            String preferredPredecessor,
            String description,
            HttpdDefaultsContainer httpdDefaults,
            HttpdDefaultsContainer archDefaults
    ) {
        this.preferredFileId = preferredFileId;
        this.settingName = settingName;
        this.preferredPredecessor = preferredPredecessor;
        this.description = description;
        this.httpdDefaults = httpdDefaults;
        this.archDefaults = archDefaults;
    }

    @Override
    public String getPreferredFileId() {
        return preferredFileId;
    }

    @Override
    public String getSettingName() {
        return settingName;
    }

    public String getPreferredPredecessor() {
        return preferredPredecessor;
    }

    public String getDescription() {
        return description;
    }

    public void setPreferredSuccessor(String preferredSuccessor) {
        this.preferredSuccessor = preferredSuccessor;
    }

    public String getPreferredSuccessor() {
        return preferredSuccessor;
    }

    public HttpdDefaultsContainer getHttpdDefaults() {
        return httpdDefaults;
    }

    public HttpdDefaultsContainer getArchDefaults() {
        return archDefaults;
    }
}
