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

public class TelaConsultarCurso extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarCurso frame = new TelaConsultarCurso();
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
	public TelaConsultarCurso() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(30, 75, 380, 160);
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
		btnConsultar.setBounds(304, 42, 103, 23);
		contentPane.add(btnConsultar);
		
		textField = new JTextField();
		textField.setBounds(126, 11, 281, 20);
		contentPane.add(textField);
		
		JLabel lblConsultarCurso = new JLabel();
		lblConsultarCurso.setText("Consultar Curso");
		lblConsultarCurso.setBounds(30, 14, 106, 14);
		contentPane.add(lblConsultarCurso);
		
	}
}
