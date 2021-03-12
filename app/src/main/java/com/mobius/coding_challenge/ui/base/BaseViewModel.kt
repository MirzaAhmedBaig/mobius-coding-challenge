package com.mobius.coding_challenge.ui.base

import androidx.lifecycle.ViewModel
import com.mobius.coding_challenge.data.repositories.DataRepository

abstract class BaseViewModel(val dataRepository: DataRepository) : ViewModel() {

}