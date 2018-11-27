package fachada;

import java.util.ArrayList;

import modelo.Administrador;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Mensagem;
import modelo.Pessoa;
import repositorio.Messenger;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO - Programação Orientada a Objetos
 * Prof. Fausto Ayres
 *
 */
public class Fachada {
	private static Messenger repositorio = new Messenger();
	private static int idmens=0;	//autoincremento
	private static Pessoa logada=null;
	//...
	public static void login(String email, String senha) throws Exception{		
		if(logada!=null)
			throw new Exception ("ja existe uma pessoa logada");
		
		Pessoa p = repositorio.autenticar(email,senha);
		if(p==null)
			throw new Exception ("login nao foi possivel por email ou senha invalida: "+email);
		else
			logada = p;
	}

	public static void logoff() throws Exception {	
		if(logada==null)
			throw new Exception ("nao existe uma pessoa logada");
		logada = null;
	}
	/*Pega a pessoa Logada*/
	public static Pessoa getLogada(){
		return logada;
	}
	/*Cadastrar AbstractPessoa*/
	public static Pessoa cadastrarPessoa(String nome, String email, String senha, 
			String curso, String departamento, String aux) throws Exception{
		
		Pessoa p = repositorio.localizar(email);
		if( p != null ){
			throw new Exception("Email informado ja cadastrado");
		}
		if(aux.equalsIgnoreCase("aluno")){
			p = new Aluno(nome, email, senha, curso);
			repositorio.adicionar(curso, (Aluno) p);
			return p;
		}
		else if(aux.equalsIgnoreCase("funcionario")){
			p = new Funcionario(nome, email, senha, departamento);
			repositorio.adicionar(departamento, (Funcionario) p);
			return p;
		}
		else if(aux.equalsIgnoreCase("administrador")){
			p = new Administrador(nome, email, senha);
			repositorio.adicionar( (Administrador) p);
			return p;
		}
		else{
			throw new Exception("Não foi possivel realizar o cadastro - identificador não válido");
		}
	}
	// listar pessoas novo
	public static ArrayList<String> listarPessoas() throws Exception{
		if(logada == null){
			throw new Exception("Operação não autorizada. Para listar as pessoas é preciso estar logado");
		}
		TreeMap<String, Pessoa> abstractpessoas = repositorio.getPessoa();
		System.out.println(abstractpessoas);
		if(abstractpessoas==null){
			throw new Exception("nao há ninguem cadastrado");
		}
		//criação de um arraylist para dicionar os emails e tipos das pessoas do repositorio
		ArrayList<String> aux = new ArrayList<String>();
		String email = "";
		String tipo = "";
		String texto = "";
		for(Pessoa p : abstractpessoas.values()) {
			  //String key = p.getKey();
//			  AbstractPessoa value = p.getValue();
			  email = p.getEmail();
			  //concatena o email com o tipo
			  texto +=email+ " - "+p.getClass().getSimpleName() +"\n";
			  //adiciona o texto concatenado à lista aux
			  aux.add(texto);
		}
		//ordena a lista
		Collections.sort(aux);
		
		return aux;
	}
	/*Listar Pessoas*/
	/*public static ArrayList<Pessoa>  listarPessoas(String nome){		
		ArrayList<Pessoa> aux = new ArrayList<Pessoa>();
		
		if (nome==""){
		for(Pessoa p : repositorio.getPessoa())
				aux.add(p);
		}else{
		for(Pessoa p : repositorio.getPessoa())
			if(p.getNome().contains(nome))
				aux.add(p);
		}
		return aux;
	}
	/*Cadastrar Mensagem*/
	public static Mensagem enviarMensagem(String destinatario, String texto) 
			throws  Exception{
		idmens++;
		Pessoa p = repositorio.localizar(destinatario);
		if (texto.equals(""))
			throw new Exception("Não há texto escrito");
		if (p==null)
			throw new Exception("Destinatario nao localizado:" + destinatario);
		if (logada ==null)
			throw new Exception("Usuário precisa estar logado");

		Mensagem m = new Mensagem(idmens, texto);
	
		m.setDestinatario(p);
		
		m.setEmitente(Fachada.getLogada());
		
		Calendar c = Calendar.getInstance();
		String data = c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
		m.setData(data);
		
		p.adicionarCxEntrada(m);
		getLogada().adicionarCxSaida(m);
		
		repositorio.adicionar(m);
		
		return m;
	}
	
	/*Listar Caixa de Entrada*/
	public static ArrayList<Mensagem> listarCxEntrada() throws Exception{		
		Pessoa e = getLogada();
		if (logada ==null)
			throw new Exception("Usuário precisa estar logado");		
		return e.getCaixaEntrada();
	}
	
