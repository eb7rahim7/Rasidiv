package com.afm.shopaccounts.ui.screens.suppliers

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
import com.afm.shopaccounts.data.entity.SupplierPayment
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDate
import com.afm.shopaccounts.viewmodel.SupplierViewModel

@Composable
fun SupplierStatementScreen(
    navController: NavHostController,
    viewModel: SupplierViewModel = hiltViewModel()
) {
    val supplierId = navController.currentBackStackEntry?.arguments?.getLong("supplierId") ?: 0L
    val payments by viewModel.getSupplierPayments(supplierId).collectAsStateWithLifecycle(initialValue = emptyList())
    val purchases by viewModel.getSupplierPurchases(supplierId).collectAsStateWithLifecycle(initialValue = emptyList())
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "كشف حساب المورد", onBackClick = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = amount, onValueChange = { amount = it }, modifier = Modifier.fillMaxWidth(), label = { Text("مبلغ السداد") })
            Button(
                onClick = {
                    amount.toDoubleOrNull()?.let {
                        viewModel.addSupplierPayment(SupplierPayment(supplierId = supplierId, amount = it))
                        amount = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تسجيل سداد")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(purchases) { invoice ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("فاتورة شراء: ${invoice.invoiceNumber}")
                            Text("التاريخ: ${formatDate(invoice.date)}")
                            Text("الإجمالي: ${formatCurrency(invoice.totalAmount)}")
                        }
                    }
                }
                items(payments) { payment ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("سداد")
                            Text("التاريخ: ${formatDate(payment.date)}")
                            Text("المبلغ: ${formatCurrency(payment.amount)}")
                        }
                    }
                }
            }
        }
    }
}