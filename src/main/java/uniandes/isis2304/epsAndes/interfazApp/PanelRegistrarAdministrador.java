package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdk.nashorn.internal.scripts.JO;

public class PanelRegistrarAdministrador extends JPanel {

	public PanelRegistrarAdministrador(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField nombre = new JTextField(15);
		JTextField email = new JTextField(15);
		JTextField cedula = new JTextField(15);
		
		setLayout(new GridLayout(5,1));
		add(new JLabel("Identificador: "));
		add(id);
		
		add(new JLabel("Email: "));
		add(email);
		
		add(new JLabel("Nombre: "));
		add(nombre);
		
		add(new JLabel("Cedula: "));
		add(cedula);
		
		
		add(new JLabel("Tipo Identificacion: "));
		JComboBox combo1 = new JComboBox();
		combo1.addItem("CC");
		combo1.addItem("CE");
		combo1.addItem("TI");
		combo1.addItem("RC");
		add(combo1);
		
		
	
		int result = JOptionPane.showConfirmDialog(null, this, "Registrar Administrador", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarAdministradorDatos(Long.parseLong(id.getText()), email.getText(), nombre.getText(), Long.parseLong(cedula.getText()), 4, combo1.getSelectedItem().toString());
		}
	}
}
