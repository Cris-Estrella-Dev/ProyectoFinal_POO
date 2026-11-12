package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import accesoDatos.*;
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

/** Médicos de una especialidad, para elegir con quién se registra la cita. */
public class ListaEspecialista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JButton okButton;
	private final JTable TablaEspecialidad = new JTable();

	/**
	 * Create the dialog.
	 */
	public ListaEspecialista(final RegistrarCita registrarCita, String especialidad) {
		super(registrarCita, "Especialistas en " + especialidad, true);
		setBackground(new Color(112, 128, 144));
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

		JTableHeader header = TablaEspecialidad.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TablaEspecialidad.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(TablaEspecialidad);
		TablaEspecialidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setBounds(17, 18, 907, 606);
		panel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Seleccionar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		okButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = TablaEspecialidad.getSelectedRow();
		        if (selectedRow != -1) {
		            String id = TablaEspecialidad.getValueAt(selectedRow, 0).toString();
		            String nombre = (String) TablaEspecialidad.getValueAt(selectedRow, 1);

		            registrarCita.agregardatostextbox(id, nombre);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(ListaEspecialista.this, "Por favor, seleccione un médico de la tabla.");
		        }
		    }
		});

		CargarDatos.CargarConsultaEspecifica(TablaEspecialidad, "medicos", "especialidad", especialidad);

	}

}
