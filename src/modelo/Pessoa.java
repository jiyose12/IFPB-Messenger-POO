package modelo;

import java.util.ArrayList;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

public abstract class Pessoa {
		private String email;
		private String senha;
		private String nome;
		
		private ArrayList<Mensagem> caixaEntrada = new ArrayList<Mensagem>();
		private ArrayList<Mensagem> caixaSaida = new ArrayList<Mensagem>();
		
		
		public Pessoa(String nome, String email,  String senha) {
			super();
			this.nome = nome;
			this.email = email;
			this.senha = senha;
		}
		
		public ArrayList<Mensagem> getCaixaEntrada() {
			return caixaEntrada;
		}

		public void setCaixaEntrada(ArrayList<Mensagem> caixaEntrada) {
			this.caixaEntrada = caixaEntrada;
		}

		public ArrayList<Mensagem> getCaixaSaida() {
			return caixaSaida;
		}

		public void setCaixaSaida(ArrayList<Mensagem> caixaSaida) {
			this.caixaSaida = caixaSaida;
		}

		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}		
		
		//---------------------------------------
		public void adicionarCxEntrada(Mensagem p){
			caixaEntrada.add(p);
		}
		public void adicionarCxSaida(Mensagem p){
			caixaSaida.add(p);
		}
		
		public Mensagem localizarNaCx(int id){
			for(Mensagem p : caixaEntrada){
				if(p.getId()==id)
					return p;
			}
			for(Mensagem p : caixaSaida){
				if(p.getId()==id)
					return p;
			}
			return null;
		}
		
		
		@Override
		public String toString() {
			return "Email = "+email+ " Nome = "+nome+ " \n";
		}
		
	
}
