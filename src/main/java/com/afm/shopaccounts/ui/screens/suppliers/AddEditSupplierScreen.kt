package com.afm.shopaccounts.ui.screens.suppliers

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Supplier
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.viewmodel.SupplierViewModel

@Composable
fun AddEditSupplierScreen(
    navController: NavHostController,
    viewModel: SupplierViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var taxNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "إضافة / تعديل مورد", onBackClick = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("اسم المورد") })
            OutlinedTextField(value = phone, onValueChange = { phone = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الهاتف") })
            OutlinedTextField(value = taxNumber, onValueChange = { taxNumber = it }, modifier = Modifier.fillMaxWidth(), label = { Text("الرقم الضريبي") })
            OutlinedTextField(value = address, onValueChange = { address = it }, modifier = Modifier.fillMaxWidth(), label = { Text("العنوان") })
            Button(
                onClick = {
                    viewModel.saveSupplier(
                        Supplier(name = name, phone = phone, taxNumber = taxNumber, address = address)
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank()
            ) {
                Text("حفظ")
            }
        }
    }
}