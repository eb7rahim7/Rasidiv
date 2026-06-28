package com.afm.shopaccounts.ui.screens.customers

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Customer
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.viewmodel.CustomerViewModel

@Composable
fun AddEditCustomerScreen(
    navController: NavHostController,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val customerId = navController.currentBackStackEntry?.arguments?.getLong("customerId") ?: -1L
    
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var creditLimit by remember { mutableStateOf("") }

    LaunchedEffect(customerId) {
        if (customerId != -1L) {
            viewModel.getCustomerById(customerId)?.let { customer ->
                name = customer.name
                phone = customer.phone
                notes = customer.notes
                creditLimit = customer.creditLimit.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (customerId == -1L) "إضافة عميل" else "تعديل عميل",
                onBackClick = { navController.popBackStack() }
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
            OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("اسم العميل") })
            OutlinedTextField(value = phone, onValueChange = { phone = it }, modifier = Modifier.fillMaxWidth(), label = { Text("رقم الهاتف") })
            OutlinedTextField(value = creditLimit, onValueChange = { creditLimit = it }, modifier = Modifier.fillMaxWidth(), label = { Text("حد الائتمان") })
            OutlinedTextField(value = notes, onValueChange = { notes = it }, modifier = Modifier.fillMaxWidth(), label = { Text("ملاحظات") }, minLines = 3)
            
            Button(
                onClick = {
                    viewModel.saveCustomer(
                        Customer(
                            id = if (customerId == -1L) 0 else customerId,
                            name = name,
                            phone = phone,
                            notes = notes,
                            creditLimit = creditLimit.toDoubleOrNull() ?: 0.0
                        )
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
