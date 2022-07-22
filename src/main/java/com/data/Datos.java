package com.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.models.entity.Cliente;

public class Datos {

	
	static SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");		
	static String timeStampfebaja="2021-12-28 18:59:00";
	static String timeStampferegistro="2021-12-28 18:59:00";
	static String datefenacimiento="1989-08-07";
	static Date fenacimiento = null;
	
	public static Cliente expected() {		
	try {
		fenacimiento = date.parse(datefenacimiento);
	} catch (ParseException e) {			
		e.printStackTrace();
	}
	Date febaja = null;
	try {
		febaja = timeStamp.parse(timeStampfebaja);
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	Date feregistro = null;
	try {
		feregistro = timeStamp.parse(timeStampferegistro);
	} catch (ParseException e2) {
		e2.printStackTrace();
	}
	
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

	return clienteTest;
}
 public static final Cliente datos= expected();
 
}
