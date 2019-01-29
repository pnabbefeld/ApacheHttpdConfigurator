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
public class HttpdConfigDataBlock {

    private final HttpdConfigDataDefinition specificationReference;
    private final HttpdConfigValuesContainer currentValues;

    public HttpdConfigDataBlock(
            HttpdConfigDataDefinition specificationReference,
            HttpdConfigValuesContainer currentValues
    ) {
        this.specificationReference = specificationReference;
        this.currentValues = currentValues;
    }

    public HttpdConfigDataDefinition getSpecificationReference() {
        return specificationReference;
    }

    public HttpdConfigValuesContainer getCurrentValues() {
        return currentValues;
    }
}
