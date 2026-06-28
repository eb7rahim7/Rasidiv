package com.afm.shopaccounts.ui.screens.sales

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.afm.shopaccounts.viewmodel.SaleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesScreen(
    navController: NavHostController,
    viewModel: SaleViewModel = hiltViewModel()
) {
    val invoices by viewModel.invoices.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(title = "المبيعات")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.CreateSaleInvoice.route) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (invoices.isEmpty()) {
                    item { EmptyState("لا يوجد فواتير مبيعات حالياً") }
                }
                items(invoices) { invoice ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.SaleInvoiceDetail.createRoute(invoice.id)) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("فاتورة #${invoice.invoiceNumber}")
                                Text(formatDate(invoice.date))
                            }
                            Column {
                                Text(formatCurrency(invoice.totalAmount))
                                Text(if (invoice.isCredit) "آجل" else "نقدي")
                            }
                        }
                    }
                }
            }
        }
    }
}
