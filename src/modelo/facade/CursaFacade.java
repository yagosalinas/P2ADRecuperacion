package modelo.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import controlador.ModulosController;
import modelo.vo.AlumnoVO;
import modelo.vo.CursaVO;
import modelo.vo.CursanVO;
import modelo.vo.ModuloVO;

public class CursaFacade {
	
	private static ArrayList<CursaVO> listaCursa;
	
	
	public CursaFacade() {
		
	}
	
	public static void nuevoCursa() {
		
		try {
			listaCursa=new ArrayList<CursaVO>();
			CursaVO cur=new CursaVO();
			cur.setAnho("");
			cur.setNota(0);
			
			listaCursa.add(cur);
			
			CursanVO cursan=new CursanVO();
			cursan.setCursan(listaCursa);
			
			JAXBContext ctx=JAXBContext.newInstance(CursaVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(cursan, new File("src\\Cursa.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
	
	public static void listarCursa() {
		
		try {
			JAXBContext ctx=JAXBContext.newInstance(CursaVO.class);
			Unmarshaller ums=ctx.createUnmarshaller();
			CursanVO cursan=(CursanVO)ums.unmarshal(new File("src\\Cursa.xml"));
					
			for(CursaVO c : cursan.getCursan()) {
				System.out.println(c.getAnho()+"\n"+c.getNota());
			}
			
		} catch(JAXBException ex2) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex2);
		}	
	}
	
	public static void actualizarCursa(int id, String dni) {
		
		try {
			ModuloVO mod=new ModuloVO();
			AlumnoVO al=new AlumnoVO();
			String dniSeleccionado=dni;
			int idSeleccionado=id;
			CursaVO cur=new CursaVO();
			for(int i=0;i<listaCursa.size();i++) {
				if(al.getDni().equals(dniSeleccionado) && mod.getId()==idSeleccionado) {
					listaCursa.set(i,cur);
				}
			}
			
			CursanVO cursan=new CursanVO();
			cursan.setCursan(listaCursa);
			
			JAXBContext ctx=JAXBContext.newInstance(CursaVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(cursan, new File("src\\Cursa.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
	
	public static void eliminarCursa(int id, String dni) {
		
		try {
			ModuloVO mod=new ModuloVO();
			AlumnoVO al=new AlumnoVO();
			String dniSeleccionado=dni;
			int idSeleccionado=id;
			for(int i=0;i<listaCursa.size();i++) {
				if(al.getDni().equals(dniSeleccionado) && mod.getId()==idSeleccionado) {
					listaCursa.remove(i);
				}
			}
			
			CursanVO cursan=new CursanVO();
			cursan.setCursan(listaCursa);
			
			JAXBContext ctx=JAXBContext.newInstance(CursaVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(cursan, new File("src\\Cursa.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
}
