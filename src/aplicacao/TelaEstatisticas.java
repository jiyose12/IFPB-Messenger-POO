package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;

import javax.swing.JButton;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class TelaEstatisticas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstatisticas frame = new TelaEstatisticas();
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
	public TelaEstatisticas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TextField textField_naoenviaramemail = new TextField();
		textField_naoenviaramemail.setBounds(10, 75, 167, 245);
		contentPane.add(textField_naoenviaramemail);
		
		TextField textField_emitenteigualdest = new TextField();
		textField_emitenteigualdest.setBounds(213, 75, 167, 245);
		contentPane.add(textField_emitenteigualdest);
		
		TextField textField_totalalunosporcurso = new TextField();
		textField_totalalunosporcurso.setBounds(407, 75, 167, 245);
		contentPane.add(textField_totalalunosporcurso);
		
		JLabel lblNewLabel = new JLabel("Nao Enviaram Mensagens");
		lblNewLabel.setBounds(23, 11, 141, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmitenteIgualDest = new JLabel("Mensagens com o Emitente Igual Destinatario");
		lblEmitenteIgualDest.setBounds(191, 0, 218, 69);
		contentPane.add(lblEmitenteIgualDest);
		
		JLabel lblTotalDeAlunos = new JLabel("Total de Alunos por Curso");
		lblTotalDeAlunos.setBounds(419, 11, 141, 45);
		contentPane.add(lblTotalDeAlunos);
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ArrayList<String> naoenviaram = Fachada.pessoasQueNaoEnviaram();
					ArrayList<Pessoa> emitenteigualdest = Fachada.emitenteIgualDestinatario();
					HashMap<String, String> qtpessoasporcurso = Fachada.totalAlunosPorCurso();
					
					String textoqt="";
					if (naoenviaram.isEmpty())
						textField_naoenviaramemail.setText("Nao há ocorrências");
					if (emitenteigualdest==null)
						textField_emitenteigualdest.setText("Nao há ocorrências");
					if (qtpessoasporcurso==null)
						textField_totalalunosporcurso.setText("Nao há ocorrências");
						//pessoas que nao enviaram email
						for(String s: naoenviaram) 
							textField_naoenviaramemail.setText(s);
						// msgs emitente igual dest
						for(Pessoa p: emitenteigualdest) {
							for(Mensagem m: p.getCaixaEntrada()) {
								
							textField_emitenteigualdest.setText(m.getTexto());
							}
						}
						//total de alunos por curso
						for(Map.Entry<String,String> qt : qtpessoasporcurso.entrySet()) {
							  String key = qt.getKey();
							  String value = qt.getValue();
							  
							  textoqt+=key + " - " + value + "\n";
							 
							}
						 textField_totalalunosporcurso.setText(textoqt);
						}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnNewButton.setBounds(240, 362, 124, 52);
		contentPane.add(btnNewButton);
		
		
	}
}
