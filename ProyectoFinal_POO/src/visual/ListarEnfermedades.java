package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

public class ListarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaEnfermoDe = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEnfermedades dialog = new ListarEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedades() {
		setTitle("Listar enfermedades");
		setBounds(100, 100, 829, 573);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(6, 6, 817, 494);
			contentPanel.add(panel);
			panel.setLayout(null);
			tablaEnfermoDe.setBounds(6, 6, 805, 488);
			panel.add(tablaEnfermoDe);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAddEnfermedad = new JButton("AÑADIR");
				btnAddEnfermedad.setActionCommand("OK");
				buttonPane.add(btnAddEnfermedad);
				getRootPane().setDefaultButton(btnAddEnfermedad);
			}
			{
				JButton btnCancelar = new JButton("CANCELAR");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
