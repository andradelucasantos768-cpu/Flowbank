package flowbank;

import flowbank.controlador.BancoController;
import flowbank.interfacegrafica.TelaLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar LookAndFeel");
        }
        SwingUtilities.invokeLater(() -> {
            BancoController controller = new BancoController();
            new TelaLogin(controller).setVisible(true);
        });
    }
}
