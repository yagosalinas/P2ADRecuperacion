package modelo.facade;

import controlador.ModulosController;
import modelo.vo.ModuloVO;
import modelo.vo.ModulosVO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuloFacade {
    private ModuloFacade() {
    }

    public static void marshal(ModulosVO modulos) {
        try {
            JAXBContext ctx=JAXBContext.newInstance(ModulosVO.class);
            Marshaller ms=ctx.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            ms.marshal(modulos,new File("src/Modulo.xml"));
        } catch (JAXBException e) {
            Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,"Error inesperado durante el unmarshalling",e);
        }
    }

    public static ModulosVO unmarshal() {
        try {
            JAXBContext ctx=JAXBContext.newInstance(ModulosVO.class);
            Unmarshaller ums=ctx.createUnmarshaller();
            return (ModulosVO) ums.unmarshal(new File("src/Modulo.xml"));
        } catch (JAXBException e) {
            Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,"Error inesperado durante el marshalling",e);
        }
        return null;
    }

    public static void nuevoModulo(String nombre,int ci,String curso,int horas) {
        ModuloVO nuevoModulo=new ModuloVO(null,nombre,curso,ci,horas);
        ModulosVO modulos=unmarshal();
        if (modulos!=null) {
            int mayorId=0;
            if (!modulos.getModulos().isEmpty()) {
                for (ModuloVO m : modulos.getModulos()) {
                    if (m.getId()>mayorId) {
                        mayorId=m.getId();
                    }
                }
                nuevoModulo.setId(mayorId+1);
            } else {
                nuevoModulo.setId(0);
            }
            modulos.agregarModulo(nuevoModulo);
            marshal(modulos);
        }
    }

    public static List<String[]>listarModulo() {
        List<String[]> listarMod=new ArrayList<>();
        ModulosVO modulos=unmarshal();
        if (modulos==null) {
            Logger.getLogger(ModulosController.class.getName()).log(Level.INFO,"Error en el archivo 'Modulo.xml'");
            return new ArrayList<>();
        }
        if (!modulos.getModulos().isEmpty()) {
            String[] listM;
            for (ModuloVO m : modulos.getModulos()) {
                listM=new String[5];
                listM[0]=m.getNombre();
                listM[1]="" + m.getCiclo();
                listM[2]=m.getCurso();
                listM[3]="" + m.getHoras();
                listM[4]="" + m.getId();
                listarMod.add(listM);
            }
        }
        return listarMod;
    }

    public static void actualizarModulo(Integer id,String nombre,int ci,String curso,int horas) {
        ModulosVO modulos=unmarshal();
        if (modulos!=null) {
            for (ModuloVO modulo : modulos.getModulos()) {
                if (modulo.getId().equals(id)) {
                    modulo.setNombre(nombre);
                    modulo.setCiclo(ci);
                    modulo.setCurso(curso);
                    modulo.setHoras(horas);
                }
            }
            marshal(modulos);
        }
    }

    public static void eliminarModulo(Integer id) {
        ModulosVO modulos=unmarshal();
        if (modulos!=null) {
            for (int i=0;i<modulos.getModulos().size();i++) {
                ModuloVO modulo=modulos.getModulos().get(i);
                if (modulo.getId().equals(id)) {
                    modulos.getModulos().remove(modulo);
                    break;
                }
            }
            marshal(modulos);
        }
    }
}
