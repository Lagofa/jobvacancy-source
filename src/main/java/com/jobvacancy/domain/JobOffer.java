package com.jobvacancy.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.jobvacancy.domain.exception.DateException;
import com.jobvacancy.domain.exception.ExperienceException;
import com.jobvacancy.domain.exception.TitleException;
import com.jobvacancy.domain.util.ValidatorJobOffer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * A JobOffer.
 */
@Entity
@Table(name = "JOB_OFFER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobOffer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @Type(type = "date")
    private Date startDate;

    @Column(name = "end_date")
    @Type(type = "date")
    private Date endDate;

    @Column(name = "experiencia")
    private Long experiencia;
    
    @Column(name = "postulations")
    private Long postulations;

    @ManyToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws TitleException{
    	ValidatorJobOffer validator=  new ValidatorJobOffer();
        this.title = validator.validateTitle(title);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
    public Long getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Long experiencia)  throws ExperienceException{
    	ValidatorJobOffer validator=  new ValidatorJobOffer();    
    	this.experiencia = validator.validateExperience(experiencia);
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate){
    	this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    	
    }
    
    public void setEndDate(Date endDate) throws DateException {
    	ValidatorJobOffer validator=  new ValidatorJobOffer();    
    	this.endDate= validator.validateEndDate(this.startDate,endDate);
    }
    
    public void setPostulations(Long postulations) {
        this.postulations = postulations;
    }
    
    public Long getPostulations() {
        return postulations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobOffer jobOffer = (JobOffer) o;

        if (!Objects.equals(id, jobOffer.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobOffer{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", location='" + location + "'" +
            ", description='" + description + "'" +
            ", experiencia='" + experiencia + "'" +
            ", startDate='" + startDate + "'" +
            ", endDate='" + endDate + "'" +
            ", postulations='" + postulations + "'" +
            '}';
    }
}
