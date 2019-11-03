package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarMedico extends JPanel {

	public PanelRegistrarMedico(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField nombre = new JTextField(15);
		JTextField email = new JTextField(15);
		JTextField cedula = new JTextField(15);
		JTextField registro = new JTextField(15);
		
		
		setLayout(new GridLayout(7,1));
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
		
		add(new JLabel("Especialidad: "));
		JComboBox combo2 = new JComboBox();
		combo2.addItem("GERONTOLOGIA");
		combo2.addItem("ORTOPEDIA");
		combo2.addItem("INTERNA");
		combo2.addItem("CIRUGIA");
		combo2.addItem("NEUROLOGIA");
		combo2.addItem("PEDIATRIA");
		combo2.addItem("CARDIOLOGIA");
		combo2.addItem("ODONTOLOGIA");
		combo2.addItem("DERMATOLOGIA");
		add(combo2);
		
		add(new JLabel("Numero registro: "));
		add(registro);
		
		
	
		int result = JOptionPane.showConfirmDialog(null, this, "Registrar medico", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarMedicoDatos(Long.parseLong(id.getText()), email.getText(), nombre.getText(), Long.parseLong(cedula.getText()), 1, combo1.getSelectedItem().toString(), combo2.getSelectedItem().toString(),registro.getText() );
		}
	}
}

