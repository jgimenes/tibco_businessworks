package customfunctions;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "Custom String Functions", prefix = "cf", namespace = "http://tibco.bw.customfunctions", helpText = "Custom defined function")
public class FormatacaoCEP {

	@XPathFunction(helpText = "Retorna o número do CEP no formato 99999-999.", parameters = {
			@XPathFunctionParameter(name = "numeroCEP", optional = false, optionalValue = "") })
	public String formatarCEP(String numeroCEP) {
		return FormatacaoTexto.formatarTexto(numeroCEP, "#####-###");
	}
}
