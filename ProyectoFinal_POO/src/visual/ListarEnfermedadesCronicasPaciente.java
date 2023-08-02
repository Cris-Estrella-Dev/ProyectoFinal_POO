package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEnfermedadesCronicasPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaEnfermedadesCronicasPaciente = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEnfermedadesCronicasPaciente dialog = new ListarEnfermedadesCronicasPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedadesCronicasPaciente() {
		setTitle("Enfermedades crónicas del paciente");
		setBounds(100, 100, 964, 659);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaEnfermedadesCronicasPaciente.setBounds(20, 17, 925, 557);
		contentPanel.add(tablaEnfermedadesCronicasPaciente);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						HistorialPaciente historialPaciente =  new HistorialPaciente();
						historialPaciente.setModal(true);
						historialPaciente.setVisible(true);
						
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

}
