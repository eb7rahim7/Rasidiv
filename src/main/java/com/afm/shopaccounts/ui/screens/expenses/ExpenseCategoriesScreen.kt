package com.afm.shopaccounts.ui.screens.expenses

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
import com.afm.shopaccounts.data.entity.ExpenseCategory
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.viewmodel.ExpenseViewModel

@Composable
fun ExpenseCategoriesScreen(
    navController: NavHostController,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "تصنيفات المصروفات", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(name, { name = it }, Modifier.fillMaxWidth(), label = { Text("اسم التصنيف") })
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        viewModel.saveCategory(ExpenseCategory(name = name))
                        name = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("إضافة تصنيف")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { category ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(category.name)
                        }
                    }
                }
            }
        }
    }
}