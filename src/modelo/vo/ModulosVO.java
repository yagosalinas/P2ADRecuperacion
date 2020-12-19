package modelo.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name="modulos")
public class ModulosVO {
    private List<ModuloVO> modulos = new ArrayList<>();

    public List<ModuloVO> getModulos() {
        return modulos;
    }

    @XmlElement(name="modulo")
    public void setModulos(List<ModuloVO> modulos) {
        this.modulos = modulos;
    }

    public void agregarModulo(ModuloVO modulo) {
    	modulos.add(modulo);
	}
}
