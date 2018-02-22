package br.com.gv2c.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gv2c.painel.vo.ProcessoVO;


@FacesConverter(value="processoConverter")
public class ProcessoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		for (UIComponent child : component.getChildren()) {  
            if (child instanceof UISelectItems) {  
                @SuppressWarnings("unchecked")
				List<ProcessoVO> items = (List<ProcessoVO>) ((UISelectItems) child).getValue();  
                if(items!=null){
	                return obterProcesso(items, value);
                }
            }  
        } 
		return null;
	}

	private Object obterProcesso(List<ProcessoVO> items, String value) {
		System.out.println("obterProcesso:" + value);

		for(ProcessoVO vo : items){
        	if(vo.getId().toString().equals(value)){
        		return vo; 
        	}
        }
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && ! "".equals(value)) {
			ProcessoVO vo = (ProcessoVO) value;
			System.out.println("getAsString Selecionado:" + vo.getId() + "-" +vo.getNome());
            return vo.getId().toString();
		}
		return null;
	}
}
