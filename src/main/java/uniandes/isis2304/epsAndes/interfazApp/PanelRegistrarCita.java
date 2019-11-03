package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarCita extends JPanel
{
	public PanelRegistrarCita(InterfazEpsAndesApp interfaz)
	{
		JTextField id = new JTextField(15);
		JTextField idReceta = new JTextField(15);
		JTextField idUsuario = new JTextField(15);
		JTextField idRecepcionista = new JTextField(15);
		JTextField idServicio = new JTextField(15);
		JTextField estado = new JTextField(15);
		JTextField fecha = new JTextField(15);
		JTextField horario = new JTextField(15);
		
		
		
		
		setLayout(new GridLayout(8,1));
		add(new JLabel("Identificador: "));
		add(id);
		
		add(new JLabel("Identificador receta: "));
		add(idReceta);
		
		add(new JLabel("Identificador usuario: "));
		add(idUsuario);
		
		add(new JLabel("Identificador recepcionista: "));
		add(idRecepcionista);
		
		add(new JLabel("Identificador servicio: "));
		add(idServicio);
		
		add(new JLabel("Estado: "));
		add(estado);
		
		add(new JLabel("Fecha: "));
		add(fecha);
		
		add(new JLabel("Horario: "));
		add(horario);

	
		int result = JOptionPane.showConfirmDialog(null, this, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			interfaz.registrarCitaDatos(Long.parseLong(id.getText()), Long.parseLong(idReceta.getText()), Long.parseLong(idUsuario.getText()), Long.parseLong(idRecepcionista.getText()),
					idServicio.getText(), estado.getText(), fecha.getText(), Integer.parseInt(horario.getText()));
		}
	
	}
}
