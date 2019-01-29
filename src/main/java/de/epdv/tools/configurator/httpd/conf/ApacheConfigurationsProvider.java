/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

import de.epdv.tools.configurator.httpd.CfgEdTopComponent;
import de.epdv.tools.configurator.httpd.options.HttpdConfigReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 *
 * @author peter
 */
public class ApacheConfigurationsProvider {

    private static ApacheConfigurationsProvider INSTANCE;

    public static ApacheConfigurationsProvider getDefault() {
        if (INSTANCE == null) {
            INSTANCE = new ApacheConfigurationsProvider();
        }
        return INSTANCE;
    }

    private ApacheConfigurationsProvider() {
    }

    public List<HttpdConfigReference> getConfigurations() {
        return loadData();
    }

    public void saveConfigurations(List<HttpdConfigReference> refs) {
        Preferences prefs = NbPreferences.forModule(CfgEdTopComponent.class);
        prefs.put("version", "1.0");
        if (refs.size() > 0) {
            prefs.putInt("entries", refs.size());
            int index = 1;
            for (HttpdConfigReference ref : refs) {
                prefs.put("httpd.main-doc." + index, ref.getOriginalConfigFilePath());
                prefs.put("httpd.main-work." + index, ref.getWorkingCopyPath());
                index++;
            }
        } else {
            prefs.putInt("entries", 1);
            prefs.put("httpd.main-doc.1", "/etc/httpd/conf/httpd.conf");
            prefs.put("httpd.main-work.1", "/home/peter/NetBeansProjects/ApacheHttpdConfigurator/httpd-config-work");
        }
    }

    private List<HttpdConfigReference> loadData() {
        Preferences prefs = NbPreferences.forModule(CfgEdTopComponent.class);
        List<HttpdConfigReference> refs = new ArrayList<>();
        String version = prefs.get("version", null);
        if (version == null) {
            saveConfigurations(refs);
            version = prefs.get("version", null);
        }
        if (!"1.0".equals(version)) {
            Exceptions.printStackTrace(new IOException("Version of stored data does not fit"));
        } else {
            HttpdConfigReference ref;
            int entries = prefs.getInt("entries", 0);
            for (int i = 1; i <= entries; i++) {
                String original = prefs.get("httpd.main-doc." + i, "");
                String workfile = prefs.get("httpd.main-work." + i, "");
                if (!original.trim().isEmpty() && !workfile.trim().isEmpty()) {
                    ref = new HttpdConfigReference(original, workfile);
                    refs.add(ref);
                }
            }
        }
        return refs;
    }
}
