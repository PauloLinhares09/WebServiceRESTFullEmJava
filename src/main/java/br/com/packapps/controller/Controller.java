package br.com.packapps.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class Controller {
	
	public static String[] deStringForArray(String list, String separador){
		String[] listArray = list.split(separador);
		
		return listArray;
	}

	public static String getDataSql() {
		Date hoje  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(hoje);
	}

}
