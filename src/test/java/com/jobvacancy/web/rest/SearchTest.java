package com.jobvacancy.web.rest;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jobvacancy.Application;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.web.rest.util.Search;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SearchTest {
	 private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
	 private static final String DEFAULT_LOCATION = "SAMPLE_TEXT";
	 private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
	 
	 @Inject
	 private JobOfferRepository jobOfferRepository;
		
	 private JobOffer jobOffer;
	 private JobOffer jobOffer2;
		
	 @Before
	 public void initTest() {
	 jobOffer = new JobOffer();
	 jobOffer.setTitle(DEFAULT_TITLE);
	 jobOffer.setLocation(DEFAULT_LOCATION);
	 jobOffer.setDescription(DEFAULT_DESCRIPTION);
	 jobOffer2 = new JobOffer();
	 jobOffer2.setTitle("Tester");
	 jobOffer2.setLocation("Lanus");
	 jobOffer2.setDescription("Junit");
	 }
	 
	 @Test
	 @Transactional
	 public void searchWithOperatorOr() throws Exception {
	 
	 jobOffer.setTitle("Tester java");
	 jobOffer.setDescription("Programador java para testeo de aplicaciones");
	 jobOffer2.setTitle("Desarrollo movil");
	 jobOffer2.setDescription("Desarrollador juniors para aplicaciones movil");
	 jobOfferRepository.deleteAll();
	 jobOfferRepository.saveAndFlush(jobOffer);
	 jobOfferRepository.saveAndFlush(jobOffer2);
	 
	 List<JobOffer> list = jobOfferRepository.findAll();
	 List<JobOffer> listJobOffer = new Search().search(list,"Programador"); 
	 
	 assertEquals(1,listJobOffer.size());
	 assertThat(listJobOffer.get(0).getTitle()).isEqualTo("Tester java");
	 assertThat(listJobOffer.get(0).getLocation()).isEqualTo(DEFAULT_LOCATION);
	 assertThat(listJobOffer.get(0).getDescription()).isEqualTo("Programador java para testeo de aplicaciones");
	 }
}
