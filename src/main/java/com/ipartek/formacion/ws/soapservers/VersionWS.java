package com.ipartek.formacion.ws.soapservers;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(targetNamespace="http://formacion.ipartek.com")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface VersionWS {

	 @WebMethod(operationName="obtenerVersion")
	    public String getVersion();
	
}
