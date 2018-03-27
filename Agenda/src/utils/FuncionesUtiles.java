package utils;

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
		String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
		return validador(EMAIL_VERIFICATION, txt);
	}

	public static boolean validarTelefono(String txt) {
		return validador("\\d*", txt);
	}

	public static boolean validarSoloLetras(String txt) {
		return validador("^[a-zA-Z\u00F1\u00D1]+$", txt);
	}

	public static boolean validarCalle(String txt) {
		return validador("[A-Z][a-zA-Z]*\\D{3}", txt);
	}

	public static boolean validarPiso(String txt) {
		return validador("\\d*", txt);
	}

	public static boolean validarAltura(String txt) {
		return validador("^[0-9]*$", txt);
	}
}
