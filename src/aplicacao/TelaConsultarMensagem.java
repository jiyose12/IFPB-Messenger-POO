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
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class TelaConsultarMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarMensagem frame = new TelaConsultarMensagem();
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
	public TelaConsultarMensagem() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(129, 11, 281, 20);
		contentPane.add(textField);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(30, 77, 380, 160);
		contentPane.add(textArea);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String palavra = textField.getText();
					TreeMap<String, String> lista1 = Fachada.consultarMensagens(palavra);
					String saida = "";
					
					System.out.println(lista1);
					
					if (lista1 == null)
						//textArea.setText( "palavra nao encontrada\n");
						JOptionPane.showMessageDialog(null,"palavra não encontrada");
					else 	
						for(Map.Entry<String,String> m : lista1.entrySet()) {
							  String key = m.getKey();
							  String value = m.getValue();
							  
							  saida += "ID: "+ key + " - "+ value + "\n";
						}
					textArea.setText(saida);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsultar.setBounds(307, 42, 103, 23);
		contentPane.add(btnConsultar);
		
		JLabel lblConsultarMensagem = new JLabel();
		lblConsultarMensagem.setText("Consultar Mensagem");
		lblConsultarMensagem.setBounds(13, 14, 106, 14);
		contentPane.add(lblConsultarMensagem);
		
		
	}
}
