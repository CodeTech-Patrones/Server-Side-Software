package com.appnutrix.recipe.domain;

import com.appnutrix.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {
    public List<Recipe> findAllByNutritionist(Long nutritionist) throws Exception;
    public Recipe save(Recipe recipe) throws Exception;
    void delete(Integer id) throws Exception;
    List<Recipe> getAll() throws  Exception;
    Optional<Recipe> getById(Integer id) throws Exception;
}
