package com.jobvacancy.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jobvacancy.Application;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.repository.UserRepository;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;

@WebAppConfiguration
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class US31StepDefs {
	
    @Mock
    private UserRepository mockUserRepository;

    @Inject
    private JobOfferRepository jobOfferRepository;

    private JobOffer jobOffer;

    List<JobOffer>jobOffers;
    Date today;
    @Before
    public void initTest() {
        jobOffer = new JobOffer();
        today=Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                
    }

    @Given("^existe una jobOffer \"([^\"]*)\" con vencimiento (\\d+) dia despues de hoy$")
    public void existe_una_jobOffer_con_vencimiento_dia_despues_de_hoy(String arg1, int arg2) throws Throwable {	Date today=Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	   Date tomorrow=new Date(today.getTime() + TimeUnit.DAYS.toMillis(arg2));
		jobOffer.setTitle(arg1);
	    jobOffer.setStartDate(today);
	    jobOffer.setEndDate(tomorrow);
	    jobOfferRepository.save(jobOffer);
    }

	@When("^el oferente va a ver las jobOffers no vencidas$")
	public void el_oferente_va_a_ver_las_jobOffers_no_vencidas() throws Throwable {
	  jobOffers=jobOfferRepository.findJobOffersActives();
	
	}
	
	@Then("^el oferente ve la jobOffer \"([^\"]*)\" no vencida$")
	public void el_oferente_ve_la_jobOffer(String arg1) throws Throwable {
		assertThat(jobOffers.get(0).getTitle()).isEqualTo("Programador Java");
	    jobOfferRepository.deleteAll();
	}
	
	@Given("^existe una jobOffer \"([^\"]*)\" con vencimiento ayer$")
	public void existe_una_jobOffer_con_vencimiento_ayer(String arg1) throws Throwable {
	}
	
	@When("^el oferente va a ver las jobOffers vencidas$")
	public void el_oferente_va_a_ver_las_jobOffers_vencidas() throws Throwable {
	}

}
