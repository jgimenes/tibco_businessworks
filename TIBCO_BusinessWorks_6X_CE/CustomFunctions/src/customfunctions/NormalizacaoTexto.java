package customfunctions;

import java.text.Normalizer;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.customfunctions", helpText = "Funções customizadas para tratamento de strings.")
public class NormalizacaoTexto {

	@XPathFunction(helpText = "Remover acentos e caracteres especiais do texto.", parameters = {
			@XPathFunctionParameter(name = "texto", optional = false, optionalValue = "") })
	public String normalizarTexto(String texto) {
		if (texto != null) {
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
			texto = texto.replaceAll("[^\\p{ASCII}]", "");
		}
		return texto;
	}
}
