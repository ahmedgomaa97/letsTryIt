package com.newcore.myformvalidation.formfield

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import com.newcore.myformvalidation.ValidatorResult
import com.newcore.myformvalidation.enums.CheckFieldsMode

abstract class BaseFormField<V : TextView, D>(
    @IdRes val id: Int,
    var isOptional: Boolean = false,
) {
    abstract fun initListeners()
    abstract fun getValue(): D
    protected abstract fun checkForErrors(value: D)

    var message: ValidatorResult = ValidatorResult.Success()

    open fun isValid(): Boolean {
        checkForErrors(getValue())
        displayErrorMessage()
        return message is ValidatorResult.Success
    }

    fun setShowErrorState(checkFieldsMode: CheckFieldsMode) {
        showError = when (checkFieldsMode) {
            CheckFieldsMode.Always -> true
            CheckFieldsMode.AfterFirstSubmit, CheckFieldsMode.EverySubmit -> false
        }
    }

    open fun updateView(layoutView: View) {
        field = layoutView.findViewById(id)
        initListeners()
    }

    lateinit var field: V

    protected fun displayErrorMessage() {
        if (showError)
            field.error = message.stringMessage
    }

    private var showError: Boolean = false
        set(value) {
            field = value
            checkForErrors(getValue())
            displayErrorMessage()
        }
}