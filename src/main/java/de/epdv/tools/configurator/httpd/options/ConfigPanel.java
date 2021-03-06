/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.epdv.tools.configurator.httpd.options;

import de.epdv.tools.configurator.httpd.CfgEdTopComponent;
import de.epdv.tools.configurator.httpd.conf.ApacheConfigurationsProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

final class ConfigPanel extends JPanel {

    private final ConfigOptionsPanelController controller;

    ConfigPanel(ConfigOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOrig = new JLabel();
        txtOrig = new JTextField();
        btnBrowseOrig = new JButton();
        lblWork = new JLabel();
        txtWork = new JTextField();
        btnBrowseWork = new JButton();
        sclHint = new JScrollPane();
        txtHint = new JTextPane();

        lblOrig.setLabelFor(txtOrig);
        Mnemonics.setLocalizedText(lblOrig, NbBundle.getMessage(ConfigPanel.class, "ConfigPanel.lblOrig.text")); // NOI18N

        Mnemonics.setLocalizedText(btnBrowseOrig, NbBundle.getMessage(ConfigPanel.class, "ConfigPanel.btnBrowseOrig.text")); // NOI18N

        lblWork.setLabelFor(txtWork);
        Mnemonics.setLocalizedText(lblWork, NbBundle.getMessage(ConfigPanel.class, "ConfigPanel.lblWork.text")); // NOI18N

        Mnemonics.setLocalizedText(btnBrowseWork, NbBundle.getMessage(ConfigPanel.class, "ConfigPanel.btnBrowseWork.text")); // NOI18N

        sclHint.setEnabled(false);

        txtHint.setEditable(false);
        txtHint.setBackground(UIManager.getDefaults().getColor("Panel.background"));
        txtHint.setText(NbBundle.getMessage(ConfigPanel.class, "ConfigPanel.txtHint.text")); // NOI18N
        sclHint.setViewportView(txtHint);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(sclHint, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblOrig)
                            .addComponent(lblWork))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOrig, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtWork))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(btnBrowseOrig, GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBrowseWork, GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrig)
                    .addComponent(txtOrig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseOrig))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWork)
                    .addComponent(txtWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseWork))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(sclHint, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(ConfigPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(ConfigPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        List<HttpdConfigReference> refs = ApacheConfigurationsProvider.getDefault().getConfigurations();
        initialise(refs);
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(ConfigPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(ConfigPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        List<HttpdConfigReference> refs = new ArrayList<>();
        String original = txtOrig.getText();
        String workfile = txtWork.getText();
        refs.add(new HttpdConfigReference(original, workfile));
        ApacheConfigurationsProvider.getDefault().saveConfigurations(refs);
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBrowseOrig;
    private JButton btnBrowseWork;
    private JLabel lblOrig;
    private JLabel lblWork;
    private JScrollPane sclHint;
    private JTextPane txtHint;
    private JTextField txtOrig;
    private JTextField txtWork;
    // End of variables declaration//GEN-END:variables

    private void initialise(List<HttpdConfigReference> refs) {
        if (refs != null && refs.size() > 0) {
            HttpdConfigReference ref = refs.get(0);
            txtOrig.setText(ref.getOriginalConfigFilePath());
            txtWork.setText(ref.getWorkingCopyPath());
        } else {
            txtOrig.setText("");
            txtWork.setText("");
        }
    }
}
