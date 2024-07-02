package com.example.guardia.features.feature_panic_button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.guardia.R
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.KFunction1

@Composable
fun PanicButtonScreen(
    navController: NavHostController,
    viewModel: PanicButtonViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState
    val action = viewModel::dispatcherViewAction

    if (viewState.isPanicButtonVisible) {
        PanicButton(
            action = action
        )
    } else {
        CallingHelp(
            action = action,
            timer = viewState.timer
        )
        LaunchedEffect(key1 = true) {
            action(
                PanicButtonViewAction.CountDownTimer
            )
        }
    }
}

@Composable
private fun CallingHelp(
    action: (PanicButtonViewAction) -> Unit,
    timer: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.panic_button_title),
            style = AppTheme.typography.bodyBold.body_large,
            color = AppTheme.colors.primary.dark_grey,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.panic_button_subtitle),
            style = AppTheme.typography.bodyRegular.body_medium,
            color = AppTheme.colors.primary.dark_grey,
            textAlign = TextAlign.Center
        )
        if (timer > 0)
            Lottie()
        Timer(timer)
        Disclaimer()
        Buttons(
            action,
            timer
        )
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun Timer(
    timer: Int
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(8.dp),
                color = AppTheme.colors.primary.lighter_grey
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = timer.toString(),
            style = AppTheme.typography.titleBold.title_lg,
            color = AppTheme.colors.primary.dark_grey
        )
    }
}

@Composable
private fun Lottie() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    AnimatedVisibility(
        visible = (progress < 1.0f)
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(120.dp)
        )
    }
}

@Composable
private fun Buttons(
    action: (PanicButtonViewAction) -> Unit,
    timer: Int
) {
    Button(
        modifier = Modifier
            .padding(top = 16.dp),
        onClick = {
            action(
                PanicButtonViewAction.UpdateScreenState
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (timer > 0) AppTheme.colors.primary.dark_pink else AppTheme.colors.primary.lighter_pink
        ),
        enabled = (timer > 0)
    ) {
        Text(
            text = stringResource(id = R.string.panic_button_cancel_button)
        )
    }
    Button(
        modifier = Modifier
            .padding(bottom = 16.dp),
        onClick = {
            action(
                PanicButtonViewAction.UpdateScreenState
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary.dark_pink,
        )
    ) {
        Text(
            text = stringResource(id = R.string.panic_button_already_been_helped),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 24.dp)
        )
    }
}

@Composable
private fun Disclaimer() {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(8.dp),
                color = AppTheme.colors.primary.lighter_grey
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp),
            painter = painterResource(id = R.drawable.ic_shield),
            contentDescription = null,
            tint = AppTheme.colors.primary.dark_pink
        )
        Text(
            modifier = Modifier
                .padding(end = 16.dp, top = 16.dp, bottom = 16.dp),
            text = stringResource(id = R.string.panic_button_disclaimer),
            style = AppTheme.typography.bodyRegular.body_medium,
            color = AppTheme.colors.primary.dark_grey
        )
    }
}

@Composable
private fun PanicButton(action: KFunction1<PanicButtonViewAction, Unit>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.image_default_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    action(PanicButtonViewAction.UpdateScreenState)
                },
            painter = painterResource(id = R.drawable.image_emergency_button),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PanicButtonScreenPreview() {
    PanicButtonScreen(
        navController = rememberNavController()
    )
}