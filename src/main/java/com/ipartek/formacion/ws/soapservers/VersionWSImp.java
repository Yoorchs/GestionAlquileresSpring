package com.ipartek.formacion.ws.soapservers;

import javax.jws.WebService;

@WebService(endpointInterface="com.ipartek.formacion.ws.soapservers.VersionWS", serviceName = "versionService")
public class VersionWSImp implements VersionWS {

	public String getVersion() {
		 return "Version 1.0.0";
	}

}
