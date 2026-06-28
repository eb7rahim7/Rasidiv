package com.afm.shopaccounts.ui.screens.sales

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Product
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.viewmodel.SaleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaleInvoiceScreen(
    navController: NavHostController,
    viewModel: SaleViewModel = hiltViewModel()
) {
    val state by viewModel.formState.collectAsStateWithLifecycle()
    val products by viewModel.products.collectAsStateWithLifecycle()
    
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var qty by remember { mutableStateOf("1") }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "فاتورة بيع جديدة",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Product Selection
            Text("اختر صنف:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products.take(5)) { product ->
                    OutlinedCard(
                        onClick = { selectedProduct = product },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = if (selectedProduct?.id == product.id) 
                                MaterialTheme.colorScheme.primaryContainer 
                            else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text(product.name, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = qty,
                    onValueChange = { qty = it },
                    modifier = Modifier.width(100.dp),
                    label = { Text("الكمية") }
                )
                
                Button(
                    onClick = {
                        selectedProduct?.let {
                            viewModel.addItem(it, qty.toDoubleOrNull() ?: 1.0)
                            qty = "1"
                            selectedProduct = null
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = selectedProduct != null
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("إضافة")
                }
            }

            // Items List
            Text("الأصناف المختارة:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.items.asReversed()) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.productName)
                                Text("${item.qty} × ${formatCurrency(item.price)}")
                            }
                            Text(formatCurrency(item.total))
                        }
                    }
                }
            }

            // Summary and Save
            val total = state.items.sumOf { it.total }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Divider()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("الإجمالي", style = MaterialTheme.typography.titleLarge)
                    Text(formatCurrency(total), style = MaterialTheme.typography.titleLarge)
                }
                
                Button(
                    onClick = {
                        viewModel.createInvoice()
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.items.isNotEmpty()
                ) {
                    Text("حفظ الفاتورة")
                }
            }
        }
    }
}
