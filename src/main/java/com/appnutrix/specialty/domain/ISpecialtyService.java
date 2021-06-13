package com.appnutrix.specialty.domain;

import com.appnutrix.specialty.domain.Specialty;

import java.util.List;


public interface ISpecialtyService /*extends CrudService<Specialty>*/{

    public List<Specialty> findByInstitutionName(String institution_name) throws Exception;
}
