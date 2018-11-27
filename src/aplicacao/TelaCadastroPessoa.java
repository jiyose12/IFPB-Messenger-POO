package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pessoa;
import modelo.Mensagem;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nome;
	private JTextField textField_email;
	private JTextField textField_senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa frame = new TelaCadastroPessoa();
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
	public TelaCadastroPessoa() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome");
		label.setBounds(10, 31, 46, 14);
		contentPane.add(label);
		
		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		textField_nome.setBounds(72, 28, 86, 20);
		contentPane.add(textField_nome);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(72, 66, 86, 20);
		contentPane.add(textField_email);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 69, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblmsg = new JLabel("");
		lblmsg.setBounds(259, 31, 122, 14);
		contentPane.add(lblmsg);
		
		JButton button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String nome = textField_nome.getText();
					String email = textField_email.getText();
					String senha = textField_senha.getText();
					Pessoa p = Fachada.cadastrarPessoa(nome, email, senha, senha, senha, senha);
					lblmsg.setText("cadastrado "+p.getNome());
					
					textField_nome.setText("");
					textField_email.setText("");
					textField_senha.setText("");
					textField_nome.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		button.setBounds(109, 155, 115, 23);
		contentPane.add(button);
		
		textField_senha = new JTextField();
		textField_senha.setColumns(10);
		textField_senha.setBounds(72, 109, 86, 20);
		contentPane.add(textField_senha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 112, 46, 14);
		contentPane.add(lblSenha);
		
	}
}
