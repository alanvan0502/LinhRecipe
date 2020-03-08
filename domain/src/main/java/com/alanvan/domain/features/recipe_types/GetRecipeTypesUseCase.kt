package com.alanvan.domain.features.recipe_types

import com.alanvan.domain.base.BaseUseCase
import com.alanvan.domain.model.recipe_type.RecipeType
import com.alanvan.domain.repository.RecipeRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetRecipeTypesUseCase(
    private val repository: RecipeRepository,
    private val executionScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler
) : BaseUseCase<List<RecipeType>, GetRecipeTypesUseCase.Params>() {

    override fun execute(params: Params?): Observable<List<RecipeType>> {
        requireNotNull(params)
        return repository.getRecipeTypes(params.token)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
            .toObservable()
    }

    data class Params(
        val token: String?
    )
}