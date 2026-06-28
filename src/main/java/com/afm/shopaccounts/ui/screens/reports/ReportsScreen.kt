package com.afm.shopaccounts.ui.screens.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.afm.shopaccounts.viewmodel.ReportsViewModel

@Composable
fun ReportsScreen(
    navController: NavHostController,
    viewModel: ReportsViewModel = hiltViewModel()
) {
    val totalSales by viewModel.totalSales.collectAsStateWithLifecycle()
    val totalPurchases by viewModel.totalPurchases.collectAsStateWithLifecycle()
    val totalExpenses by viewModel.totalExpenses.collectAsStateWithLifecycle()
    val totalProfit by viewModel.totalProfit.collectAsStateWithLifecycle()

    Scaffold(topBar = { AppTopBar(title = "التقارير", onBackClick = { navController.popBackStack() }) }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("إجمالي المبيعات: ${formatCurrency(totalSales)}")
                        Text("إجمالي المشتريات: ${formatCurrency(totalPurchases)}")
                        Text("إجمالي المصروفات: ${formatCurrency(totalExpenses)}")
                        Text("إجمالي الأرباح: ${formatCurrency(totalProfit)}")
                    }
                }
            }
        }
    }
}