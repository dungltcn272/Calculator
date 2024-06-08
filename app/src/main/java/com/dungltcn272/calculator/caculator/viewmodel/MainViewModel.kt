package com.dungltcn272.calculator.caculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dungltcn272.calculator.caculator.utils.HandleInput
import com.dungltcn272.calculator.caculator.utils.PrefKey
import com.dungltcn272.calculator.caculator.helper.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper) :
    ViewModel() {

    var chainOfCalculations by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    var equalState by mutableStateOf(false)
        private set

    init {
        chainOfCalculations = preferenceHelper.getString(PrefKey.KEY_CHAIN, "").toString()
        result = preferenceHelper.getString(PrefKey.KEY_RESULT, "").toString()
    }

    fun addCharacter(character: Char) {
        viewModelScope.launch {
            if (equalState) {
                chainOfCalculations = result
                equalState = false
                result = ""
            }
            if (HandleInput.isAllowedToAdd(chainOfCalculations, character)) {
                if (character == '@') {
                    chainOfCalculations += "00"
                } else chainOfCalculations += character
            }
        }
    }

    fun clear() {
        viewModelScope.launch {
            chainOfCalculations = ""
            result = ""
        }
    }


    fun delete() {
        viewModelScope.launch {
            if (chainOfCalculations.isNotEmpty()) {
                chainOfCalculations = chainOfCalculations.dropLast(1)
            }
        }
    }

    fun saveChain() {
        viewModelScope.launch {
            preferenceHelper.putString(PrefKey.KEY_CHAIN, chainOfCalculations)
        }
    }

    fun calculate() {
        viewModelScope.launch {
            if (chainOfCalculations.isEmpty() || chainOfCalculations.toDoubleOrNull() != null) {
                return@launch
            }
            val chain = chainOfCalculations.replace("%", "/100")

            if (chain.last() in listOf('+', '-', 'x', '/')) {
                return@launch
            }
            HandleInput.calculate(chain).also {
                if (it != "@@") {
                    result = it
                } else {
                    return@launch
                }
            }
        }
    }

    fun equalClick() {
        viewModelScope.launch {
            if (chainOfCalculations.isEmpty() || result.isEmpty()) {
                return@launch
            }
            if (result.isNotEmpty() && !equalState) {
                equalState = true
            }
        }
    }

}