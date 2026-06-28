package com.afm.shopaccounts.ui.screens.employees

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
import com.afm.shopaccounts.ui.navigation.Screen
import com.afm.shopaccounts.viewmodel.EmployeeViewModel

@Composable
fun EmployeesScreen(
    navController: NavHostController,
    viewModel: EmployeeViewModel = hiltViewModel()
) {
    val employees by viewModel.employees.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppTopBar(title = "الموظفون") },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddEditEmployee.createRoute()) }) {
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
            if (employees.isEmpty()) item { EmptyState("لا يوجد موظفون") }
            items(employees) { employee ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.EmployeeTransactions.createRoute(employee.id)) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(employee.name)
                        Text(employee.profession)
                        Text("الراتب: ${formatCurrency(employee.salary)}")
                    }
                }
            }
        }
    }
}