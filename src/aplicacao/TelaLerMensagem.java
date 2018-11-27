package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLerMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLerMensagem frame = new TelaLerMensagem();
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
	public TelaLerMensagem() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(120, 11, 281, 20);
		contentPane.add(textField);
		
		JLabel lblIdDaMensagem = new JLabel();
		lblIdDaMensagem.setText("ID da Mensagem");
		lblIdDaMensagem.setBounds(24, 14, 106, 14);
		contentPane.add(lblIdDaMensagem);
		
		JLabel label = new JLabel("");
		label.setBounds(24, 75, 377, 160);
		contentPane.add(label);
		
		JButton btnLerMensagem = new JButton("Ler Mensagem");
		btnLerMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(textField.getText());
					Mensagem m = Fachada.obterMensagem(id);
					label.setText(m.getTexto());
					
				} catch (Exception e) {
					label.setText(e.getMessage());
				}
			}
		});
		btnLerMensagem.setBounds(298, 42, 103, 23);
		contentPane.add(btnLerMensagem);
		
	}
}
