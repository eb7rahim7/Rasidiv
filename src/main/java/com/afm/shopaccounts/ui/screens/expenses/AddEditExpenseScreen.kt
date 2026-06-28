package com.afm.shopaccounts.ui.screens.expenses

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
import com.afm.shopaccounts.data.entity.Expense
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.viewmodel.ExpenseViewModel

@Composable
fun AddEditExpenseScreen(
    navController: NavHostController,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val amount = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "إضافة / تعديل مصروف", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(amount.value, { amount.value = it }, Modifier.fillMaxWidth(), label = { Text("المبلغ") })
            OutlinedTextField(description.value, { description.value = it }, Modifier.fillMaxWidth(), label = { Text("الوصف") })
            OutlinedTextField(notes.value, { notes.value = it }, Modifier.fillMaxWidth(), label = { Text("ملاحظات") })
            Button(
                onClick = {
                    viewModel.saveExpense(
                        Expense(
                            amount = amount.value.toDoubleOrNull() ?: 0.0,
                            description = description.value,
                            notes = notes.value
                        )
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("حفظ")
            }
        }
    }
}