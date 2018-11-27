package aplicacao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;


public class TelaPrincipal {
	private JFrame frmPrincipal;
	private JMenu mnLog;
	private JMenuItem mntmLogoff;
	private JMenuItem mntmLogin;
	private JMenuItem mntmLer;
	private JMenu mnPessoa;
	private JMenuItem mntmCadastrarPessoa;
	private JMenuItem mntmListarPessoas;
	private JMenu mnMensagem;
	private JMenuItem mntmEnviarMensagem;
	private JMenuItem mntmListarCxEntrada;
	private JMenuItem mntmListarCxSaida;
	private JMenuItem mntmLerMensagem;
	private JMenu mnAdm;
	private JMenuItem mntmConsultarCurso;
	private JMenuItem mntmConsultarDepartamento;
	private JMenuItem mntmConsultarMensagens;
	private JMenuItem mntmListarMensagens;
	private JMenuItem mntmEstatisticas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame()	;

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Fachada.cadastrarPessoa("zeca","p0@", "000", "tsi", "","aluno");
					Fachada.cadastrarPessoa("joao","p1@", "111", "Redes", "","aluno");
					Fachada.cadastrarPessoa("jose","p2@", "222", "TSI", "","aluno");
					Fachada.cadastrarPessoa("josineide","p3@", "333", "Engenharia Eletrica", "","aluno");
					Fachada.cadastrarPessoa("paulo","p4@", "444", "", "Reitoria","funcionario");
					Fachada.cadastrarPessoa("priscila","p5@", "555", "", "Setor de Distribuicao","funcionario");
					Fachada.cadastrarPessoa("lara","p6@", "666", "", "reitoria","funcionario");
					Fachada.cadastrarPessoa("jiyose","admin@", "007", "", "DTI","administrador");
					
					Fachada.login("p1@", "111");
					
					Fachada.enviarMensagem("p2@", "ola");
					Fachada.enviarMensagem("p3@", "tudo");
					Fachada.logoff();
					
					Fachada.login("p2@", "222");
					
					Fachada.enviarMensagem("p1@", "bem contigo")	;
					Fachada.enviarMensagem("p2@", "eu vou");
					Fachada.enviarMensagem("p3@", "bem");
					Fachada.logoff();
					
					Fachada.login("p3@", "333");
					
					Fachada.enviarMensagem("p1@", "obrigado");
					Fachada.logoff();
					
					Fachada.login("admin@", "007");
					
//					System.out.println("Emitente\n");
//					System.out.println(Fachada.obterMensagem(1).getEmitente());
//					System.out.println("Destinatario\n");
//					System.out.println(Fachada.obterMensagem(1).getDestinatario());
					
					System.out.println(Fachada.getLogada().getCaixaEntrada());
					System.out.println("terminou entrada e comecou saida\n");
					System.out.println(Fachada.getLogada().getCaixaSaida());
					System.out.println(Fachada.getLogada().getClass().getName());
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmPrincipal, e);
				}

			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "até breve !");

			}
		});
		frmPrincipal.setTitle("Aplicacao");
		frmPrincipal.setBounds(100, 100, 436, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);


		mnLog = new JMenu("Logar");
		menuBar.add(mnLog);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin telalogin = new TelaLogin();
				telalogin.setVisible(true);
			}
		});
		mnLog.add(mntmLogin);

		mntmLogoff = new JMenuItem("Logoff");
		mntmLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Fachada.logoff();
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnLog.add(mntmLogoff);
		
		mnPessoa = new JMenu("Pessoa");
		menuBar.add(mnPessoa);
		
		mntmCadastrarPessoa = new JMenuItem("Cadastrar Pessoa");
		mntmCadastrarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroPessoa telacadastropessoa = new TelaCadastroPessoa();
				telacadastropessoa.setVisible(true);
			}
		});
		mnPessoa.add(mntmCadastrarPessoa);
		
		mntmListarPessoas = new JMenuItem("Listar Pessoas");
		mntmListarPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListarPessoas telalistarpessoas = new TelaListarPessoas();
				telalistarpessoas.setVisible(true);
			}
		});
		mnPessoa.add(mntmListarPessoas);
		
		mnMensagem = new JMenu("Mensagem");
		menuBar.add(mnMensagem);
		
		mntmEnviarMensagem = new JMenuItem("Enviar Mensagem");
		mntmEnviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaEnviarMensagem telaenviarmensagem = new TelaEnviarMensagem();
				telaenviarmensagem.setVisible(true);
			}
		});
		mnMensagem.add(mntmEnviarMensagem);
		
		mntmListarCxEntrada = new JMenuItem("Listar Cx Entrada");
		mntmListarCxEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarCxEntrada listarcxentrada = new ListarCxEntrada();
				listarcxentrada.setVisible(true);
			}
		});
		mnMensagem.add(mntmListarCxEntrada);
		
		mntmListarCxSaida = new JMenuItem("Listar Cx Saida");
		mntmListarCxSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarCxSaida listarcxsaida = new ListarCxSaida();
				listarcxsaida.setVisible(true);
			}
		});
		mnMensagem.add(mntmListarCxSaida);
		
		mntmLerMensagem = new JMenuItem("Ler Mensagem");
		mntmLerMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLerMensagem telalermensagem = new TelaLerMensagem();
				telalermensagem.setVisible(true);
			}
		});
		mnMensagem.add(mntmLerMensagem);
		
		mnAdm = new JMenu("Adm");
		menuBar.add(mnAdm);
		
		mntmConsultarCurso = new JMenuItem("Consultar Curso");
		mntmConsultarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConsultarCurso telaconsultarcurso = new TelaConsultarCurso();
				telaconsultarcurso.setVisible(true);
			}
		});
		mnAdm.add(mntmConsultarCurso);
		
		mntmConsultarDepartamento = new JMenuItem("Consultar Departamento");
		mntmConsultarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConsultarDepartamento telaconsultardepartamento = new TelaConsultarDepartamento();
				telaconsultardepartamento.setVisible(true);
			}
		});
		mnAdm.add(mntmConsultarDepartamento);
		
		mntmConsultarMensagens = new JMenuItem("Consultar Mensagens");
		mntmConsultarMensagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConsultarMensagem telaconsultarmensagem = new TelaConsultarMensagem();
				telaconsultarmensagem.setVisible(true);
			}
		});
		mnAdm.add(mntmConsultarMensagens);
		
		mntmListarMensagens = new JMenuItem("Listar Mensagens");
		mntmListarMensagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListarMensagens telalistarmensagens = new TelaListarMensagens();
				telalistarmensagens.setVisible(true);
			}
		});
		mnAdm.add(mntmListarMensagens);
		
		mntmEstatisticas = new JMenuItem("Estat\u00EDsticas");
		mntmEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaEstatisticas telaestatisticas = new TelaEstatisticas();
				telaestatisticas.setVisible(true);
			}
		});
		mnAdm.add(mntmEstatisticas);

	}
}
