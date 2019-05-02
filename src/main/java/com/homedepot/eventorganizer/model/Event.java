package com.homedepot.eventorganizer.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.homedepot.eventorganizer.util.Interval;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "events")
@TypeDef(name = "interval", typeClass = Interval.class)
public class Event implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(value = "event_id")
    private Long eventId;

    @JsonProperty(value = "event_name")
    private String eventName;

    @JsonProperty(value = "event_date")
    private LocalDate eventDate;

    @JsonProperty(value = "event_duration")
    @Type(type = "interval")
    //private PGInterval eventDuration;
    private Integer eventDuration;

}


