package com.afm.shopaccounts.ui.screens.products

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import com.afm.shopaccounts.ui.navigation.Screen
import com.afm.shopaccounts.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(title = "الأصناف")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddEditProduct.createRoute()) }) {
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
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::setSearchQuery,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("بحث عن صنف") }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (products.isEmpty()) {
                    item { EmptyState("لا يوجد أصناف حالياً") }
                }
                items(products) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.AddEditProduct.createRoute(product.id)) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(product.name)
                                Text("الكمية: ${product.currentQty} ${product.unit}")
                                Text("السعر: ${formatCurrency(product.salePrice)}")
                            }
                            IconButton(onClick = { navController.navigate(Screen.AddEditProduct.createRoute(product.id)) }) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}
