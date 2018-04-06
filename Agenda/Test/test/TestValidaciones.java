package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.FuncionesUtiles;

public class TestValidaciones {

	@Test
	public void validarEmailOk(){
		String text = "joaco@htmial.com";
		assertTrue(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailSinA(){
		String text = "joacohtmial.com";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailConMayus(){
		String text = "Joaco@htmial.com";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailSinPunto(){
		String text = "joaco@htmialcom";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailSinLetraAntesArroba(){
		String text = "@htmialcom";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailConMultiplePunto(){
		String text = "joaco.lema@htmial.com";
		assertTrue(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailVacio(){
		String text = "";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailSoloPuntoyArroba(){
		String text = ".@";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailSoloNumeros(){
		String text = "123123";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailNumerosyLetras(){
		String text = "123123asd@hotmail.com";
		assertTrue(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarEmailConEspacioAntes(){
		String text = " 123123asd@hotmail.com";
		assertFalse(FuncionesUtiles.validarEmail(text));
	}
	
	@Test
	public void validarTelefono(){
		String text = "4667-2405";
		assertTrue(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefono2(){
		String text = "(011)4667-2405";
		assertTrue(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefono3(){
		String text = "(011)4667 2405";
		assertTrue(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefono4(){
		String text = "(11)4667 2405";
		assertTrue(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoError(){
		String text = "123123";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoSinNume(){
		String text = "()-";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoVacio(){
		String text = "";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoLetras(){
		String text = "asdsad";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoLetrasYNumeros(){
		String text = "as1ds321ad";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoMenos6Numeros(){
		String text = "12345";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoMas10Numeros(){
		String text = "12345678911";
		assertTrue(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoConGuionMedio(){
		String text = "1234-5678";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarTelefonoConParentesis(){
		String text = "(12)5678";
		assertFalse(FuncionesUtiles.validarTelefono(text));
	}
	
	@Test
	public void validarCalle(){
		String text = "calle";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleConPunto(){
		String text = "Pte. Peron";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleVacio(){
		String text = "";
		assertFalse(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleEspacio(){
		String text = " ";
		assertFalse(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleEspacioDoble(){
		String text = "calle juan al";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleConEspacio(){
		String text = "calle calle";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleConNum(){
		String text = "25 de mayo";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleMinus(){
		String text = "calle";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleConEspFinal(){
		String text = "calle ";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleMayus(){
		String text = "ASD";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarCalleMixto(){
		String text = "Asaddd";
		assertTrue(FuncionesUtiles.validarCalle(text));
	}
	
	@Test
	public void validarSoloLetrasInicioMayus(){
		String text = "Asaddd";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));	
	}
	
	@Test
	public void validarSoloLetras(){
		String text = "abc";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasConNumeros(){
		String text = "abc12";
		assertFalse(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasVacio(){
		String text = "";
		assertFalse(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasMayuscula(){
		String text = "ASD";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasMixto(){
		String text = "AsDd";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasConEspacio(){
		String text = "joaquin lema";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasConEspacioMayus(){
		String text = "Joaquin Lema";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validarSoloLetrasConEspacioMayusyMinus(){
		String text = "Joaquin Lema pato";
		assertTrue(FuncionesUtiles.validarSoloLetras(text));
	}
	
	@Test
	public void validaPiso(){
		String txt = "2";
		assertTrue(FuncionesUtiles.validarPiso(txt));
	}
	
	@Test
	public void validaPisoDoble(){
		String txt = "21";
		assertTrue(FuncionesUtiles.validarPiso(txt));
	}
	
	@Test
	public void validaPisoVacio(){
		String txt = "";
		assertTrue(FuncionesUtiles.validarPiso(txt));
	}
	
	@Test
	public void validaPisoConLetra(){
		String txt = "a";
		assertFalse(FuncionesUtiles.validarPiso(txt));
	}
	
	@Test
	public void validaPisoConLetraMayus(){
		String txt = "M";
		assertFalse(FuncionesUtiles.validarPiso(txt));
	}
	
	
}
