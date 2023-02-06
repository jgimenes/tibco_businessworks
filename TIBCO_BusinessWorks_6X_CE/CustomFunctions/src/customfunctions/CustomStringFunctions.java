package customfunctions;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.CustomStringFunctions", helpText = "Funções customizadas para tratamento de strings.")
public class CustomStringFunctions {

	@XPathFunction(helpText = "Retorna o número do CEP no formato 99999-999.", parameters = {
			@XPathFunctionParameter(name = "numeroCEP", optional = false, optionalValue = "") })
	public String formatarCEP(String numeroCEP) {
		return FormatacaoTexto.formatarTexto(numeroCEP, "#####-###");
	}

	@XPathFunction(helpText = "Retorna o número do CNPJ no formato 99.999.999/9999-99.", parameters = {
			@XPathFunctionParameter(name = "numeroCNPJ", optional = false, optionalValue = "") })
	public String formatarCNPJ(String numeroCNPJ) {
		return FormatacaoTexto.formatarTexto(numeroCNPJ, "##.###.###/####-##");
	}

	@XPathFunction(helpText = "Retorna o número do CPF no formato 999.999.999-99.", parameters = {
			@XPathFunctionParameter(name = "numeroCPF", optional = false, optionalValue = "") })
	public String formatarCPF(String numeroCPF) {
		return FormatacaoTexto.formatarTexto(numeroCPF, "###.###.###-##");
	}

	@XPathFunction(helpText = "Validar e-mail utilizando expressões regulares.", parameters = {
			@XPathFunctionParameter(name = "email", optional = false, optionalValue = "") })
	public Boolean validarEmail(String email) {
		boolean isValidEmail = false;
		if (email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isValidEmail = true;
			}

		}

		return isValidEmail;
	}

	@XPathFunction(helpText = "Validar o número do CPF.", parameters = {
			@XPathFunctionParameter(name = "numeroCPF", optional = false, optionalValue = "") })
	public Boolean validarCPF(String numeroCPF) {
		numeroCPF = normalizarTexto(numeroCPF.trim());
		if ((numeroCPF == null) || (numeroCPF.length() < 11) || (numeroCPF.matches("^(\\d)\\1{10}"))) {
			return false;
		} else {
			Integer dig1 = calcularDV(numeroCPF.substring(0, 9), pesosCPF);
			Integer dig2 = calcularDV(numeroCPF.substring(0, 9) + dig1, pesosCPF);
			if (numeroCPF.equals(numeroCPF.substring(0, 9) + dig1.toString() + dig2.toString())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@XPathFunction(helpText = "Validar o número do CNPJ.", parameters = {
			@XPathFunctionParameter(name = "numeroCNPJ", optional = false, optionalValue = "") })
	public Boolean validarCNPJ(String numeroCNPJ) {
		numeroCNPJ = normalizarTexto(numeroCNPJ.trim());
		if ((numeroCNPJ == null) || (numeroCNPJ.length() < 14) || (numeroCNPJ.matches("^(\\d)\\1{13}"))) {
			return false;
		} else {
			Integer dig1 = calcularDV(numeroCNPJ.substring(0, 12), pesosCNPJ);
			Integer dig2 = calcularDV(numeroCNPJ.substring(0, 12) + dig1, pesosCNPJ);
			if (numeroCNPJ.equals(numeroCNPJ.substring(0, 12) + dig1.toString() + dig2.toString())) {
				return true;
			} else {
				return false;
			}
		}

	}

	@XPathFunction(helpText = "Conta as linhas de uma string.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public Integer contarLinhas(String texto) {
		return (int) texto.lines().count();
	}
	
	@XPathFunction(helpText = "Inverter o texto.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public String inverterTexto(String texto) {
		return new StringBuilder(texto).reverse().toString();
	}

	@XPathFunction(helpText = "Contar as palavras de uma string.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public Integer contarPalavras(String texto) {
		String[] words = texto.split("\\s+");
		return words.length;
	}

	@XPathFunction(helpText = "Remover acentos de uma string.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public String removerAcentos(String texto) {
		if (texto != null) {
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
			texto = texto.replaceAll("[^\\p{ASCII}]", "");
		}
		return texto;
	}

	@XPathFunction(helpText = "Remover caracteres especiais de uma string.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public String normalizarTexto(String texto) {
		if (texto != null) {
			texto = texto.replaceAll("[^A-Za-z0-9]", "");
		}
		return texto;
	}

	private static final int[] pesosCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesosCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDV(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

}
