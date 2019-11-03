package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarRol extends JPanel
{
	public PanelRegistrarRol(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField nombre = new JTextField(15);

		setLayout(new GridLayout(2,1));
		add(new JLabel("Identificador: "));
		add(id);
		
		add(new JLabel("Nombre: "));
		add(nombre);


		int result = JOptionPane.showConfirmDialog(null, this, "Registrar Rol", JOptionPane.OK_CANCEL_OPTION);

		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.adicionarRolesDeUsuario(Long.parseLong(id.getText()), nombre.getText());
		}
	}
}
