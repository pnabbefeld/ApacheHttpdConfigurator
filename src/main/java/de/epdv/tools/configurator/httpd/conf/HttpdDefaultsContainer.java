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
public abstract class HttpdDefaultsContainer {

    private final Object value;

    public HttpdDefaultsContainer(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