	/*Listar Caixa de Saida*/
	public static ArrayList<Mensagem>  listarCxSaida() throws Exception{		
		Pessoa e = getLogada();
		if (logada ==null)
			throw new Exception("Usuário precisa estar logado");		
		return e.getCaixaSaida();
	}
	//***************************************************************************
	// MODIFICACAO PARTE 2
	//***************************************************************************
	
	/*Listar todas as msg (ADM)*/
	public static ArrayList<Mensagem>  listarMensagens() throws Exception{	
		
		if (logada.getClass().getName() !="modelo.Administrador")
			throw new Exception("Usuário precisa estar logado como adm");		
		return repositorio.getMensagem();
	}
	
	/*Ler Mensagem*/ //listar pela cx de entrada e saida
	public static Mensagem obterMensagem(int id)  throws Exception{		
		Mensagem m = null;
		if (logada ==null)
			throw new Exception("Usuário precisa estar logado");
		
		else if(!(logada instanceof Administrador))
			m = logada.localizarNaCx(id);
		else
			m = repositorio.localizar(id);
			
		if (m==null)
			throw new Exception("Mensagem inexistente");
			return m;		
	}
	//Contultar curso -- retorna uma lista de nomes
	public static ArrayList<String> consultarCursoDepartamento(String cursodepartamento)  throws Exception{		
		ArrayList<String> nomes = new ArrayList<String>();
		if (logada ==null)
			throw new Exception("Usuário precisa estar logado");
		
		ArrayList<Pessoa> m = repositorio.buscarpessoasKeys(cursodepartamento);
			if (m.isEmpty())
				throw new Exception("curso nao existe");
			
			for (Pessoa b: m) {
				nomes.add(b.getNome());
			}
			return nomes;		
	}
	//consultar msg por palavra
	public static TreeMap<String, String> consultarMensagens(String palavra) throws Exception{
		if( logada == null ){
			throw new Exception("Operação não autorizada - é preciso que você seja um administrador");
		}
		
		if(repositorio.getMensagem().isEmpty()) 
			return null;
		
		TreeMap<String, String> aux = new TreeMap<String, String>();
		int cont = 0;
		
		if( logada instanceof Administrador ){
			for(Mensagem m: repositorio.getMensagem()){
				if(m.getTexto().contains(palavra)){
					aux.put(String.valueOf(m.getId()),m.getTexto());
					cont++;
				}
			}
			if (cont > 0) {
				return aux;
			}
			else {
				return null;	
			}
		}
		else{
			throw new Exception("Operação não autorizada - é preciso que você seja um administrador");
		}
	}
		//***************************************************************************
		// ESTATISTICAS
		//***************************************************************************
		//As pessoas (email) que não enviaram mensagens
	public static ArrayList<String> pessoasQueNaoEnviaram()  throws Exception{		
		ArrayList<String> nomes = new ArrayList<String>();
		
		if (!(logada instanceof Administrador))
			throw new Exception("Usuário precisa estar logado como Administrador");
			
			for (Pessoa p : repositorio.getPessoa().values()) {
				if (p.getCaixaSaida().isEmpty()) {
					nomes.add(p.getNome());
				}
			}
			return nomes;
	}
	//As mensagens (header) com emitente igual ao destinatário
	public static ArrayList<Pessoa> emitenteIgualDestinatario()  throws Exception{		
		ArrayList<Pessoa> saida = new ArrayList<Pessoa>();
		
		if (!(logada instanceof Administrador))
			throw new Exception("Usuário precisa estar logado como Administrador");
			
			for (Mensagem m : repositorio.getMensagem()) {
				if (m.getEmitente() == m.getDestinatario()) {
					saida.add(m.getEmitente());
				}
			}
			return saida;
	}
	//Total de alunos de cada curso
	public static HashMap<String,String> totalAlunosPorCurso()  throws Exception{		
		HashMap<String, String> aux = new HashMap<String, String>();
		int r=0,t=0;
		
		if (!(logada instanceof Administrador))
			throw new Exception("Usuário precisa estar logado como Administrador");
			//percorre o tree ap de pessoas
				for(Map.Entry<String,Pessoa> m : repositorio.getPessoa().entrySet()) {
					  String key = m.getKey();
					  Pessoa value = m.getValue();

				if (value instanceof Aluno) {
					//conta as ocorrencias dos cursos
					if (key.equalsIgnoreCase("redes")) {
						r++;
					}
					if (key.equalsIgnoreCase("tsi")) {
						t++;
					}
				}
			}
				//preenche o hashmap auxiliar
				aux.put("Redes", String.valueOf(r));
				aux.put("Tsi", String.valueOf(t));
				
			return aux;
	}
	/*Apagar Mensagem*/
/*	public static void apagarMensagem(int id) throws Exception {
		Mensagem m = pessoa.localizar(id);
		if (m==null)
			throw new Exception("Mensagem inexistente");
		
		pessoa.remover(m);
		//remover da cxEntrada
		
		}
	}*/

}
