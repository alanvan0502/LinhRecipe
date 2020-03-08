package com.alanvan.repository.repository

import com.alanvan.domain.model.recipe_type.RecipeType
import com.alanvan.domain.repository.RecipeRepository
import com.alanvan.repository.data.injection.Modules
import com.alanvan.repository.data.model.recipe_types.RecipeTypesMapper
import com.alanvan.repository.service.LSService
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class RecipeAPIRepository : RecipeRepository, KodeinAware {
    override val kodein: Kodein = Modules.container

    private val service: LSService by instance()

    override fun getRecipeTypes(token: String?): Single<List<RecipeType>> {
        return service.getRecipeTypes("recipe_types.get", "json", token ?: "")
            .map { RecipeTypesMapper().map(it) }
    }
}