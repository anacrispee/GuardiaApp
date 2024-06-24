package com.example.guardia.features.feature_panic_button

sealed class PanicButtonViewAction {
    object UpdateScreenState : PanicButtonViewAction()
    object CountDownTimer : PanicButtonViewAction()
}