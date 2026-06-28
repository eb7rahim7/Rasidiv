package com.afm.shopaccounts.ui.screens.sales

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.SaleInvoice
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.InfoRow
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.components.formatDate
import com.afm.shopaccounts.viewmodel.SaleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaleInvoiceDetailScreen(
    navController: NavHostController,
    viewModel: SaleViewModel = hiltViewModel()
) {
    val invoiceId = navController.currentBackStackEntry?.arguments?.getLong("invoiceId") ?: -1L
    var invoice by remember { mutableStateOf<SaleInvoice?>(null) }
    val items by viewModel.getInvoiceItems(invoiceId).collectAsStateWithLifecycle(emptyList())

    LaunchedEffect(invoiceId) {
        if (invoiceId != -1L) {
            invoice = viewModel.getInvoiceById(invoiceId)
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "تفاصيل الفاتورة",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        invoice?.let { inv ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        InfoRow(label = "رقم الفاتورة", value = inv.invoiceNumber)
                        InfoRow(label = "التاريخ", value = formatDate(inv.date))
                        InfoRow(label = "طريقة الدفع", value = inv.paymentMethod.name)
                        InfoRow(label = "الإجمالي", value = formatCurrency(inv.totalAmount))
                    }
                }

                Text("الأصناف", style = MaterialTheme.typography.titleMedium)

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.productName)
                                Text("${item.qty} × ${formatCurrency(item.price)}")
                            }
                            Text(formatCurrency(item.total))
                        }
                        Divider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
    }
}
