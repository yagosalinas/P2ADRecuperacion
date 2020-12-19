package controlador;

import java.util.List;
import modelo.facade.ModuloFacade;
import vista.ModulosUI;

public class ModulosController extends ModulosUI {
	


	@Override
	protected void agregarModulo(String nom,int ci,String cur,int hrs) { 
		
		String nombre=nom;
		int ciclo=ci;
		String curso=cur;
		int horas=hrs;		
		
		ModuloFacade.nuevoModulo(nombre,ciclo,curso,horas);
	}

	@Override
	protected void editarModulo(Integer idModulo,String nom, int ci, String cur, int hrs) {
		
		Integer id=idModulo;
		String nombre=nom;
		int ciclo=ci;
		String curso=cur;
		int horas=hrs;
		  
		ModuloFacade.actualizarModulo(id,nombre,ciclo,curso,horas);
	}

	@Override
	protected void eliminarModulo(Integer idModulo) {
		
		Integer id=idModulo;
		
		ModuloFacade.eliminarModulo(id);
	}

	@Override
	protected List<String[]> transformarListaVO() {
		
		return ModuloFacade.listarModulo();
	}
}
