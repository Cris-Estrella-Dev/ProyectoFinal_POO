package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class PerfilMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable TablaCitasDelMedico = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PerfilMedico dialog = new PerfilMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PerfilMedico() {
		setTitle("Perfil médico");
		setBounds(100, 100, 806, 575);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		TablaCitasDelMedico.setBounds(6, 189, 794, 313);
		contentPanel.add(TablaCitasDelMedico);
		
		JLabel lblFotoPerfilMedico = new JLabel("");
		lblFotoPerfilMedico.setBounds(380, 60, 61, 71);
		contentPanel.add(lblFotoPerfilMedico);
		lblFotoPerfilMedico.setIcon(new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/icons8-doctor-48.png"));
		
		JLabel lblNewLabel_1 = new JLabel("MIS CITAS");
		lblNewLabel_1.setBounds(371, 161, 81, 16);
		contentPanel.add(lblNewLabel_1);
		{
			JLabel lblNombreMedico = new JLabel("");
			lblNombreMedico.setBounds(314, 6, 154, 16);
			contentPanel.add(lblNombreMedico);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnConsultar = new JButton("CONSULTAR");
				btnConsultar.setActionCommand("OK");
				buttonPane.add(btnConsultar);
				getRootPane().setDefaultButton(btnConsultar);
			}
			{
				JButton btnSalir = new JButton("SALIR");
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
