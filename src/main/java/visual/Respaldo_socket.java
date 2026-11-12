package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import accesoDatos.CargarDatos;
import accesoDatos.ClienteRespaldo;
import accesoDatos.MiServidor;

/**
 * Respaldo de los médicos registrados. Las filas se envían por socket al
 * servidor de respaldo ({@link MiServidor}), que las guarda en un archivo.
 */
public class Respaldo_socket extends JDialog {

	/** Separador de campos en el archivo de respaldo. */
	private static final String SEPARADOR = "    ";

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaMedicos = new JTable();

	/**
	 * Create the dialog.
	 */
	public Respaldo_socket() {
		setTitle("Respaldo");
		setBounds(100, 100, 986, 641);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRespaldarInfo = new JButton("Respaldar información");
				btnRespaldarInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						respaldar();
					}
				});
				buttonPane.add(btnRespaldarInfo);
				getRootPane().setDefaultButton(btnRespaldarInfo);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnSalir);
			}
		}

		JTableHeader header = tablaMedicos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablaMedicos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(tablaMedicos);
		scrollPane.setBounds(31, 6, 907, 550);
		contentPanel.add(scrollPane);

		CargarDatos.cargarMedicosParaRespaldo(tablaMedicos);
	}

	private void respaldar() {
		TableModel model = tablaMedicos.getModel();
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No hay médicos registrados para respaldar.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		List<String> lineas = new ArrayList<>();
		for (int fila = 0; fila < model.getRowCount(); fila++) {
			StringBuilder linea = new StringBuilder();
			for (int columna = 0; columna < model.getColumnCount(); columna++) {
				if (columna > 0) {
					linea.append(SEPARADOR);
				}
				linea.append(model.getValueAt(fila, columna));
			}
			lineas.add(linea.toString());
		}

		try {
			String archivo = ClienteRespaldo.enviar(MiServidor.PUERTO, lineas);
			JOptionPane.showMessageDialog(this, "Respaldo realizado exitosamente.\n" + lineas.size() + " médicos guardados en:\n" + archivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "No se pudo completar el respaldo: el servidor de respaldo no responde.\n" + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
