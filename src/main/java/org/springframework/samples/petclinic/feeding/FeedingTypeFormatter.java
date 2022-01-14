package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{

	@Autowired
	private FeedingService feedingService;
	
    @Override
    public String print(FeedingType object, Locale locale) {
        // TODO Auto-generated method stub
    	String nombre = object.getName();
        return nombre;
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
    	FeedingType ft = this.feedingService.getFeedingType(text);
    	if(Objects.isNull(ft)) {
    		throw new ParseException(text, 0);
    	}else {
    		return ft;
    	}
    }
    
}
