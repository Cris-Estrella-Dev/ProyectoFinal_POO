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

/** Catálogo de vacunas, para elegir la que se aplica en una consulta. */
public class ListarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaVacunadoDe = new JTable();
	private JButton btnAddVacuna;

	/**
	 * Create the dialog.
	 */
	public ListarVacunas(final Consultas consultas) {
		super(consultas, "Listar vacunas", true);
		setBounds(100, 100, 955, 703);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaVacunadoDe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tablaVacunadoDe);
		scrollPane.setBounds(17, 17, 920, 599);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAddVacuna = new JButton("AÑADIR");
				btnAddVacuna.setActionCommand("OK");
				buttonPane.add(btnAddVacuna);
				getRootPane().setDefaultButton(btnAddVacuna);

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

			btnAddVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedrow = tablaVacunadoDe.getSelectedRow();
					if (selectedrow != -1) {
			            String id_vacuna = tablaVacunadoDe.getValueAt(selectedrow, 0).toString();
			            String nombre = (String) tablaVacunadoDe.getValueAt(selectedrow, 1);

						consultas.agregardatosvacuna(nombre, id_vacuna);
						dispose();
			        } else {
			            JOptionPane.showMessageDialog(ListarVacunas.this, "Por favor, seleccione una vacuna de la tabla.");
			        }
				}
			});

		}
		CargarDatos.CargarConsulta(tablaVacunadoDe, "vacuna");

	}

}
