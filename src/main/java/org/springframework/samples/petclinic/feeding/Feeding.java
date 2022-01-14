package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.Visit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedings")
public class Feeding {
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
	
	
	@NotNull
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate startDate;
	
	@NotNull
	@Min(1)
	@Column(name = "weeks_duration")
    double weeksDuration;
    
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_id")
	Pet pet;  
	
	@ManyToOne
	@JoinColumn(name = "feeding_type_id")
	FeedingType feedingType;

	
}
