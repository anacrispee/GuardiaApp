package com.example.guardia.domain.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController

@Composable
fun Listener(
    screenContext: Context,
    navController: NavHostController,
    fromScreen: String,
    apiCalls:
) {
    LaunchedEffect(key1 = true) {
        if (isNetworkAvailable(context = screenContext).not()) {
            navController.navigate("ConnectionErrorScreen/$fromScreen")
        } else {
            return@LaunchedEffect
        }
    }
}

private fun isNetworkAvailable(context: Context) : Boolean =
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        getNetworkCapabilities(activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }
    } ?: false