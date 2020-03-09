package com.alanvan.domain.features.recipe_details

import com.alanvan.domain.base.BaseUseCase
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.domain.repository.RecipeRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetRecipeDetailsUseCase(
    private val repository: RecipeRepository,
    private val executionScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler
) : BaseUseCase<RecipeDetails, GetRecipeDetailsUseCase.Params>() {

    override fun execute(params: Params?): Observable<RecipeDetails> {
        requireNotNull(params)
        return repository.getRecipe(params.token, params.recipeId)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
            .toObservable()
    }

    data class Params(
        val token: String?,
        val recipeId: String
    )
}