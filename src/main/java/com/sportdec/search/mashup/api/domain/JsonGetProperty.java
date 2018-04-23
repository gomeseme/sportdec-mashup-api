package com.sportdec.search.mashup.api.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;

public interface JsonGetProperty {
	
	final Logger logger = LoggerFactory.getLogger(JsonGetProperty.class);

	default String getStringFromJson(JsonElement element) {
		if(!element.isJsonNull())
			return element.getAsString();
		
		return "";
	}
	
	default Date getDateFromJson(JsonElement element, DateFormat dateFormat) {
		try {
			if(element.isJsonNull())
				return null;
			
			return dateFormat.parse(element.getAsString());
		} catch (ParseException e) {
			logger.error("Error on parsing Date from Json. " + e.getMessage(), e);
		}
		return null;
	}
	
}
