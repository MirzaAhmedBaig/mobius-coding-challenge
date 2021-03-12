package com.mobius.coding_challenge.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobius.coding_challenge.data.repositories.DataRepository
import com.mobius.coding_challenge.utils.ResourceManager
import javax.inject.Inject

class BaseViewModelFactory @Inject constructor(
    private val dataRepository: DataRepository,
    private val resourceManager: ResourceManager
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DataRepository::class.java, ResourceManager::class.java)
            .newInstance(dataRepository, resourceManager)
    }
}