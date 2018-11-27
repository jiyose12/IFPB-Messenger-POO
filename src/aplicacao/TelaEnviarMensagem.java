package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEnviarMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField textField_text;
	private JTextField textField_email;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEnviarMensagem frame = new TelaEnviarMensagem();
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
	public TelaEnviarMensagem() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_text = new JTextField();
		textField_text.setBounds(31, 56, 366, 135);
		contentPane.add(textField_text);
		
		JLabel label = new JLabel();
		label.setText("Destinatario");
		label.setBounds(51, 14, 58, 14);
		contentPane.add(label);
		
		textField_email = new JTextField();
		textField_email.setBounds(113, 11, 191, 20);
		contentPane.add(textField_email);
		
		label_1 = new JLabel("");
		label_1.setBounds(335, 14, 58, 31);
		contentPane.add(label_1);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String email = textField_email.getText();
					String texto = textField_text.getText();
					Mensagem m = Fachada.enviarMensagem(email, texto);
					label_1.setText("ID da mensagem "+m.getId());
					
					textField_text.setText("");
					textField_email.setText("");
				} catch (Exception e) {
					label_1.setText(e.getMessage());
				}
				
			}
		});
		btnEnviar.setBounds(308, 202, 89, 23);
		contentPane.add(btnEnviar);
		
	
		

	}

}
