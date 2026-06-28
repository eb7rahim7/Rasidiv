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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Product
import com.afm.shopaccounts.data.entity.PurchaseInvoice
import com.afm.shopaccounts.data.entity.PurchaseInvoiceItem
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.viewmodel.ProductViewModel
import com.afm.shopaccounts.viewmodel.SupplierViewModel
import com.afm.shopaccounts.viewmodel.PurchaseViewModel

@Composable
fun PurchaseInvoiceScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel(),
    purchaseViewModel: PurchaseViewModel = hiltViewModel()
) {
    val supplierId = navController.currentBackStackEntry?.arguments?.getLong("supplierId") ?: 0L
    val products by productViewModel.filteredProducts.collectAsStateWithLifecycle()
    val items = remember { mutableStateListOf<PurchaseInvoiceItem>() }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var qty by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "فاتورة شراء", onBackClick = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(products.take(10)) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        Button(onClick = { selectedProduct = product }, modifier = Modifier.fillMaxWidth()) {
                            Text(product.name)
                        }
                    }
                }
            }
            Text("الصنف المختار: ${selectedProduct?.name ?: "لا يوجد"}")
            OutlinedTextField(value = qty, onValueChange = { qty = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الكمية") })
            OutlinedTextField(value = price, onValueChange = { price = it }, modifier = Modifier.fillMaxWidth(), label = { Text("السعر") })
            Button(
                onClick = {
                    val product = selectedProduct ?: return@Button
                    val quantity = qty.toDoubleOrNull() ?: return@Button
                    val unitPrice = price.toDoubleOrNull() ?: return@Button
                    items.add(
                        PurchaseInvoiceItem(
                            invoiceId = 0,
                            productId = product.id,
                            productName = product.name,
                            qty = quantity,
                            price = unitPrice,
                            total = quantity * unitPrice
                        )
                    )
                    qty = ""
                    price = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("إضافة للفاتورة")
            }
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(item.productName)
                            Text(formatCurrency(item.total))
                        }
                    }
                }
            }
            Button(
                onClick = {
                    val total = items.sumOf { it.total }
                    purchaseViewModel.createInvoice(
                        PurchaseInvoice(
                            supplierId = supplierId,
                            invoiceNumber = "",
                            totalAmount = total
                        ),
                        items.toList()
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = items.isNotEmpty()
            ) {
                Text("حفظ الفاتورة")
            }
        }
    }
}