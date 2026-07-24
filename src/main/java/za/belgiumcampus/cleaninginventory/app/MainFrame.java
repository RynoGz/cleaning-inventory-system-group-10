
package za.belgiumcampus.cleaninginventory.app;

import java.awt.CardLayout;
import za.belgiumcampus.cleaninginventory.database.DatabaseConnection;
import za.belgiumcampus.cleaninginventory.controller.AuthenticationController;
import za.belgiumcampus.cleaninginventory.view.dashboard.DashboardsForm;
import za.belgiumcampus.cleaninginventory.view.materials.MaterialForm;
import za.belgiumcampus.cleaninginventory.view.reports.ReportsForm;
import za.belgiumcampus.cleaninginventory.view.suppliers.SupplierForm;
import za.belgiumcampus.cleaninginventory.view.cleaners.CleanersForm;
import za.belgiumcampus.cleaninginventory.view.login.LoginForm;
import za.belgiumcampus.cleaninginventory.model.User;
import za.belgiumcampus.cleaninginventory.view.userManagement.UserManagementForm;
import javax.swing.JOptionPane;


public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    
    private User currentUser;
    
    private final AuthenticationController authController = new AuthenticationController();
    
    private boolean isAdmin() {
        return currentUser.getRole().equalsIgnoreCase("Admin");
    }

    public MainFrame() {

    }
    
    public MainFrame(User user) {
        initComponents();
        currentUser = user;
        initializeMainFrame();
    }
    
    private void initializeMainFrame() {

        setIconImage(new javax.swing.ImageIcon(
            getClass().getResource("/images/BroomIcon.png")).getImage());

        lblUser.setText("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");

        pnlContent.setLayout(new CardLayout());

        pnlContent.add(new DashboardsForm(currentUser), "dashboard");
        pnlContent.add(new CleanersForm(), "cleaners");
        pnlContent.add(new MaterialForm(), "material");
        pnlContent.add(new SupplierForm(), "suppliers");
        pnlContent.add(new UserManagementForm(), "users");

        if (isAdmin()) {
            loadForm("dashboard");
        } else {
            loadForm("suppliers");
        }
    }
    
    private void loadForm(String name){
        CardLayout cl = (CardLayout) pnlContent.getLayout();
        cl.show(pnlContent, name);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnMaterials = new javax.swing.JButton();
        btnSuppliers = new javax.swing.JButton();
        btnCleaners = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnIssuance = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        btnUsersAction = new javax.swing.JButton();
        lblUser = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cleaning Inventory System");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frmMain"); // NOI18N
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(24, 24, 24));

        pnlTitle.setOpaque(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("<html> Inventory System</html>");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addContainerGap())
        );

        btnDashboard.setBackground(new java.awt.Color(24, 24, 24));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);

        btnMaterials.setBackground(new java.awt.Color(24, 24, 24));
        btnMaterials.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMaterials.setForeground(new java.awt.Color(255, 255, 255));
        btnMaterials.setText("Materials");
        btnMaterials.setBorderPainted(false);
        btnMaterials.setName(""); // NOI18N
        btnMaterials.addActionListener(this::btnMaterialsActionPerformed);

        btnSuppliers.setBackground(new java.awt.Color(24, 24, 24));
        btnSuppliers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuppliers.setForeground(new java.awt.Color(255, 255, 255));
        btnSuppliers.setText("Suppliers");
        btnSuppliers.setBorderPainted(false);
        btnSuppliers.addActionListener(this::btnSuppliersActionPerformed);

        btnCleaners.setBackground(new java.awt.Color(24, 24, 24));
        btnCleaners.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCleaners.setForeground(new java.awt.Color(255, 255, 255));
        btnCleaners.setText("Cleaners");
        btnCleaners.setBorderPainted(false);
        btnCleaners.addActionListener(this::btnCleanersActionPerformed);

        btnLogout.setBackground(new java.awt.Color(24, 24, 24));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Log out");
        btnLogout.setBorderPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnIssuance.setBackground(new java.awt.Color(24, 24, 24));
        btnIssuance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIssuance.setForeground(new java.awt.Color(255, 255, 255));
        btnIssuance.setText("Stock Issuance");
        btnIssuance.setBorderPainted(false);
        btnIssuance.addActionListener(this::btnIssuanceActionPerformed);

        pnlContent.setName("pnlContent"); // NOI18N

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1029, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        btnUsersAction.setBackground(new java.awt.Color(24, 24, 24));
        btnUsersAction.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUsersAction.setForeground(new java.awt.Color(255, 255, 255));
        btnUsersAction.setText("User Management");
        btnUsersAction.setBorderPainted(false);
        btnUsersAction.addActionListener(this::btnUsersActionActionPerformed);

        lblUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("Logged in as : Administrator");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMaterials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCleaners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnIssuance, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                            .addComponent(btnUsersAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCleaners, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIssuance, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsersAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
      
        if (!isAdmin()) {
            JOptionPane.showMessageDialog(this,"Dashboard is only available to administrators.");
            return;
        }
        loadForm("dashboard");        
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnMaterialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialsActionPerformed
        loadForm("material");
    }//GEN-LAST:event_btnMaterialsActionPerformed

    private void btnSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppliersActionPerformed
        loadForm("suppliers");
    }//GEN-LAST:event_btnSuppliersActionPerformed

    private void btnCleanersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanersActionPerformed
        loadForm("cleaners");
    }//GEN-LAST:event_btnCleanersActionPerformed

    private void btnIssuanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIssuanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIssuanceActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        authController.logout();
        dispose();

        LoginForm login = new LoginForm();
        login.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnUsersActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionActionPerformed
        if (!isAdmin()) {
            JOptionPane.showMessageDialog(this, "User Management is only available to administrators.");
            return;
        }

        loadForm("users");
    }//GEN-LAST:event_btnUsersActionActionPerformed

    //public static DatabaseConnection db = new DatabaseConnection();
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
//        try{
//            db.connect();
//            db.createTable();
//        } catch (ClassNotFoundException ex){
//            ex.printStackTrace();
//        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCleaners;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnIssuance;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMaterials;
    private javax.swing.JButton btnSuppliers;
    private javax.swing.JButton btnUsersAction;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
