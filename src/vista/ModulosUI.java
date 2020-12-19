package vista;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JComboBox;

public abstract class ModulosUI {

	private JFrame frame;
	private JTextField nombreTextField;
	private JTextField cursoTextField;
	private JTextField horasTextField;
	private JTable table;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JButton btnAlumnos;
	private JButton btnEliminar;
	private JComboBox<String> cicloComboBox;
	private Integer id;
	private ArrayList<ComboxItem> ciclos;


	/**
	 * Create the application.
	 */
	public ModulosUI() {
		initialize();
	}

	/**
	 * Muestra el formulario
	 */
	public void show(){
		frame.setVisible(true);
	}

	/**
	 * Agregar un modulo en funcion de los valores de los campos:
	 * nombre, ciclo, curso y horas
	 * @param horas
	 * @param curso
	 * @param
	 * @param nombre
	 */
	protected abstract void agregarModulo(String nombre, int ciclo , String curso, int horas);

	/**
	 * Edita un modulo seleccionado en funcion de los valores de los campos:
	 * nombre, ciclo, curso y horas, id
	 * @param id
	 * @param horas
	 * @param nombre
	 * @param ciclo
	 * @param horas
	 */
	protected abstract void editarModulo(Integer id, String nombre, int ciclo , String curso, int horas);

	/**
	 * Elimina un modulo seleccionado
	 */
	protected abstract void eliminarModulo(Integer id);

	/**
	 * En este método deben implementarse las funcionalidades necesarias para transformar una lista de VO
	 * en una lista de arrays de String (String[]) donde cada elemento sea un array con {"Nombre","Ciclo","Curso","Horas","ID"}
	 * @return
	 */
	protected abstract  List<String[]> transformarListaVO();

	/**
	 * En este método deben recuperarse todos los ciclos que se deseen mostrar en el combo Ciclos y
	 * agregarlos empleando el método addCiclo(id,nombre)
	 */
	protected void cargarComboCiclos(){
		addCiclo(1,"ASIR");
		addCiclo(2,"DAM");
		addCiclo(3,"DAW");
		addCiclo(4,"SMR");
	}

	protected Integer getId(){
		return this.id;
	}

	protected String getNombre(){
		return this.nombreTextField.getText();
	}


	protected Integer getCicloId(){
		Integer toret = null;

		toret = getCicloId(cicloComboBox.getSelectedIndex());

		return toret;
	}

	protected String getCurso(){
		return this.cursoTextField.getText();
	}

	protected String getHoras(){
		return this.horasTextField.getText();
	}

