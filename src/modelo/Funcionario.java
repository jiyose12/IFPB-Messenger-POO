package modelo;

import java.util.TreeMap;

public class Funcionario extends Pessoa{
	private String departamento;

	//TreeMap<String,Pessoa> departamentos =  new TreeMap<String,Pessoa>();
	
	public Funcionario(String nome, String email,  String senha,String departamento) {
		super(nome,email,senha);
		this.departamento = departamento;
		}
}
