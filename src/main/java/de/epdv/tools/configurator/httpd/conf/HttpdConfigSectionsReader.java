/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class HttpdConfigSectionsReader {

    public static final String CONFIG_PARAM_PREFIX = "config";

    private static class Property {

        private final String key;
        private final String value;

        public Property(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public HttpdConfigSectionsReader() {
    }

    public List<HttpdConfigBlock> load(String prefix, String path) throws IOException {
        return load(prefix, this.getClass().getResourceAsStream(path));
    }

    public List<HttpdConfigBlock> load(String prefix, InputStream is) throws IOException {
        List<HttpdConfigBlock> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        HttpdConfigBlockBuilder currentBuilder = null;
        String section = null;
        String line;
        Property prop;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                if (line.startsWith("[")) {
                    section = getSection(line);
                } else {
                    prop = getProperty(prefix, section, line);
                    currentBuilder = applyProperty(result, currentBuilder, prop);
                }
            }
        }
        if (currentBuilder != null) {
            result.add(currentBuilder.buildConfig());
        }
        return result;
    }

    private HttpdConfigBlockBuilder applyProperty(List<HttpdConfigBlock> list, HttpdConfigBlockBuilder currentBuilder, Property prop) {
        HttpdConfigBlockBuilder tempBuilder = currentBuilder;
        String key = prop.getKey();
        if (key.startsWith(CONFIG_PARAM_PREFIX + ".")) {
            String temp = key.substring(CONFIG_PARAM_PREFIX.length() + 1);
            int p = temp.indexOf('.');
            String type = key.substring(0, p);
            String sect = key.substring(p + 1);
            p = temp.indexOf('.');
            String skey;
            if (p < 0) {
                skey = null;
            } else {
                skey = key.substring(p + 1);
                sect = sect.substring(0, p);
            }
            if (currentBuilder == null || !type.equals(currentBuilder.getConfigType()) || !sect.equals(currentBuilder.getConfigSection())) {
                if (currentBuilder != null) {
                    list.add(currentBuilder.buildConfig());
                }
                String prev = currentBuilder == null ? null : currentBuilder.getConfigSection();
                tempBuilder = new HttpdConfigBlockBuilder(prev, type, sect);
            }
            String val = prop.getValue();
            tempBuilder.put(skey, val);
        }
        return tempBuilder;
    }

    private String getSection(String line) throws IOException {
        String sect;
        if (line.startsWith("[") && line.startsWith("]")) {
            sect = line.substring(1, line.length() - 1).trim();
            if (!sect.isEmpty()) {
                return sect;
            }
        }
        throw new IOException("Syntax error in properties file");
    }

    private Property getProperty(String prefix, String section, String line) throws IOException {
        int p = line.indexOf('=');
        if (p < 1) {
            throw new IOException("Syntax error in properties file");
        }
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix).append('.');
        }
        if (section != null) {
            sb.append(prefix).append('.');
        }
        sb.append(line.substring(0, p));
        return new Property(sb.toString(), line.substring(p + 1));
    }
}
