/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.action;

import de.epdv.tools.configurator.httpd.conf.ApacheConfigurationsProvider;
import de.epdv.tools.configurator.httpd.options.HttpdConfigReference;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Apache",
        id = "de.epdv.tools.configurator.httpd.action.CfgEditorAction"
)
@ActionRegistration(
        displayName = "#CTL_CfgEditorAction"
)
@ActionReference(
        path = "Menu/Apache",
        position = 3333
)
@Messages("CTL_CfgEditorAction=Httpd Configuration Editor")
public final class CfgEditorAction implements ActionListener {

    private static class ConfigWrapper {

        private final HttpdConfigReference ref;

        public ConfigWrapper(HttpdConfigReference ref) {
            this.ref = ref;
        }

        public String getTooltip() {
            return "Working copy: " + ref.getWorkingCopyPath();
        }

        @Override
        public String toString() {
            return ref.getOriginalConfigFilePath();
        }
    }

    private static class ConfigWrapperRenderer extends JLabel implements ListCellRenderer<ConfigWrapper> {

        private final DefaultListCellRenderer delegate = new DefaultListCellRenderer();

        public ConfigWrapperRenderer() {
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ConfigWrapper> list, ConfigWrapper value, int index, boolean isSelected, boolean cellHasFocus) {
            HttpdConfigReference ref = value.ref;
            String text = ref.getOriginalConfigFilePath();
            String tip = "Working copy: " + ref.getWorkingCopyPath();
            JLabel crc = (JLabel)delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            crc.setText(text);
            crc.setToolTipText(tip);
            return crc;
        }
    }

    public CfgEditorAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<HttpdConfigReference> configs = ApacheConfigurationsProvider.getDefault().getConfigurations();
        JDialog dlg = new JDialog();
        JLabel lblConfig = new JLabel("Select");
        dlg.add(lblConfig, BorderLayout.LINE_START);
        JComboBox<ConfigWrapper> cbxConfig = new JComboBox<>(createConfigComboBoxModel(configs));
        cbxConfig.setRenderer(new ConfigWrapperRenderer());
        dlg.add(cbxConfig, BorderLayout.CENTER);
    }

    private ComboBoxModel<ConfigWrapper> createConfigComboBoxModel(List<HttpdConfigReference> configRefs) {
        DefaultComboBoxModel<ConfigWrapper> dcbm = new DefaultComboBoxModel<>();
        configRefs.forEach((ref) -> {
            dcbm.addElement(new ConfigWrapper(ref));
        });
        return dcbm;
    }
}
