package codigo;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		String ruta = new String("C:/Users/carlo/Documents/Teoria_Compiladores/Analizador-ProyectoGrupal/src/codigo/Lexer.flex");
		generarLexer(ruta);
	}
	
	public static void generarLexer(String ruta) {
		File archivo = new File(ruta);
		JFlex.Main.generate(archivo);
	}
}
