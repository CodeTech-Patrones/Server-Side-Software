package com.appnutrix.professionalProfile.domain;

import com.appnutrix.specialty.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessionalSpecialtiesRepository
        extends JpaRepository<ProfessionalSpecialties, ProfessionalSpecialtiesFK>{

    @Query("Select b.specialty from ProfessionalSpecialties b where b.professionalProfile.id = :professional_profile_id")
    public List<Specialty> findByProfessionalProfileId(@Param("professional_profile_id") Integer professional_profile_id);
}