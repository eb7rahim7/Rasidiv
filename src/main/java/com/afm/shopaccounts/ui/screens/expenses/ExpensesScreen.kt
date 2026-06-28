package com.afm.shopaccounts.ui.screens.expenses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.EmptyState
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDate
import com.afm.shopaccounts.ui.navigation.Screen
import com.afm.shopaccounts.viewmodel.ExpenseViewModel

@Composable
fun ExpensesScreen(
    navController: NavHostController,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val expenses by viewModel.filteredExpenses.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppTopBar(title = "المصاريف") },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddEditExpense.createRoute()) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (expenses.isEmpty()) item { EmptyState("لا توجد مصروفات") }
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.AddEditExpense.createRoute(expense.id)) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(expense.description.ifBlank { "مصروف" })
                        Text("التاريخ: ${formatDate(expense.date)}")
                        Text("المبلغ: ${formatCurrency(expense.amount)}")
                    }
                }
            }
        }
    }
}