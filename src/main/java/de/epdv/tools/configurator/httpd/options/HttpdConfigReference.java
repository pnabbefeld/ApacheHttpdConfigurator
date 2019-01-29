/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.options;

/**
 *
 * @author peter
 */
public class HttpdConfigReference {

    private final String originalConfigFilePath;
    private final String workingCopyPath;

    public HttpdConfigReference(String original, String workfile) {
        this.originalConfigFilePath = original;
        this.workingCopyPath = workfile;
    }

    public String getOriginalConfigFilePath() {
        return originalConfigFilePath;
    }

    public String getWorkingCopyPath() {
        return workingCopyPath;
    }
}
