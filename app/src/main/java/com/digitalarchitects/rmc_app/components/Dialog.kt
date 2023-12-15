package com.digitalarchitects.rmc_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIEvent
import com.digitalarchitects.rmc_app.data.editmyaccount.EditMyAccountUIState

//fun EditMyAccountDialog(
//    state: EditMyAccountUIState,
//    onEvent: (EditMyAccountUIEvent) -> Unit,
//    modifier: Modifier =  Modifier
//){
//    AlertDialog(
//        onDismissRequest = { /*TODO*/},
//        title = { Text(text = "Saved changes") },
//        text = {
//            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                TextField(value = state.firstName,
//                    onValueChange ={
//                        onEvent(EditMyAccountUIEvent.SetFirstName(it))
//                    },
//                    placeholder = {
//                        Text(text = "First Name")
//                    }
//                )
//                TextField(value = state.lastName,
//                    onValueChange ={
//                        onEvent(EditMyAccountUIEvent.SetFirstName(it))
//                    },
//                    placeholder = {
//                        Text(text = "Last Name")
//                    }
//                )
//            }
//        }
//    )
//}