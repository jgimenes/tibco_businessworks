package customfunctions;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.customfunctions", helpText = "Custom defined function")
public class FormatacaoCNPJ {

	@XPathFunction(helpText = "Retorna o número do CNPJ no formato 99.999.999/9999-99.", parameters = {
			@XPathFunctionParameter(name = "numeroCEP", optional = false, optionalValue = "") })
	public String formatarCNPJ(String numeroCNPJ) {
		return FormatacaoTexto.formatarTexto(numeroCNPJ, "##.###.###/####-##");
	}
}
