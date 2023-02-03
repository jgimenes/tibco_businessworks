package customfunctions;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.customfunctions", helpText = "Funções customizadas para tratamento de strings.")
public class FormatacaoCPF {

	@XPathFunction(helpText = "Retorna o número do CPF no formato 999.999.999-99. ", parameters = {
			@XPathFunctionParameter(name = "numeroCPF", optional = false, optionalValue = "") })
	public String formatarCPF(String numeroCPF) {
		return FormatacaoTexto.formatarTexto(numeroCPF, "###.###.###-##");
	}
}
