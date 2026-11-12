package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

/** Pacientes que tienen citas con el médico que inició sesión. */
public class ListaPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaPacientes = new JTable();
	private JButton btnSeleccionarPaciente;

	/**
	 *
	 * Create the dialog.
	 */
	public ListaPacientes() {
		ImageIcon imageIcon = Recursos.imagen(Recursos.MIS_PACIENTES);
		setTitle("Mis pacientes");
		setBounds(100, 100, 970, 666);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tablaPacientes);
		scrollPane.setBounds(17, 36, 931, 557);
		contentPanel.add(scrollPane);
		JLabel label = new JLabel(imageIcon);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionarPaciente = new JButton("Ver historial clínico del paciente");
				btnSeleccionarPaciente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = tablaPacientes.getSelectedRow();
				        if (selectedRow != -1) {
				            String cedula = tablaPacientes.getValueAt(selectedRow, 0).toString();
				            String nombre = (String) tablaPacientes.getValueAt(selectedRow, 1);
				            String telefono = (String) tablaPacientes.getValueAt(selectedRow, 2);
				            String direccion = (String) tablaPacientes.getValueAt(selectedRow, 3);

				            HistorialPaciente historial = new HistorialPaciente(ListaPacientes.this, cedula, nombre, direccion, telefono);
							historial.setVisible(true);
				        } else {
				            JOptionPane.showMessageDialog(ListaPacientes.this, "Por favor, seleccione un paciente de la tabla.");
				        }
					}
				});
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
		CargarDatos.cargarPacientesDelMedico(tablaPacientes);
		{
			label.setBounds(0, -37, 970, 878);
			contentPanel.add(label);
		}
	}

}