	protected void addCiclo(int id, String nombre){
		if(this.ciclos==null){
			ciclos = new ArrayList<ComboxItem>();
		}
			ciclos.add(new ComboxItem(id,nombre));

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		id = null;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 676, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 31, 46, 14);
		frame.getContentPane().add(lblNombre);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(10, 47, 372, 20);
		frame.getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);

		JLabel lblCiclo = new JLabel("Ciclo");
		lblCiclo.setBounds(410, 31, 250, 14);
		frame.getContentPane().add(lblCiclo);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(10, 78, 46, 14);
		frame.getContentPane().add(lblCurso);

		//Combo ciclos
		cargarComboCiclos();
		cicloComboBox = new JComboBox();
		cicloComboBox.setBounds(410, 47, 250, 20);
		for(ComboxItem it: ciclos){
			cicloComboBox.addItem(it.getDescription());
		}
		frame.getContentPane().add(cicloComboBox);


		cursoTextField = new JTextField();
		cursoTextField.setBounds(10, 94, 86, 20);
		frame.getContentPane().add(cursoTextField);
		cursoTextField.setColumns(10);

		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setBounds(126, 78, 46, 14);
		frame.getContentPane().add(lblHoras);

		horasTextField = new JTextField();
		horasTextField.setColumns(10);
		horasTextField.setBounds(126, 94, 86, 20);
		frame.getContentPane().add(horasTextField);

		btnGuardar = new JButton("Añadir");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int horas=0;
				try {
					horas=Integer.parseInt(horasTextField.getText());
					if(horas==0) {
						horasTextField.setText(null);
					}
				} catch(NumberFormatException e){
					System.out.println("Error, el valor introducido no es un digito.");
				}
				
				if(id==null){
					try {
						agregarModulo(nombreTextField.getText(),cicloComboBox.getSelectedIndex()+1,cursoTextField.getText(),horas);
					} catch(NumberFormatException e){
						System.out.println("Error, el valor introducido no es un digito.");
					}
					
				}else{
					try {
						editarModulo(id,nombreTextField.getText(),cicloComboBox.getSelectedIndex()+1,cursoTextField.getText(),horas);
					}catch(NumberFormatException e){
						System.out.println("Error, el valor introducido no es un digito.");
					}			
				}
				clearFields();
				recargarTabla();
			}
		});
		btnGuardar.setBounds(235, 94, 89, 23);
		frame.getContentPane().add(btnGuardar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFields();
			}

		});
		btnLimpiar.setBounds(334, 93, 89, 23);
		frame.getContentPane().add(btnLimpiar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(433, 93, 89, 23);
		btnEliminar.setEnabled(false);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(id!=null){
					int res = JOptionPane.showConfirmDialog(null, "El módulo "+getNombre()+" se eliminará de forma permanente","Eliminar",JOptionPane.OK_CANCEL_OPTION);
					if(res==JOptionPane.OK_OPTION){
						eliminarModulo(id);
						clearFields();
						recargarTabla();
					}
				}
			}
		});
		frame.getContentPane().add(btnEliminar);

		btnAlumnos = new JButton("Alumnos");
		btnAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(id==null){
					verAlumnos();
				}else{
					verAlumnosDeModulo();
				}
			}
		});
		btnAlumnos.setBounds(571, 93, 89, 23);
		frame.getContentPane().add(btnAlumnos);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 139, 650, 2);
		frame.getContentPane().add(separator);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 650, 287);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEliminar.setEnabled(true);
				btnGuardar.setText("Guardar");
				nombreTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
				cicloComboBox.setSelectedIndex(getCicloIndex((String) table.getModel().getValueAt(table.getSelectedRow(), 1)));
				cursoTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
				horasTextField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
				id = Integer.parseInt((String)table.getModel().getValueAt(table.getSelectedRow(), 4));
			}
		});
		scrollPane.setViewportView(table);

		recargarTabla();

	}


	private void recargarTabla(){
		DefaultTableModel tm = new DefaultTableModel();
		tm.addColumn("Nombre");
		tm.addColumn("Ciclo");
		tm.addColumn("Curso");
		tm.addColumn("Horas");
		tm.addColumn("ID");
		for(String[] s:transformarListaVO()){
			s[1] = getCicloNombre(Integer.parseInt(s[1]));
			tm.addRow(s);
		}

		table.setModel(tm);
		table.removeColumn(table.getColumnModel().getColumn(4));
		table.getColumnModel().getColumn(0).setPreferredWidth(257);
		table.getColumnModel().getColumn(1).setPreferredWidth(214);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void clearFields() {
		id = null;
		btnEliminar.setEnabled(false);
		btnGuardar.setText("Añadir");
		nombreTextField.setText("");
		cursoTextField.setText("");
		horasTextField.setText("");
		table.clearSelection();
	}

	private Integer getCicloId(int index){
		return ciclos.get(index).getId();
	}

	private int getCicloIndex(String nombre){
		int i = 0;
		for(i=0;i<ciclos.size();i++){
			if(ciclos.get(i).getDescription().equalsIgnoreCase(nombre)){
				break;
			}
		}

		return i;
	}

	private String getCicloNombre(Integer id){
		String nombre = "";
		for(ComboxItem it:ciclos){
			if(it.getId().equals(id)){
				nombre=it.getDescription();
				break;
			}
		}
		return nombre;
	}

	private void verAlumnosDeModulo(){
		// TODO Lanzar interfaz matricula
		JOptionPane.showMessageDialog(null, "Esta funcionalidad aún no está implementada. Disculpe las molestias");
	}

	private void verAlumnos(){
		// TODO Lanzar interfaz alumnos
		verAlumnosDeModulo();
	}

	class ComboxItem
    {
        private Integer id;
        private String description;
        private int position = 0;

        public ComboxItem(Integer id, String description)
        {
            this.id = id;
            this.description = description;
        }

        public Integer getId()
        {
            return id;
        }

        public String getDescription()
        {
            return description;
        }

        public int getIndex(){
        	return position;
        }

        public void setIndex(int index){
        	position = index;
        }

        public String toString()
        {
            return description;
        }
    }
}
