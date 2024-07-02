package com.example.guardia.features.feature_panic_button

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class PanicButtonViewModel : ViewModel(), KoinComponent {

    var viewState by mutableStateOf(PanicButtonViewState())

    fun dispatcherViewAction(action: PanicButtonViewAction) {
        when (action) {
            PanicButtonViewAction.UpdateScreenState -> updateScreenState()
            PanicButtonViewAction.CountDownTimer -> countDownToResend()
        }
    }

    private fun countDownToResend() {
        viewState.countDownTimer?.cancel()
        viewState = viewState.copy(
            countDownTimer = object : CountDownTimer(DEFAULT_SECONDS, INTERVAL_SECONDS) {
                override fun onTick(millisUntilFinished: Long) {
                    val currentTime = (millisUntilFinished / INTERVAL_SECONDS).toInt()
                    viewState = viewState.copy(
                        timer = currentTime,
                        remainingTime = millisUntilFinished.takeIf { currentTime > 0 }
                    )
                }

                override fun onFinish() {
                    viewState = viewState.copy(timer = 0)
                }
            }.start()
        )
    }

    private fun updateScreenState() {
        viewState = if (viewState.isPanicButtonVisible) {
            viewState.copy(
                isPanicButtonVisible = false
            )
        } else
            viewState.copy(
                isPanicButtonVisible = true
            )
    }

    companion object {
        const val DEFAULT_SECONDS = 120000L
        const val INTERVAL_SECONDS = 1000L
    }
}