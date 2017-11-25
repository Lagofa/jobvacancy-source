package com.jobvacancy.domain.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.jobvacancy.domain.exception.DateException;
import com.jobvacancy.domain.exception.ExperienceException;
import com.jobvacancy.domain.exception.TitleException;

public class ValidatorJobOffer {
	

	Date today=Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	
	public Date validateEndDate(Date startDate, Date endDate) throws DateException{
		if(!((today.before(endDate) || today.equals(endDate)) && (startDate.before(endDate) || startDate.equals(endDate)))){
        	throw new DateException("Invalid End Date");
        }
		return endDate;
	}
	
	public String validateTitle(String title) throws TitleException{
		if(title==null){
        	throw new TitleException("Invalid Title");
        }
		return title;
	}
	public Long validateExperience(Long experience) throws ExperienceException{
		if(experience==null){
			experience=new Long(0);
        }else
        	if(experience<new Long(0)){
            	throw new ExperienceException("Invalid Experience");
        	}
		return experience;
	}
}
