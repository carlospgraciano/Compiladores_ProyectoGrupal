package codigo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Window.Type;

public class FrmMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMain() {
		setTitle("Proyecto Grupal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea txtEntrada = new JTextArea();
		txtEntrada.setBounds(10, 11, 351, 298);
		
		JTextArea txtSalida = new JTextArea();
		txtSalida.setBounds(388, 11, 253, 364);
		txtSalida.setEditable(false);
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.setBounds(10, 320, 178, 55);
		btnAnalizar.setFont(new Font("Tahoma", Font.PLAIN, 34));
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Escribiendo en un archivo, lo que se ponga en el text field
				File archivo = new File("archivo.txt");
				PrintWriter escribir;
				try {
					escribir = new PrintWriter(archivo);
					escribir.print(txtEntrada.getText());
					escribir.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// Analizando con JFlex haciendo uso del archivo creado
				try {
					// Leyendo el archivo
					Reader lector = new BufferedReader(new FileReader("archivo.txt"));
					// Creando el analizador lógico en base al archivo
					Lexer lexer = new Lexer(lector);
					// String para el resultado final
					String resultado = "";
					
					while(true) {
						// Leyendo los tokens del archivo
						Tokens tokens = lexer.yylex();
						
						// Cuando se termina el contenido del archivo
						if(tokens == null) {
							resultado += "FIN";
							txtSalida.setText(resultado);
						}
						
						// Decisión de cada token
						switch(tokens) {
							case Suma:
							case Resta:
							case Multiplicacion:
							case Division:
							case Potencia:
							case Abre_Parentesis:
							case Cierra_Parentesis:
							case Digito:
								resultado += tokens + "\n";
								break;
							case Valida:
								resultado += "Expresión valida\n";
								break;
							default:
								resultado += "Simbolo no definido\n";
								break;
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(198, 320, 163, 55);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 34));
		contentPane.setLayout(null);
		contentPane.add(txtEntrada);
		contentPane.add(btnAnalizar);
		contentPane.add(btnCerrar);
		contentPane.add(txtSalida);
		
	}
}
