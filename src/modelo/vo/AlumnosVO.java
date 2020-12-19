package modelo.vo;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class AlumnosVO {

private List<AlumnoVO> alumnos;
	
	public AlumnosVO() {
		
	}

	public List<AlumnoVO> getAlumno() {
		return alumnos;
	}

	@XmlElement(name="alumno")
	public void setAlumno(List<AlumnoVO> alumnos) {
		this.alumnos = alumnos;
	}
}
