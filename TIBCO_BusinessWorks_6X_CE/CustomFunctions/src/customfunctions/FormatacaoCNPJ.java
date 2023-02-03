package customfunctions;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.customfunctions", helpText = "Funções customizadas para tratamento de strings.")
public class FormatacaoCNPJ {

	@XPathFunction(helpText = "Retorna o número do CNPJ no formato ##.###.###/####-##. ", parameters = {
			@XPathFunctionParameter(name = "numeroCNPJ", optional = false, optionalValue = "") })
	public String formatarCNPJ(String numeroCNPJ) {
		return FormatacaoTexto.formatarTexto(numeroCNPJ, "##.###.###/####-##");
	}
}
