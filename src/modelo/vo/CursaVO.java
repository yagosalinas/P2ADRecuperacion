package modelo.vo;

import javax.xml.bind.annotation.*;

@XmlType(propOrder= {"anho","nota"})
public class CursaVO {

	private String anho;
	private int nota;
	
	public CursaVO() {
		
	}
	
	public String getAnho() {
		return anho;
	}

	public void setAnho(String anho) {
		this.anho = anho;
	}


	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}

	