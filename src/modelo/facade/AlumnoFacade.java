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
import modelo.vo.AlumnosVO;

public class AlumnoFacade {
	
	private static ArrayList<AlumnoVO> listaAlumnos;
	
	public AlumnoFacade() {
		
	}
	
	public static void añadirAlumno() {
		
		try {
			AlumnoVO alu=new AlumnoVO();
			alu.setDni("");
			alu.setNombre("");
			alu.setApellido1("");
			alu.setApellido2("");
			alu.setTelefono(000000000);
			
			listaAlumnos.add(alu);
			
			AlumnosVO alumnos=new AlumnosVO();
			alumnos.setAlumno(listaAlumnos);
			
			JAXBContext ctx=JAXBContext.newInstance(AlumnoVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(alumnos, new File("src\\Alumno.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
	
	public static void listarAlumno() {
		
		try {
			JAXBContext ctx=JAXBContext.newInstance(AlumnoVO.class);
			Unmarshaller ums=ctx.createUnmarshaller();
			AlumnosVO alumnos=(AlumnosVO)ums.unmarshal(new File("src\\Alumno.xml"));
					
			for(AlumnoVO al : alumnos.getAlumno()) {
				System.out.println(al.getDni()+"\n"+al.getNombre()+"\n"+al.getApellido1()+"\n"+
						al.getApellido2()+"\n"+al.getTelefono());
			}
			
		} catch(JAXBException ex2) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex2);
		}	
	}
	
	public static void actualizarAlumno(String dni) {
		
		try {
			String ElementoSeleccionado=dni;
			AlumnoVO al=new AlumnoVO();
			for(int i=0;i<listaAlumnos.size();i++) {
				if(listaAlumnos.get(i).getDni()==ElementoSeleccionado) {
					listaAlumnos.set(i,al);
				}
			}
			
			AlumnosVO alumnos=new AlumnosVO();
			alumnos.setAlumno(listaAlumnos);
			
			JAXBContext ctx=JAXBContext.newInstance(AlumnoVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(alumnos, new File("src\\Alumno.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
	
	public static void eliminarAlumno(String dni) {
		
		try {
			String ElementoSeleccionado=dni;
			for(int i=0;i<listaAlumnos.size();i++) {
				if(listaAlumnos.get(i).getDni()==ElementoSeleccionado) {
					listaAlumnos.remove(i);
				}
			}
			
			AlumnosVO alumnos=new AlumnosVO();
			alumnos.setAlumno(listaAlumnos);
			
			JAXBContext ctx=JAXBContext.newInstance(AlumnoVO.class);
			Marshaller ms=ctx.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(alumnos, new File("src\\Alumno.xml"));
			
		} catch(JAXBException ex) {
			Logger.getLogger(ModulosController.class.getName()).log(Level.SEVERE,null,ex);
		}	
	}
}

