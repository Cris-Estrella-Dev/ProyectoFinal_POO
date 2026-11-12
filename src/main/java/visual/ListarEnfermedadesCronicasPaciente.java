package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

/** Enfermedades crónicas que se le han diagnosticado a un paciente. */
public class ListarEnfermedadesCronicasPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaEnfermedadesCronicasPaciente = new JTable();

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedadesCronicasPaciente(Window owner, String cedula) {
		super(owner, "Enfermedades crónicas del paciente", ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 964, 659);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tablaEnfermedadesCronicasPaciente);
		scrollPane.setBounds(20, 17, 925, 557);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
		CargarDatos.cargarEnfermedadesCronicasPaciente(tablaEnfermedadesCronicasPaciente, cedula);
	}

}
