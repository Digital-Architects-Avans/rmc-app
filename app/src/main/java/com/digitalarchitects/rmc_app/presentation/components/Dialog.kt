package com.digitalarchitects.rmc_app.presentation.components

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