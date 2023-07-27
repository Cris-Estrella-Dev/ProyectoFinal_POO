package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consultas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JTextField txtAlergicoA;
	private JTextField txtAntecedentesPatologicos;
	private JTextField txtSufreDeAlgo;
	private JTextField txtOperadoDe;
	private JTextField txtHabitosToxicos;
	private JTextField txtMotivoCita;
	private JTextField txtDerivarEspecialista;
	private ListarVacunas listarVacunas;
	private ListarEnfermedades listarEnfermedades;
	private HistorialPaciente historialPaciente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consultas dialog = new Consultas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consultas() {
		setTitle("Consultas");
		setBounds(100, 100, 1247, 617);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("NOMBRE:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(37, 48, 57, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO:");
			lblTelefono.setForeground(new Color(255, 255, 255));
			lblTelefono.setBounds(290, 48, 78, 16);
			contentPanel.add(lblTelefono);
		}
		{
			JLabel lblDireccion = new JLabel("DIRECCION:");
			lblDireccion.setForeground(new Color(255, 255, 255));
			lblDireccion.setBounds(540, 48, 83, 16);
			contentPanel.add(lblDireccion);
		}
		{
			JLabel lblFechaDeLa = new JLabel("FECHA DE LA CITA:");
			lblFechaDeLa.setForeground(new Color(255, 255, 255));
			lblFechaDeLa.setBounds(874, 48, 132, 16);
			contentPanel.add(lblFechaDeLa);
		}
		{
			JLabel lblNombrePaciente = new JLabel("");
			lblNombrePaciente.setBounds(119, 48, 159, 16);
			contentPanel.add(lblNombrePaciente);
		}
		{
			JLabel lblTelefonoPaciente = new JLabel("");
			lblTelefonoPaciente.setBounds(369, 48, 159, 16);
			contentPanel.add(lblTelefonoPaciente);
		}
		{
			JLabel lblDireccionPaciente = new JLabel("");
			lblDireccionPaciente.setBounds(635, 21, 218, 86);
			contentPanel.add(lblDireccionPaciente);
		}
		{
			JLabel lblFechaCitaPaciente = new JLabel("");
			lblFechaCitaPaciente.setBounds(1000, 48, 182, 16);
			contentPanel.add(lblFechaCitaPaciente);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("EDAD:");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(37, 159, 46, 16);
			contentPanel.add(lblNewLabel_1);
		}
		
		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setBounds(84, 154, 46, 26);
		contentPanel.add(txtEdadPaciente);
		txtEdadPaciente.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SEXO:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(648, 244, 40, 16);
		contentPanel.add(lblNewLabel_2);
		
		JRadioButton rdbtnSexoMasculino = new JRadioButton("M");
		rdbtnSexoMasculino.setForeground(new Color(255, 255, 255));
		rdbtnSexoMasculino.setBounds(700, 240, 46, 23);
		contentPanel.add(rdbtnSexoMasculino);
		
		JRadioButton rdbtnSexoFemenino = new JRadioButton("F");
		rdbtnSexoFemenino.setForeground(new Color(255, 255, 255));
		rdbtnSexoFemenino.setBounds(758, 240, 46, 23);
		contentPanel.add(rdbtnSexoFemenino);
		
		JLabel lblNewLabel_3 = new JLabel("ALERGICO A:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(648, 159, 85, 16);
		contentPanel.add(lblNewLabel_3);
		
		txtAlergicoA = new JTextField();
		txtAlergicoA.setBounds(865, 154, 212, 26);
		contentPanel.add(txtAlergicoA);
		txtAlergicoA.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ANTECEDENTES PATOLOGICOS:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(648, 197, 205, 36);
		contentPanel.add(lblNewLabel_4);
		
		txtAntecedentesPatologicos = new JTextField();
		txtAntecedentesPatologicos.setBounds(864, 202, 192, 26);
		contentPanel.add(txtAntecedentesPatologicos);
		txtAntecedentesPatologicos.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("SUFRE DE ALGO:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(37, 207, 114, 16);
		contentPanel.add(lblNewLabel_5);
		
		txtSufreDeAlgo = new JTextField();
		txtSufreDeAlgo.setBounds(145, 197, 195, 36);
		contentPanel.add(txtSufreDeAlgo);
		txtSufreDeAlgo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("OPERADO DE:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(37, 264, 100, 16);
		contentPanel.add(lblNewLabel_6);
		
		txtOperadoDe = new JTextField();
		txtOperadoDe.setBounds(133, 259, 208, 26);
		contentPanel.add(txtOperadoDe);
		txtOperadoDe.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("HABITOS TOXICOS:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(37, 324, 126, 16);
		contentPanel.add(lblNewLabel_7);
		
		txtHabitosToxicos = new JTextField();
		txtHabitosToxicos.setBounds(162, 319, 248, 26);
		contentPanel.add(txtHabitosToxicos);
		txtHabitosToxicos.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("MOTIVO DE LA CITA:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(648, 295, 138, 16);
		contentPanel.add(lblNewLabel_8);
		
		txtMotivoCita = new JTextField();
		txtMotivoCita.setBounds(814, 264, 271, 170);
		contentPanel.add(txtMotivoCita);
		txtMotivoCita.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("VACUNADO DE:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setBounds(37, 381, 108, 16);
		contentPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("ENFERMO DE:");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setBounds(37, 424, 91, 16);
		contentPanel.add(lblNewLabel_10);
		
		JButton btnListaVacunas = new JButton("VER VACUNAS");
		btnListaVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarVacunas = new ListarVacunas();
				listarVacunas.setModal(true);
				listarVacunas.setVisible(true);
			}
		});
		btnListaVacunas.setBounds(145, 376, 117, 29);
		contentPanel.add(btnListaVacunas);
		
		JButton btnVerListaEnfermedades = new JButton("VER ENFERMEDADES");
		btnVerListaEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarEnfermedades = new ListarEnfermedades();
				listarEnfermedades.setModal(true);
				listarEnfermedades.setVisible(true);
			}
		});
		btnVerListaEnfermedades.setBounds(119, 419, 159, 29);
		contentPanel.add(btnVerListaEnfermedades);
		
		JLabel lblNewLabel_11 = new JLabel("DERIVAR A UN ESPECIALISTA:");
		lblNewLabel_11.setForeground(new Color(255, 255, 255));
		lblNewLabel_11.setBounds(37, 470, 192, 16);
		contentPanel.add(lblNewLabel_11);
		
		txtDerivarEspecialista = new JTextField();
		txtDerivarEspecialista.setBounds(228, 465, 182, 26);
		contentPanel.add(txtDerivarEspecialista);
		txtDerivarEspecialista.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("VER HISTORIAL MEDICO DEL PACIENTE");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						historialPaciente = new HistorialPaciente();
						historialPaciente.setModal(true);
						historialPaciente.setVisible(true);
					}
				});
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand("Cancel");
			}
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
