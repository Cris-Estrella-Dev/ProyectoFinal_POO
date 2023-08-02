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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ListaEspecialista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JButton okButton;
	
	private final String[] columnNames = {"ID", "Nombre", "Cedula", "Especialidad", "Disponibilidad"};
	private final JTable TablaEspecialidad = new JTable();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaEspecialista dialog = new ListaEspecialista("");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaEspecialista(String especialidad) {
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		TablaEspecialidad.setModel(model);
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
		TablaEspecialidad.setBounds(17, 18, 907, 607);
		
		panel.add(TablaEspecialidad);
		JTableHeader header = TablaEspecialidad.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12)); 
        header.setEnabled(false);
        header.setBackground(Color.DARK_GRAY); 
        header.setForeground(Color.WHITE); 
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TablaEspecialidad.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		 JScrollPane scrollPane = new JScrollPane(TablaEspecialidad);
		 TablaEspecialidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		 scrollPane.setEnabled(false);
	        scrollPane.setBounds(17, 18, 907, 606);
	        panel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(especialidad);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		okButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = TablaEspecialidad.getSelectedRow();
		        if (selectedRow != -1) {
		            // Obtener la información de la fila seleccionada
		            Integer idInteger = (Integer) TablaEspecialidad.getValueAt(selectedRow, 0);
		            String id = idInteger.toString();

		            String nombre = (String) TablaEspecialidad.getValueAt(selectedRow, 1);

		            // Mostrar la información en el otro JDialog "InfoMedicoDialog"
		            RegistrarCita dialog = new RegistrarCita();
		           dialog.agregardatostextbox(id, nombre);
		           dialog.setVisible(true);
		           dispose();
		        } else {
		            // Si no se seleccionó ninguna fila, muestra un mensaje de error o realiza alguna otra acción
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un médico de la tabla.");
		        }
		    }
		});

		
		
		CargarDatos.CargarConsultaEspecifica(TablaEspecialidad, "medicos", "especialidad", especialidad);
		
	}

}
