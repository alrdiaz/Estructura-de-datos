package com.models.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ClienteTest {


	@Test
	void testValidate() {
		
		String expected = null;
		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");		
		String timeStampfebaja="2021-12-28 18:59:00";
		String timeStampferegistro="2021-12-28 18:59:00";
		String datefenacimiento="1989-08-07";
		Date fenacimiento = null;
		try {
			fenacimiento = date.parse(datefenacimiento);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		Date febaja = null;
		try {
			febaja = timeStamp.parse(timeStampfebaja);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date feregistro = null;
		try {
			feregistro = timeStamp.parse(timeStampferegistro);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(fenacimiento);			
		System.out.println(febaja);		
		System.out.println(timeStamp.format(febaja));
		System.out.println(timeStamp.format(feregistro));
		Cliente clienteTest = new Cliente(Long.valueOf(1047412114),
				                          1,
				                          "Alejandro Luis",
				                          "Romero Diaz",
				                          "tr9#5-84 OIBA-SANTANDER",
				                          "aromero4@eafit.edu.co",
				                          "6046604072",
				                          "6046600317",
				                          "3017957267",
				                          5,
				                          "Ingeniero de control",
				                          055457,
				                          "Sabaneta",
				                          fenacimiento,
				                          "M",
				                          5,
				                          "Villas del Carmen",
				                          "HMV Ingenieros",
				                          5,
				                          "EPC",
				                          057,
				                          "Colombia",
				                          "Pintura, Futbol, Musica, Cine, Runnig, Viajar",
				                          febaja,
				                          feregistro);
		
		String result=clienteTest.validate();
		
		System.out.println(expected);
		System.out.println(result);
		Assert.assertEquals(expected,result);
		
	}

}
