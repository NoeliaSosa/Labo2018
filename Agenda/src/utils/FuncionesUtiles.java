package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncionesUtiles {

	private static boolean validador(String regex, String matcher) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(matcher);
		return mat.matches();
	}

	public static boolean validarFecha(String txt) {
		return validador(
				"^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[/\\\\/](19|20)\\d{2}$",
				txt);
	}

	public static boolean validarEmail(String txt) {
		String EMAIL_VERIFICATION = "([a-zA-Z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+";
		return validador(EMAIL_VERIFICATION, txt);
	}

	public static boolean validarTelefono(String txt) {
		return validador("^\\(?(\\d{2,5})?\\)?\\s?(15)?[\\s|-]?(4)\\d{2,3}[\\s|-]?\\d{4}$",txt);
	}

	public static boolean validarSoloLetras(String txt) {
		return validador("^[a-zA-Z\\u00F1\\u00D1\\s]+$", txt);
	}

	public static boolean validarCalle(String txt) {
		return validador("^[a-zA-Z0-9\\u00F1\\u00D1\\s\\.]{3,}", txt);
	}

	public static boolean validarPiso(String txt) {
		return validador("(\\d*)?", txt);
	}

	public static boolean soloNumeros(String txt) {
		return validador("^[0-9]*$", txt);
	}
	
	public static boolean validaLocalidad(String inputLocalidad) {
		return validador("^a-zA-Z0-9", inputLocalidad);
	}

	public static String[] obtenerDatosDeConexion() {
		String[] datos = new String[3];// "jdbc:mysql://localhost:3306/agenda\",\"root\",\"Tiempo8990\"";
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader("."+File.separator+"datos"+File.separator+"conexion.txt"));
			String sCadena = null;
			int i = 0;
			while ((sCadena = bf.readLine()) != null) {
				datos[i] = sCadena;
				i++;
			}

		} catch (FileNotFoundException e) {
			return datos;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bf != null)
					bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datos;
	}

	public static boolean guardarDatosDeConexion(String ip, String puerto,
			String user, String pass) {
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("."+File.separator+"datos"+File.separator+"conexion.txt"));
			bw.write( ip + ":" + puerto );
			bw.newLine();
			bw.write(user);
			bw.newLine();
			bw.write(pass);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}



}
