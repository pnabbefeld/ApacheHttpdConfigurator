/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

import java.util.ArrayList;
import java.util.List;

public class HttpdMultiplDefaultValuesContainer extends HttpdDefaultsContainer {

    private static NamedMultiValue createMultiValue(String settingName, List<String> lines) {
        List<ActivableValue> values = new ArrayList<>();
        boolean active;
        String temp;
        for (String line : lines) {
            if (line.startsWith("#")) {
                temp = line.substring(1).trim();
                active = false;
            } else {
                temp = line.trim();
                active = true;
            }
            if (temp.startsWith(settingName)) {
                temp = temp.substring(settingName.length()).trim();
                values.add(new ActivableValue(temp, active));
            }
        }
        return new NamedMultiValue(settingName, values);
    }

    public HttpdMultiplDefaultValuesContainer(String settingName, List<String> lines) {
        super(createMultiValue(settingName, lines));
    }

    @Override
    public NamedMultiValue getValue() {
        return (NamedMultiValue)super.getValue();
    }
}
