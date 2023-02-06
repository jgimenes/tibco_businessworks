package customfunctions;

import java.io.Serializable;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;


/**
 * Classe formatar string
 * 
 * @author Julio Gimenes
 * @version 1.0
 * @since 2023-02-02 
 *  
 * Formata uma string com base na máscara informada.
 * 
 * @param texto Texto as ser formatado.
 * @param mascara Mascara para formatação.
 * @return String formatada.
 */

public class FormatacaoTexto implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5132721501804691318L;

	public static String formatarTexto(String texto, String mascara) {
		
		String textoFormatado;
				
		try {
			MaskFormatter formatter = new MaskFormatter(mascara.trim());
			formatter.setValueContainsLiteralCharacters(false);
			textoFormatado = formatter.valueToString(texto.trim());
		} catch (ParseException e1) {
			textoFormatado = texto;
			e1.printStackTrace();
		}
		
		return textoFormatado.trim().toString();
		
	}

}