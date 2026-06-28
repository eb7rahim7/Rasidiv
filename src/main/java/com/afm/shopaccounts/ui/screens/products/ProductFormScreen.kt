package com.afm.shopaccounts.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.afm.shopaccounts.data.entity.Product
import com.afm.shopaccounts.ui.components.AppTopBar

@Composable
fun ProductFormScreen(
    initialProduct: Product? = null,
    onBack: () -> Unit,
    onSave: (Product) -> Unit
) {
    var name by remember { mutableStateOf(initialProduct?.name ?: "") }
    var barcode by remember { mutableStateOf(initialProduct?.barcode ?: "") }
    var purchasePrice by remember { mutableStateOf(initialProduct?.purchasePrice?.toString() ?: "") }
    var salePrice by remember { mutableStateOf(initialProduct?.salePrice?.toString() ?: "") }
    var currentQty by remember { mutableStateOf(initialProduct?.currentQty?.toString() ?: "") }
    var minQty by remember { mutableStateOf(initialProduct?.minQty?.toString() ?: "") }
    var unit by remember { mutableStateOf(initialProduct?.unit ?: "حبة") }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (initialProduct == null) "إضافة صنف" else "تعديل صنف",
                onBackClick = onBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("اسم الصنف") })
            OutlinedTextField(value = barcode, onValueChange = { barcode = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الباركود") })
            OutlinedTextField(value = purchasePrice, onValueChange = { purchasePrice = it }, modifier = Modifier.fillMaxWidth(), label = { Text("سعر الشراء") })
            OutlinedTextField(value = salePrice, onValueChange = { salePrice = it }, modifier = Modifier.fillMaxWidth(), label = { Text("سعر البيع") })
            OutlinedTextField(value = currentQty, onValueChange = { currentQty = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الكمية الحالية") })
            OutlinedTextField(value = minQty, onValueChange = { minQty = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الحد الأدنى") })
            OutlinedTextField(value = unit, onValueChange = { unit = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الوحدة") })
            Button(
                onClick = {
                    onSave(
                        Product(
                            id = initialProduct?.id ?: 0,
                            name = name,
                            barcode = barcode,
                            purchasePrice = purchasePrice.toDoubleOrNull() ?: 0.0,
                            salePrice = salePrice.toDoubleOrNull() ?: 0.0,
                            currentQty = currentQty.toDoubleOrNull() ?: 0.0,
                            minQty = minQty.toDoubleOrNull() ?: 0.0,
                            unit = unit
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank()
            ) {
                Text("حفظ")
            }
        }
    }
}