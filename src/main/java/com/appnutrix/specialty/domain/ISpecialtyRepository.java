package com.appnutrix.specialty.domain;

import com.appnutrix.specialty.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, Integer> {
    public List<Specialty> findByInstitutionName(String institution_name);

}