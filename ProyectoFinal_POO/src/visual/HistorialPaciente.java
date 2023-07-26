package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCedulaPaciente;
	private JLabel lblNombrePaciente;
	private JLabel lblDireccionPaciente;
	private JLabel lblTelefonoCliente;
	private final JTable tablaHistorialClinico = new JTable();
	private JButton btnVerEnfermedadesCronicas;
	private JButton btnVerVacunasPaciente;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialPaciente dialog = new HistorialPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HistorialPaciente() {
		setTitle("Paciente");
		setBounds(100, 100, 1157, 775);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(347, 28, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNombrePaciente = new JLabel("");
			lblNombrePaciente.setBounds(408, 16, 149, 38);
			contentPanel.add(lblNombrePaciente);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cedula:");
			lblNewLabel_1.setBounds(45, 28, 61, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblCedulaPaciente = new JLabel("");
			lblCedulaPaciente.setBounds(118, 28, 189, 16);
			contentPanel.add(lblCedulaPaciente);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Dirección:");
			lblNewLabel_2.setBounds(601, 28, 64, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblDireccionPaciente = new JLabel("");
			lblDireccionPaciente.setBounds(678, 6, 226, 69);
			contentPanel.add(lblDireccionPaciente);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Telefono:");
			lblNewLabel_3.setBounds(916, 28, 64, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			lblTelefonoCliente = new JLabel("");
			lblTelefonoCliente.setBounds(992, 28, 159, 16);
			contentPanel.add(lblTelefonoCliente);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("HISTORIAL CLINICO");
			lblNewLabel_4.setBounds(496, 155, 135, 16);
			contentPanel.add(lblNewLabel_4);
		}
		tablaHistorialClinico.setBounds(6, 192, 1145, 510);
		contentPanel.add(tablaHistorialClinico);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerEnfermedadesCronicas = new JButton("Ver enfermedades cronicas del paciente");
				btnVerEnfermedadesCronicas.setActionCommand("OK");
				buttonPane.add(btnVerEnfermedadesCronicas);
			}
			{
				btnVerVacunasPaciente = new JButton("Ver vacunas del paciente");
				btnVerVacunasPaciente.setActionCommand("OK");
				buttonPane.add(btnVerVacunasPaciente);
				getRootPane().setDefaultButton(btnVerVacunasPaciente);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

}
