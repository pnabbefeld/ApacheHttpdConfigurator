/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

import java.util.List;

/**
 *
 * @author peter
 */
public class NamedMultiValue {

    private final String name;

    private final List<ActivableValue> values;

    public NamedMultiValue(String name, List<ActivableValue> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<ActivableValue> getValues() {
        return values;
    }
}
