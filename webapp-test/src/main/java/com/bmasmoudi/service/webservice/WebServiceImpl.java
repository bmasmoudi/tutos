package com.bmasmoudi.service.webservice;

import com.bmasmoudi.service.model.WebserviceIutPut;
import com.bmasmoudi.service.model.WebserviceOutPut;


@javax.jws.WebService
public class WebServiceImpl implements WebServiceItf {

	@Override
	public WebserviceOutPut operationWebservice(WebserviceIutPut vIn) {
		System.out.println("Hello Ws implementation");
		return null;
	}
	

}
