package com.afm.shopaccounts.ui.screens.cashregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDateTime
import com.afm.shopaccounts.viewmodel.CashRegisterViewModel

@Composable
fun CashRegisterScreen(
    navController: NavHostController,
    viewModel: CashRegisterViewModel = hiltViewModel()
) {
    val entries by viewModel.filteredEntries.collectAsStateWithLifecycle()
    val cashBalance by viewModel.cashBalance.collectAsStateWithLifecycle()
    val cardBalance by viewModel.cardBalance.collectAsStateWithLifecycle()

    Scaffold(topBar = { AppTopBar(title = "الصندوق والخزينة", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("رصيد النقد: ${formatCurrency(cashBalance)}")
                    Text("رصيد الشبكة: ${formatCurrency(cardBalance)}")
                }
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(entries) { entry ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(entry.reference.ifBlank { entry.type.name })
                            Text("التاريخ: ${formatDateTime(entry.date)}")
                            Text("المبلغ: ${formatCurrency(entry.amount)}")
                        }
                    }
                }
            }
        }
    }
}