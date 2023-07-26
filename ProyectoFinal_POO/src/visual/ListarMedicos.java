package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ListarMedicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable TablaMedicos = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarMedicos dialog = new ListarMedicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarMedicos() {
		setBackground(new Color(112, 128, 144));
		setTitle("Listar medicos");
		setBounds(100, 100, 964, 722);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 952, 643);
		contentPanel.add(panel);
		panel.setLayout(null);
		TablaMedicos.setBounds(6, 6, 940, 631);
		panel.add(TablaMedicos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("ELIMINAR");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("MODIFICAR");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
