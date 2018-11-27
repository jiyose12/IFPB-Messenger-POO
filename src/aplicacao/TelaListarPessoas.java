package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pessoa;
import modelo.Mensagem;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaListarPessoas extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarPessoas frame = new TelaListarPessoas();
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
	public TelaListarPessoas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//String nome = textField.getText();
					ArrayList<String> lista1 = Fachada.listarPessoas();
					//nome = "Listagem de pessoas: \n";
					//if (lista1.isEmpty())
					//	nome += "não tem pessoa cadastrada\n";
					//else 	
						for(String p: lista1) 
							//nome +=  p + "\n"; 

					textArea.setText(p);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		button.setText("Buscar");
		button.setBounds(171, 32, 90, 31);
		contentPane.add(button);
		
		textArea = new JTextArea();
		textArea.setBounds(35, 90, 366, 144);
		contentPane.add(textArea);
		
		
		
	}
}
