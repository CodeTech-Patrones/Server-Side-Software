package com.appnutrix.professionalProfile.infrastructure.persistance;

import com.appnutrix.professionalProfile.domain.ProfessionalProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface IProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Integer> {

}
