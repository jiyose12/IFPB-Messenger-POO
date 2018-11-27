 package modelo;


public class Mensagem {
	private int id;
	private Pessoa emitente;
	private Pessoa destinatario;
	private String texto;
	private String data;

	
	public Mensagem(int id, String texto) {
		super();
	
		this.texto = texto;
		this.id = id;
		
	}
	
	public Pessoa getEmitente() {
		return emitente;
	}

	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa p) {
		this.destinatario = p;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return "Emitente: "+emitente+ " Destinatario: " +destinatario+" ID:"+id+ " Data:" +data+" Mensagem:" +texto+ "\n";
	}


}
	


