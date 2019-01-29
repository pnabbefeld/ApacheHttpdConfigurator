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
public class HttpdSingleDefaultContainer extends HttpdDefaultsContainer {

    public HttpdSingleDefaultContainer(String value) {
        super(value);
    }

    @Override
    public String getValue() {
        return (String)super.getValue();
    }
}
