package com.kontial.cloud.service.cloudservice.model;

import jakarta.persistence.Entity;
import lombok.Data;



@Entity
@Data
public final class Person {
    private final String id;
    private final String name;

}
