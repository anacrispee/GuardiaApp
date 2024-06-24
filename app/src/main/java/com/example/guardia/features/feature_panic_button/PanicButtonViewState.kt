package com.example.guardia.features.feature_panic_button

import android.os.CountDownTimer

data class PanicButtonViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isPanicButtonVisible: Boolean = true,
    val countDownTimer: CountDownTimer? = null,
    val remainingTime: Long? = null,
    val timer: Int = 0
)