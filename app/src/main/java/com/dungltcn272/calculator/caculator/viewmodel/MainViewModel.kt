package com.dungltcn272.calculator.caculator.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dungltcn272.calculator.caculator.utils.HandleInput
import com.dungltcn272.calculator.caculator.utils.PrefKey
import com.dungltcn272.calculator.caculator.utils.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun clear() {
        chainOfCalculations = ""
        result = ""
    }


    fun delete() {
        if (chainOfCalculations.isNotEmpty()) {
            chainOfCalculations = chainOfCalculations.dropLast(1)
        }
    }

    fun saveChain() {
        preferenceHelper.putString(PrefKey.KEY_CHAIN, chainOfCalculations)
    }

    fun calculate() {
        if (chainOfCalculations.isEmpty() || chainOfCalculations.toDoubleOrNull() != null) {
            Log.d("TAG", "check resume")
            return
        }
        val chain = chainOfCalculations.replace("%", "/100")

        if (chain.last() == '+' || chain.last() == '-' || chain.last() == 'x' || chain.last() == '/') {
            Log.d("TAG", "last index = +-x/")
            return
        }
        HandleInput.calculate(chain).also {
            if (it != "@@") {
                result = it
            } else {
                return
            }
        }
    }

    fun equalClick() {
        if (chainOfCalculations.isEmpty() || result.isEmpty()) {
            return
        }
        if (result.isNotEmpty() && !equalState) {
            equalState = true
        }
    }

}