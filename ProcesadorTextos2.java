import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.StyledEditorKit.ItalicAction;

import java.awt.*;
import java.awt.event.*;

public class ProcesadorTextos2 {

	public static void main(String[] args) {
		
		MarcoProcesador2 marco = new MarcoProcesador2();
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

//Marco 
class MarcoProcesador2 extends JFrame{
	
	public MarcoProcesador2() {
		
		setTitle("Procesador de Textos");
		setSize(550,500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		LaminaProcesador2 lamina = new LaminaProcesador2();
		add(lamina);
	}
}

//Lamina
class LaminaProcesador2 extends JPanel{
	
	private JMenuBar barra;
	private JMenu fuente,
				  estilo,
				  tamanio;
	private JTextPane miarea;
	private JScrollPane barraScroll;
	private ButtonGroup grupoTamanio;
	private JPopupMenu emergente;
	private JMenuItem negritaEm,
					  cursivaEm,
					  subrayadoEm;
	private JToolBar barraH;
	private int subrayado = 20;
	
	public LaminaProcesador2() {
		
		setLayout(new BorderLayout());
		
		JPanel laminaMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		barra = new JMenuBar();
		grupoTamanio = new ButtonGroup();
		
		//Menus-------------------------------
		fuente = new JMenu("Fuente");
		estilo = new JMenu("Estilo");
		tamanio = new JMenu("Tamaño");
		
		//MenuItem----------------------------
		configuraMenu("Arial","Fuente","Arial",9,10,"");
		configuraMenu("Courier","Fuente","Courier",9,10,"");
		configuraMenu("Verdana","Fuente","Verdana",9,10,"");
		//--------------------
		configuraMenu("Negrita","Estilo","",Font.BOLD,1,"negrita.png");
		configuraMenu("Cursiva","Estilo","",Font.ITALIC,1,"cursiva.png");
		configuraMenu("Subrayado","Estilo","",subrayado,1,"subrayado.png");
		//--------------------
		configuraMenu("12","Tamaño","",9,12,"");
		configuraMenu("16","Tamaño","",9,16,"");
		configuraMenu("20","Tamaño","",9,20,"");
		configuraMenu("24","Tamaño","",9,24,"");
		
		barra.add(fuente);
		barra.add(estilo);
		barra.add(tamanio);
		
		laminaMenu.add(barra);
		add(laminaMenu,BorderLayout.NORTH);
		
		miarea = new JTextPane();
		barraScroll = new JScrollPane(miarea);
		add(barraScroll,BorderLayout.CENTER);
		
		//Menu Emergente (click derecho)----------------
		emergente = new JPopupMenu();
				
		negritaEm = new JMenuItem("Negrita",new ImageIcon("negrita.png"));
		cursivaEm = new JMenuItem("Cursiva",new ImageIcon("cursiva.png"));
		subrayadoEm = new JMenuItem("Subrayado",new ImageIcon("subrayado.png"));
		
		negritaEm.addActionListener(new StyledEditorKit.BoldAction());
		cursivaEm.addActionListener(new StyledEditorKit.ItalicAction());
		subrayadoEm.addActionListener(new StyledEditorKit.UnderlineAction());
		
		emergente.add(negritaEm);
		emergente.add(cursivaEm);
		emergente.add(subrayadoEm);
				
		miarea.setComponentPopupMenu(emergente);
		
		//Barra Herramientas-------------------------------
		barraH = new JToolBar();
		
		barraHerramientas("negrita.png").addActionListener(new StyledEditorKit.BoldAction());
		barraHerramientas("cursiva.png").addActionListener(new StyledEditorKit.ItalicAction());
		barraHerramientas("subrayado.png").addActionListener(new StyledEditorKit.UnderlineAction());
		
		barraH.addSeparator();
		
		barraHerramientas("azul.png").addActionListener(new StyledEditorKit.ForegroundAction("azul",Color.BLUE));
		barraHerramientas("amarillo.png").addActionListener(new StyledEditorKit.ForegroundAction("amarillo",Color.YELLOW));
		barraHerramientas("rojo.png").addActionListener(new StyledEditorKit.ForegroundAction("rojo",Color.RED));
		
		barraH.addSeparator();
		
		barraHerramientas("izquierda.png").addActionListener(new StyledEditorKit.AlignmentAction("izquierda",0));
		barraHerramientas("centrada.png").addActionListener(new StyledEditorKit.AlignmentAction("centrada",1));
		barraHerramientas("derecha.png").addActionListener(new StyledEditorKit.AlignmentAction("derecha",2));
		barraHerramientas("justificada.png").addActionListener(new StyledEditorKit.AlignmentAction("justificada",3));
										
		barraH.setOrientation(1);
		add(barraH,BorderLayout.WEST);
		
	}
	
	//Metodo para JMenuItem
	public void configuraMenu(String rotulo,String menu,String tipoLetra,int estilos,int tam,String rutaIcono) {
		
		//Menu Tipo Fuente-------------------------
		if(menu.equalsIgnoreCase("fuente")) {
			JMenuItem elemMenu = new JMenuItem(rotulo,new ImageIcon(rutaIcono));
			elemMenu.setBackground(Color.GRAY.brighter());
			fuente.add(elemMenu);
			elemMenu.addActionListener(new StyledEditorKit.FontFamilyAction("",tipoLetra));
		}
		
		//Menu Estilo-------------------------
		else if(menu.equalsIgnoreCase("estilo")) {
			JMenuItem elemMenu = new JMenuItem(rotulo,new ImageIcon(rutaIcono));
			elemMenu.setBackground(Color.GRAY.brighter());
			estilo.add(elemMenu);
			
			if(estilos == Font.BOLD) {
				elemMenu.addActionListener(new StyledEditorKit.BoldAction());
				/*Para que la accion del menu tambien pueda realizarse
				 * con una combinacion de teclas se usa el metodo setAccelerator
				 * de la clase JMenuItem: 
				 * (META_DOWN_MASK hace referencia a la tecla Command en Mac)*/
				elemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.META_DOWN_MASK));
			}
			if(estilos == Font.ITALIC) {
				elemMenu.addActionListener(new StyledEditorKit.ItalicAction());
				elemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.META_DOWN_MASK));
			}
			
			if(estilos == subrayado) {
				elemMenu.addActionListener(new StyledEditorKit.UnderlineAction());
				elemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.META_DOWN_MASK));
			}
		}
		
		//Menu Tamaño-------------------------
		else if(menu.equalsIgnoreCase("tamaño")) {
			JRadioButtonMenuItem elemMenu = new JRadioButtonMenuItem(rotulo);
			elemMenu.setBackground(Color.GRAY.brighter());
			grupoTamanio.add(elemMenu);
			elemMenu.addActionListener(new StyledEditorKit.FontSizeAction(rotulo,tam));
			tamanio.add(elemMenu);
		}
	}
	
	
	//Metodo para Botones barra herramientas
	public JButton barraHerramientas(String rutaIcono) {
		
		JButton boton = new JButton(new ImageIcon(rutaIcono));
		barraH.add(boton);
		return boton;
	}
}
