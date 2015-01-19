package com.bmasmoudi.service.webservice;

import com.bmasmoudi.service.model.WebserviceIutPut;
import com.bmasmoudi.service.model.WebserviceOutPut;


@javax.jws.WebService
public interface WebServiceItf {
	
	
	@javax.jws.WebMethod
	WebserviceOutPut operationWebservice(
			@javax.jws.WebParam(name = "webserviceIn") WebserviceIutPut vIn);


}
