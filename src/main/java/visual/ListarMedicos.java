package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import accesoDatos.CargarDatos;
import accesoDatos.GuardarDatos;

public class ListarMedicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable TablaMedicos = new JTable();
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Create the dialog.
	 */
	public ListarMedicos() {
		setBackground(new Color(112, 128, 144));
		setTitle("Listar médicos");
		setBounds(100, 100, 964, 722);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(6, 6, 952, 643);
		contentPanel.add(panel);
		panel.setLayout(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("ELIMINAR");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarSeleccionado();
					}
				});
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("MODIFICAR");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarSeleccionado();
					}
				});
				buttonPane.add(btnModificar);
			}
		}
		JTableHeader header = TablaMedicos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TablaMedicos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		TablaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(TablaMedicos);
		scrollPane.setBounds(17, 18, 907, 606);
		panel.add(scrollPane);

		cargarMedicos();
	}

	private void cargarMedicos() {
		CargarDatos.CargarConsulta(TablaMedicos, "medicos");
	}

	private void eliminarSeleccionado() {
		int fila = TablaMedicos.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar al médico?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
		if (option != JOptionPane.OK_OPTION) {
			return;
		}

		int idSeleccionado = ((Number) TablaMedicos.getValueAt(fila, 0)).intValue();
		if (GuardarDatos.eliminarMedico(idSeleccionado)) {
			JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");
			cargarMedicos();
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.\nUn médico con citas registradas no puede eliminarse.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void modificarSeleccionado() {
		int fila = TablaMedicos.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Columnas de la tabla medicos: id, nombre, cédula, especialidad, disponibilidad.
		int id = ((Number) TablaMedicos.getValueAt(fila, 0)).intValue();
		RegistrarMedico editor = new RegistrarMedico(id,
				(String) TablaMedicos.getValueAt(fila, 1),
				(String) TablaMedicos.getValueAt(fila, 2),
				(String) TablaMedicos.getValueAt(fila, 3),
				(String) TablaMedicos.getValueAt(fila, 4));
		editor.setModal(true);
		editor.setVisible(true);
		cargarMedicos();
	}

}
