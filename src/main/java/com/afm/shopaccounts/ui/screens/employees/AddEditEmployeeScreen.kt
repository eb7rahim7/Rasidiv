package com.afm.shopaccounts.ui.screens.employees

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Employee
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.viewmodel.EmployeeViewModel

@Composable
fun AddEditEmployeeScreen(
    navController: NavHostController,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    val name = remember { mutableStateOf("") }
    val salary = remember { mutableStateOf("") }
    val allowance = remember { mutableStateOf("") }
    val profession = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "إضافة / تعديل موظف", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(name.value, { name.value = it }, Modifier.fillMaxWidth(), label = { Text("اسم الموظف") })
            OutlinedTextField(salary.value, { salary.value = it }, Modifier.fillMaxWidth(), label = { Text("الراتب") })
            OutlinedTextField(allowance.value, { allowance.value = it }, Modifier.fillMaxWidth(), label = { Text("البدل") })
            OutlinedTextField(profession.value, { profession.value = it }, Modifier.fillMaxWidth(), label = { Text("المهنة") })
            OutlinedTextField(notes.value, { notes.value = it }, Modifier.fillMaxWidth(), label = { Text("ملاحظات") })
            Button(
                onClick = {
                    viewModel.saveEmployee(
                        Employee(
                            name = name.value,
                            salary = salary.value.toDoubleOrNull() ?: 0.0,
                            allowance = allowance.value.toDoubleOrNull() ?: 0.0,
                            profession = profession.value,
                            notes = notes.value
                        )
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.value.isNotBlank()
            ) {
                Text("حفظ")
            }
        }
    }
}