package modelo.vo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "curso", "ciclo", "horas"})
public class ModuloVO {

    private Integer id;
    private String nombre;
    private String curso;
    private int ciclo;
    private int horas;

    public ModuloVO() {

    }

    public ModuloVO(Integer id, String nombre, String curso, int ciclo, int horas) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
        this.horas = horas;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}


