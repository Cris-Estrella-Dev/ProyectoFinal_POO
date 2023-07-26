package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.GuardarDatos;

import javax.swing.JTable;

public class ListarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaVacunadoDe = new JTable();
	private JButton btnAddVacuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarVacunas dialog = new ListarVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVacunas() {
		setTitle("Listar vacunas");
		setBounds(100, 100, 955, 703);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaVacunadoDe.setBounds(6, 6, 943, 624);
		contentPanel.add(tablaVacunadoDe);
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
				JButton btnCancelar = new JButton("CANCELAR");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
				
			}
			
		}
		
	}

}
