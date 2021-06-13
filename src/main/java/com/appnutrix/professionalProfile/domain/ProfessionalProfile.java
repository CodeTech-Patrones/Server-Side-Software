package com.appnutrix.professionalProfile.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "professional_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="professional_experience_description", nullable = false, length = 500)
    private String professional_experience_description;
}