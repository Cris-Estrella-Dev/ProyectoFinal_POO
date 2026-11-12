package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

/** Catálogo de enfermedades, para elegir la que se diagnostica en una consulta. */
public class ListarEnfermedades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaEnfermoDe = new JTable();
	private JButton btnAddEnfermedad;

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedades(final Consultas consultas) {
		super(consultas, "Listar enfermedades", true);
		setBounds(100, 100, 829, 573);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(112, 128, 144));
			panel.setBounds(6, 6, 817, 494);
			contentPanel.add(panel);
			panel.setLayout(null);
			tablaEnfermoDe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(tablaEnfermoDe);
			scrollPane.setBounds(6, 6, 805, 464);
			panel.add(scrollPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 btnAddEnfermedad = new JButton("AÑADIR");
				btnAddEnfermedad.setActionCommand("OK");
				buttonPane.add(btnAddEnfermedad);
				getRootPane().setDefaultButton(btnAddEnfermedad);
			}
			{
				JButton btnCancelar = new JButton("SALIR");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}

			btnAddEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedrow = tablaEnfermoDe.getSelectedRow();
					if (selectedrow != -1) {
			            String id_enfermo = tablaEnfermoDe.getValueAt(selectedrow, 0).toString();
			            String nombre = (String) tablaEnfermoDe.getValueAt(selectedrow, 1);

						consultas.agregardatosenfermedad(nombre, id_enfermo);
						dispose();
			        } else {
			            JOptionPane.showMessageDialog(ListarEnfermedades.this, "Por favor, seleccione una enfermedad de la tabla.");
			        }
				}
			});
		}
		CargarDatos.CargarConsulta(tablaEnfermoDe, "enfermedad");
	}

}
