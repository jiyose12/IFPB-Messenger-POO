package repositorio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import modelo.Pessoa;
import modelo.Administrador;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Mensagem;

public class Messenger {
	private TreeMap<String, Pessoa> pessoas = new TreeMap<String, Pessoa>();
	
	private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
	//adicionar
	public void adicionar(String curso, Aluno p){
		pessoas.put(curso, p);
	}
	public void adicionar(String departamento, Funcionario p){
		pessoas.put(departamento, p);
	}
	public void adicionar(Administrador p){
		pessoas.put("Administrador", p);
	}
	//remover
//	public void remover(AbstractPessoa p){
//		pessoas.remove(p);
//	}
	//login
	public Pessoa autenticar(String email, String senha){
		for(Pessoa p : pessoas.values()){
			if(p.getEmail().equals(email) && p.getSenha().equals(senha)) {
				return p;
		}	
	}
		return null;
	}
	//nome
	public Pessoa procurar(String nome){
		for(Pessoa p : pessoas.values()){
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}
	//email
	public Pessoa localizar(String email){
		//abstractpessoa
		for(Pessoa p : pessoas.values()){
			if(p.getEmail().equals(email))
				return p;
		}
		return null;
	}

	public void adicionar(Mensagem p){
		mensagens.add(p);
	}
	public void remover(Mensagem p){
		mensagens.remove(p);
	}

	public Mensagem localizar(int id){
		for(Mensagem p : mensagens){
			if(p.getId()==id)
				return p;
		}
		return null;
	}
	
	public TreeMap<String, Pessoa> getPessoa() {
		return pessoas;
	}

	public ArrayList<Mensagem> getMensagem() {
		return mensagens;
	}

	public ArrayList<Pessoa> buscarpessoasKeys(String cursodepartamento) {
		
		ArrayList<Pessoa> aux = new ArrayList<Pessoa>();
		for(Map.Entry<String,Pessoa> p : pessoas.entrySet()) {
			  String key = p.getKey();
			  Pessoa value = p.getValue();
			if(key.equalsIgnoreCase(cursodepartamento)) {	
				aux.add(value);
			}
			}
		return aux;
	}
}

