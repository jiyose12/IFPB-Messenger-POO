package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class TelaConsultarDepartamento extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarDepartamento frame = new TelaConsultarDepartamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConsultarDepartamento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(32, 71, 380, 160);
		contentPane.add(textArea);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String nome = textField.getText();
					ArrayList<String> lista1 = Fachada.consultarCursoDepartamento(nome);
					System.out.println(lista1);
					nome = "Listagem de pessoas: \n";
					if (lista1.isEmpty())
						nome += "não tem pessoa cadastrada\n";
					else 	
						for(String p: lista1) 
							nome +=  p + "\n"; 

					textArea.setText(nome);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsultar.setBounds(307, 42, 103, 23);
		contentPane.add(btnConsultar);
		
		textField = new JTextField();
		textField.setBounds(129, 11, 281, 20);
		contentPane.add(textField);
		
		JLabel lblConsultarDepartamento = new JLabel();
		lblConsultarDepartamento.setText("Consultar Departamento");
		lblConsultarDepartamento.setBounds(10, 14, 118, 14);
		contentPane.add(lblConsultarDepartamento);
		
		
	}

}
