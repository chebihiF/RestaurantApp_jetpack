package org.doronco.restaurantapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun PatientScreen() {
    val viewModel: PatientViewModel = viewModel()
    LazyColumn(){
        items(viewModel.state.value){
            patient -> PatientItem(patient)
        }
    }
}

@Composable
fun PatientItem(patient: Patient) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = patient.nom)
        Text(text = patient.dateNaissance.toString())
        Text(text = patient.score.toString())
    }
}
