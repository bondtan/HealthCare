package com.example.uiexample.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme
import com.google.accompanist.permissions.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermissionDialog(
    permissionState: PermissionState
) {

    val dialogState = rememberMaterialDialogState()

    if (!permissionState.status.isGranted && permissionState.status.shouldShowRationale) {
        MaterialDialog(
            dialogState = dialogState,
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            ),
            buttons = {
                positiveButton(
                    text = stringResource(id = R.string.ok),
                    onClick = {
                        permissionState.launchPermissionRequest()
                        dialogState.hide()
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_ordinary))
                ) {
                    Text(
                        text = "Health Care notification",
                        style = ExampleTheme.typography.subtitle,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Allow you to be notified " +
                                "when your blood pressure is needed",
                        style = ExampleTheme.typography.largeBody,
                    )
                }
            }
        )
    } else {
        LaunchedEffect(key1 = Unit, block = {permissionState.launchPermissionRequest()})
        dialogState.hide()
        Log.d("Notification perm", "Status: ${permissionState.status}")
    }

}