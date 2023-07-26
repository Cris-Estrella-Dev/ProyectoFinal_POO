package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ListaPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaPacientes = new JTable();
	private JButton btnSeleccionarPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaPacientes dialog = new ListaPacientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaPacientes() {
		setTitle("Mis pacientes");
		setBounds(100, 100, 970, 666);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaPacientes.setBounds(21, 17, 931, 576);
		contentPanel.add(tablaPacientes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionarPaciente = new JButton("Ver historial clínico del paciente");
				btnSeleccionarPaciente.setActionCommand("OK");
				buttonPane.add(btnSeleccionarPaciente);
				getRootPane().setDefaultButton(btnSeleccionarPaciente);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

}
