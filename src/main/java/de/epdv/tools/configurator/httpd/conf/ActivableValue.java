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
public class ActivableValue {

    private final String value;
    private boolean active;

    public ActivableValue(String value, boolean active) {
        this.value = value;
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getValue() {
        return value;
    }
}
