package br.com.padariaweb.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import lombok.SneakyThrows;

public class Util {

	/**
	 * @return a lista de UFs do país.
	 */
	public static List<String> getListaUFs() {
		return Arrays.asList("AC", "AL", "AM", "AP", "BA", "CE", "DF", 
				"ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI",
				"PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO");
	}
	
	/**
	 * Verifica se o atributo é válido.
	 * 
	 * <ul>
	 * <li>Se for uma {@link String}, verifica se é diferente de <code>null</code> ou vazia.</li>
	 * <li>Se for um número, verifica se é diferente de <code>null</code> ou zero.</li>
	 * <li>Se for uma coleção, verifica se é diferente de <code>null</code> e se não está vazia.</li>
	 * </ul>
	 */
	public static boolean isValido(Object objeto) {
		// Objeto null
		if (objeto == null)
			return false;
		// Se string, testa vazio
		if (objeto instanceof String && 
			objeto.toString().trim().isEmpty())
			return false;
		// Se número, testa zero
		if (objeto instanceof Number &&
			(objeto.toString().isEmpty()))
			return false;
		// Se coleção, testa vazio
		if (objeto instanceof Collection &&
			((Collection<?>)objeto).isEmpty())
			return false;
		
		return true;
	}
	
	/**
	 * Gera uma semente aleatória de 26 caracteres para encriptação da senha do usuário.
	 */
	@SneakyThrows
	public static String gerarStringAleatoria() {
		byte[] bytes = new byte[8];
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(bytes);
		return converterParaBase64(bytes);
	}
	
	private static String converterParaBase64(byte[] data) {
		return new Base64().encodeToString(data);
	}
	private static byte[] converterParaBase64(String data) {
		return new Base64().decode(data);
	}
	
	/**
	 * Encripta uma string. Usado para codificação das senhas dos usuários.
	 * 
	 * @param texto - texto da string a encriptar
	 * @param salt - semente aleatória encriptadora
	 */
	@SneakyThrows
	public static String encriptar(String texto, String salt) {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(converterParaBase64(salt));
		byte[] input = md.digest(texto.getBytes("UTF-8"));
		for (int i = 0; i < 5 /* default iteration number */; i++) {
			md.reset();
			input = md.digest(input);
		}
		return converterParaBase64(input);
	}
	
	/**
	 * Manipula o horário do objeto para deixá-lo no início do dia ou no final do dia.
	 *  
	 * @param dataHora - objeto que deverá ter a data e a hora manipulados
	 * @param finalDoDia - se <code>true</code>, muda a hora para 23h 59m 59s;
	 *                     se <code>false</code>, muda a hora para 00h 00m 00s.
	 */
	public static Date atualizarHora(Date dataHora, HorarioType horario) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(dataHora);
        cal.set(Calendar.HOUR_OF_DAY, horario == HorarioType.INICIO_DO_DIA ? 0 : 23);
        cal.set(Calendar.MINUTE,      horario == HorarioType.INICIO_DO_DIA ? 0 : 59);
        cal.set(Calendar.SECOND,      horario == HorarioType.INICIO_DO_DIA ? 0 : 59);
        cal.set(Calendar.MILLISECOND, horario == HorarioType.INICIO_DO_DIA ? 0 : 999);
        return cal.getTime();
	}
	
	/**
	 * Objeto usado para referenciar o controle do método {@link OdinUtil#atualizarHora(Date, HorarioType)}.
	 */
	public static enum HorarioType {
		/**
		 * Início do dia: zera as horas do objeto (00h 00min 00s)
		 */
		INICIO_DO_DIA,
		/**
		 * Final do dia: define as horas do objeto para o máximo (23h 59min 59s)
		 */
		FINAL_DO_DIA
	}
	
	public static List<String> horariosDia30minutos(){
		List<String> horarios = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			horarios.add((String.format("%02d", i))+":00");
			horarios.add((String.format("%02d", i))+":30");
		}
		return horarios;
	}
}