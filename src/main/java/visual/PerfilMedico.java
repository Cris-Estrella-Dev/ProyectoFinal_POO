package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import accesoDatos.CargarDatos;

/** Citas del médico que inició sesión. Desde aquí se abre la consulta de un paciente. */
public class PerfilMedico extends JDialog {

	/** Anchos de las columnas dibujadas en la imagen: cédula, nombre, teléfono, dirección y fecha. */
	private static final int[] ANCHO_COLUMNAS = { 185, 188, 225, 235, 308 };

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaCitasDelMedico = new JTable();
	private JButton btnConsultar;

	/**
	 * Create the dialog.
	 */
	public PerfilMedico() {
		setTitle("Perfil médico");
		setBounds(100, 100, 1134, 916);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaCitasDelMedico.setBounds(0, 361, 1141, 416);
		tablaCitasDelMedico.setRowHeight(26);
		tablaCitasDelMedico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPanel.add(tablaCitasDelMedico);
		ImageIcon imageIcon = Recursos.imagen(Recursos.PERFIL_MEDICO);
		{
			JLabel lblNombreMedico = new JLabel("", SwingConstants.CENTER);
			lblNombreMedico.setForeground(Color.WHITE);
			lblNombreMedico.setFont(new Font("Dialog", Font.BOLD, 16));
			lblNombreMedico.setBounds(0, 292, 1141, 22);
			if (CargarDatos.getNombre_medico() != null) {
				lblNombreMedico.setText(CargarDatos.getNombre_medico());
			}
			contentPanel.add(lblNombreMedico);
		}

		ActionListener consultar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirConsulta();
			}
		};
		ActionListener salir = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		};

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnConsultar = new JButton("CONSULTAR");
				btnConsultar.addActionListener(consultar);
				btnConsultar.setActionCommand("OK");
				buttonPane.add(btnConsultar);
				getRootPane().setDefaultButton(btnConsultar);
			}
			{
				JButton btnSalir = new JButton("SALIR");
				btnSalir.addActionListener(salir);
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}

		cargarCitas();

		// Botones dibujados en la imagen de fondo: CONSULTAR y la X.
		JButton zonaConsultar = Recursos.zonaClic(new JButton());
		zonaConsultar.setBounds(965, 778, 160, 58);
		zonaConsultar.addActionListener(consultar);
		contentPanel.add(zonaConsultar);

		JButton zonaCerrar = Recursos.zonaClic(new JButton());
		zonaCerrar.setBounds(1090, 4, 44, 44);
		zonaCerrar.addActionListener(salir);
		contentPanel.add(zonaCerrar);

		{
			JLabel lblNewLabel = new JLabel(imageIcon);
			lblNewLabel.setBounds(0, 0, 1141, 855);
			contentPanel.add(lblNewLabel);
		}
	}

	private void cargarCitas() {
		CargarDatos.cargarCitasDelMedico(tablaCitasDelMedico);

		// El id de la cita se queda en el modelo pero no se muestra: la imagen
		// de fondo ya trae dibujados los cinco encabezados visibles.
		TableColumnModel columnas = tablaCitasDelMedico.getColumnModel();
		if (columnas.getColumnCount() == ANCHO_COLUMNAS.length + 1) {
			columnas.removeColumn(columnas.getColumn(0));
			for (int i = 0; i < ANCHO_COLUMNAS.length; i++) {
				columnas.getColumn(i).setPreferredWidth(ANCHO_COLUMNAS[i]);
			}
		}
	}

	private void abrirConsulta() {
		int selectedRow = tablaCitasDelMedico.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un paciente a consultar de la tabla.");
			return;
		}
		TableModel model = tablaCitasDelMedico.getModel();
		int idCita = ((Number) model.getValueAt(selectedRow, 0)).intValue();
		String cedula = model.getValueAt(selectedRow, 1).toString();
		String nombre = (String) model.getValueAt(selectedRow, 2);
		String telefono = (String) model.getValueAt(selectedRow, 3);
		String direccion = (String) model.getValueAt(selectedRow, 4);
		String fecha = (String) model.getValueAt(selectedRow, 5);

		dispose();
		Consultas consultas = new Consultas(idCita, nombre, telefono, direccion, fecha, cedula);
		consultas.setVisible(true);
	}

}
