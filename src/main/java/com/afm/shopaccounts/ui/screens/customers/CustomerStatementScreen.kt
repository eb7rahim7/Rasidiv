package com.afm.shopaccounts.ui.screens.customers

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
import com.afm.shopaccounts.data.entity.CustomerPayment
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDate
import com.afm.shopaccounts.viewmodel.CustomerViewModel

@Composable
fun CustomerStatementScreen(
    navController: NavHostController,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val customerId = navController.currentBackStackEntry?.arguments?.getLong("customerId") ?: 0L
    val sales by viewModel.getCustomerSales(customerId).collectAsStateWithLifecycle(initialValue = emptyList())
    val payments by viewModel.getCustomerPayments(customerId).collectAsStateWithLifecycle(initialValue = emptyList())
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "كشف حساب العميل", onBackClick = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("مبلغ التحصيل") }
            )
            Button(
                onClick = {
                    amount.toDoubleOrNull()?.let {
                        viewModel.addCustomerPayment(CustomerPayment(customerId = customerId, amount = it))
                        amount = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تسجيل تحصيل")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(sales) { invoice ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("فاتورة بيع: ${invoice.invoiceNumber}")
                            Text("التاريخ: ${formatDate(invoice.date)}")
                            Text("الإجمالي: ${formatCurrency(invoice.totalAmount)}")
                        }
                    }
                }
                items(payments) { payment ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("تحصيل")
                            Text("التاريخ: ${formatDate(payment.date)}")
                            Text("المبلغ: ${formatCurrency(payment.amount)}")
                        }
                    }
                }
            }
        }
    }
}