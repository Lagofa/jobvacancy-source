
package com.jobvacancy.web.rest.utils;

import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.exception.DateException;
import com.jobvacancy.domain.exception.ExperienceException;
import com.jobvacancy.domain.exception.TitleException;


public class ValidatorJobOffer {
	
	public  void validateJobOffer(JobOffer jobOffer) throws DateException, TitleException, ExperienceException{
		jobOffer.setStartDate(jobOffer.getStartDate());
		jobOffer.setEndDate(jobOffer.getEndDate());
		jobOffer.setTitle(jobOffer.getTitle());
		jobOffer.setDescription(jobOffer.getDescription());
		jobOffer.setExperiencia(jobOffer.getExperiencia());
		jobOffer.setId(jobOffer.getId());
		jobOffer.setLocation(jobOffer.getLocation());
		jobOffer.setOwner(jobOffer.getOwner());
	}

}
