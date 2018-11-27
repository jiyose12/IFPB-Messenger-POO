package modelo;

import java.util.TreeMap;

public class Aluno extends Pessoa{
	
	//TreeMap<String,Pessoa> cursos =  new TreeMap<String,Pessoa>();
	private String curso;
	
	public Aluno(String nome, String email,  String senha,String curso) {
		super(nome,email,senha);
		this.curso = curso;
		}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
}
