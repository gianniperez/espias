package Espias;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class Interfaz {

	private JFrame frame;
	private JComboBox comboEspias1;
	private JComboBox comboEspias2;
	private JComboBox probabilidades;
	private Espias espias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		espias = new Espias();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 250, 633, 329);
		frame.setTitle("Temible operario del recontraespionaje");
		frame.getContentPane().setBackground(Color.GRAY);;
		frame.getContentPane().setLayout(null);

		comboEspias1 = new JComboBox();
		comboEspias1.setBounds(51, 117, 122, 31);
		setComboEspias(espias.getEspias(), comboEspias1);
		comboEspias1.setVisible(true);
		frame.getContentPane().add(comboEspias1);

		probabilidades = new JComboBox();
		probabilidades.setBounds(254, 117, 90, 31);
		setComboProbabilidades(probabilidades);
		probabilidades.setVisible(true);
		frame.getContentPane().add(probabilidades);

		comboEspias2 = new JComboBox();
		comboEspias2.setBounds(414, 117, 122, 31);
		setComboEspias(espias.getEspias(), comboEspias2);
		comboEspias2.setVisible(true);
		frame.getContentPane().add(comboEspias2);

		JLabel lblAgregarPosiblesEncuentros = new JLabel("Agregar posibles encuentros");
		lblAgregarPosiblesEncuentros.setBounds(215, 10, 324, 31);
		frame.getContentPane().add(lblAgregarPosiblesEncuentros);

		JLabel lblEspia = new JLabel("Espia");
		lblEspia.setBounds(86, 72, 46, 31);
		frame.getContentPane().add(lblEspia);

		JLabel lblProbabilidad = new JLabel("Probabilidad de intercepci�n");
		lblProbabilidad.setBounds(225, 55, 210, 65);
		frame.getContentPane().add(lblProbabilidad);

		JLabel lblEspia_1 = new JLabel("Espia");
		lblEspia_1.setBounds(445, 67, 46, 40);
		frame.getContentPane().add(lblEspia_1);
		
		JLabel lblMensajeError = new JLabel("");
		lblMensajeError.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeError.setForeground(new Color(128, 0, 0));
		lblMensajeError.setBounds(51, 161, 488, 31);
		frame.getContentPane().add(lblMensajeError);
		
		JTextArea lblResultados = new JTextArea("");
		lblResultados.setEditable(false);
		lblResultados.setOpaque(false);
		lblResultados.setBounds(51, 36, 488, 155);
		frame.getContentPane().add(lblResultados);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int espiaSeleccionado1 = comboEspias1.getSelectedIndex();
				int espiaSeleccionado2 = comboEspias2.getSelectedIndex();

				if(espiaSeleccionado1 == espiaSeleccionado2)
					lblMensajeError.setText("No puede haber un encuentro entre un mismo esp�a!");
				
				if (espias.getRedEspias().existeArista(espiaSeleccionado1, espiaSeleccionado2))
					lblMensajeError.setText("Ese posible encuentro ya fue agregado!");
				
				if(espiaSeleccionado1 != espiaSeleccionado2 && !espias.getRedEspias().existeArista(espiaSeleccionado1, espiaSeleccionado2)) {
					espias.agregarPosibleEncuentro(comboEspias1.getSelectedIndex(), comboEspias2.getSelectedIndex(), (double) probabilidades.getSelectedItem());
					lblMensajeError.setText("");
				}
			}
		});
		btnAgregar.setBounds(254, 202, 90, 21);
		frame.getContentPane().add(btnAgregar);

		JButton btnResultado = new JButton("Mostrar resultado");
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboEspias1.setVisible(false);
				probabilidades.setVisible(false);
				comboEspias2.setVisible(false);
				lblAgregarPosiblesEncuentros.setVisible(false);
				lblEspia.setVisible(false);
				lblProbabilidad.setVisible(false);
				lblEspia_1.setVisible(false);
				btnAgregar.setVisible(false);
				lblResultados.setText(cargarResultados());
				lblResultados.setVisible(true);
				btnResultado.setEnabled(false);
			}
		});
		btnResultado.setBounds(225, 258, 145, 21);
		frame.getContentPane().add(btnResultado);

		JSeparator separator = new JSeparator();
		separator.setBounds(-48, 248, 702, 12);
		frame.getContentPane().add(separator);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	private String cargarResultados() {
		
		StringBuilder s = new StringBuilder();
		
		HashMap<Tupla<Integer, Integer>, Double> resultados = espias.redEspiasConMenorRiesgo();
		System.out.println(resultados);
		
		for (Entry<Tupla<Integer, Integer>, Double> r: resultados.entrySet()) {
			String espia1 = comboEspias1.getItemAt(r.getKey().getX()).toString();
			String espia2 = comboEspias1.getItemAt(r.getKey().getY()).toString();
			String probabilidad = "" + r.getValue();
			s.append(espia1 + " debe encontrarse con " + espia2 + ". Probabilidad de intercepci�n: " + probabilidad).append(" \n");
		}
		System.out.println(s.toString());
		return s.toString();
	}
	
	private void setComboProbabilidades(JComboBox probabilidades) {
		for (int i = 0; i <= 10; i++)
			probabilidades.addItem((double) i / 10);
	}

	private void setComboEspias(Conjunto<String> espias, JComboBox combo) {
		int i = 0;
		String e;
		while (i != espias.cantElementos()) {
			e = espias.dameElemento();
			combo.addItem(e);
			i++;
		}
	}
}
