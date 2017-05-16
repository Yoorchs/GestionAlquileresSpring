package com.ipartek.formacion.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	public static boolean ValidarDNI(String dni) {
		boolean valido = false;
		String regex = "\\d{8}[a-zA-Z]";
		String[] letrasDNI = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E", "T" };
		logger.info("Validando mediante Utils el DNI: " + dni);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dni);
		if (matcher.matches()) {
			int posicion = Integer.parseInt(dni.substring(0, 8));
			posicion = posicion % 23;
			String letra = dni.substring(8, 9);
			if (letra.equalsIgnoreCase(letrasDNI[posicion])) {
				valido = true;
				logger.info("DNI: " + dni + " valido");
			}
		}
		return valido;
	}

	public static boolean validarNIF(String nif) {
		boolean correcto = false;
		final String CONTROL_SOLO_NUMEROS = "ABEH";
		final String CONTROL_SOLO_LETRAS = "KPQS";
		final String CONTROL_NUMERO_A_LETRA = "JABCDEFGHI";
		Pattern pattern = Pattern.compile("[[A-H][J-N][P-S]UVW][0-9]{7}[0-9A-J]");
		Matcher matcher = pattern.matcher(nif);
		if (matcher.matches()) {
			int parA = 0;
			for (int i = 2; i < 8; i += 2) {
				final int digito = Character.digit(nif.charAt(i), 10);
				if (digito < 0) {
					return false;
				}
				parA += digito;
			}
			int nonB = 0;
			for (int i = 1; i < 9; i += 2) {
				final int digito = Character.digit(nif.charAt(i), 10);
				if (digito < 0) {
					return false;
				}
				int nn = 2 * digito;
				if (nn > 9) {
					nn = 1 + (nn - 10);
				}
				nonB += nn;
			}

			final int parcialC = parA + nonB;
			final int digitoE = parcialC % 10;
			final int digitoD = (digitoE > 0) ? (10 - digitoE) : 0;
			final char letraIni = nif.charAt(0);
			final char caracterFin = nif.charAt(8);

			if (// �el caracter de control es v�lido como letra?
			(CONTROL_SOLO_NUMEROS.indexOf(letraIni) < 0 && CONTROL_NUMERO_A_LETRA.charAt(digitoD) == caracterFin) ||
			// �el caracter de control es v�lido como d�gito?
					(CONTROL_SOLO_LETRAS.indexOf(letraIni) < 0 && digitoD == Character.digit(caracterFin, 10)))
			correcto = true;
		}
		return correcto;
	}
}