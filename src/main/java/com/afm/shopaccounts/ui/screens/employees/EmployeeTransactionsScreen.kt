package com.afm.shopaccounts.ui.screens.employees

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.EmployeeTransaction
import com.afm.shopaccounts.data.entity.TransactionType
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDate
import com.afm.shopaccounts.viewmodel.EmployeeViewModel

@Composable
fun EmployeeTransactionsScreen(
    navController: NavHostController,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    val employeeId = navController.currentBackStackEntry?.arguments?.getLong("employeeId") ?: 0L
    val transactions by viewModel.getTransactions(employeeId).collectAsStateWithLifecycle(initialValue = emptyList())
    var amount by remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "حركات الموظف", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(amount, { amount = it }, Modifier.fillMaxWidth(), label = { Text("المبلغ") })
            Button(
                onClick = {
                    amount.toDoubleOrNull()?.let {
                        viewModel.addTransaction(
                            EmployeeTransaction(
                                employeeId = employeeId,
                                type = TransactionType.ALLOWANCE,
                                amount = it
                            )
                        )
                        amount = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تسجيل بدل")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(transactions) { transaction ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(transaction.type.name)
                            Text("التاريخ: ${formatDate(transaction.date)}")
                            Text("المبلغ: ${formatCurrency(transaction.amount)}")
                        }
                    }
                }
            }
        }
    }
}