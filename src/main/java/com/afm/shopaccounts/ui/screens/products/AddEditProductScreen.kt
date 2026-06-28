package com.afm.shopaccounts.ui.screens.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.Product
import com.afm.shopaccounts.viewmodel.ProductViewModel

@Composable
fun AddEditProductScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val productId = navController.currentBackStackEntry?.arguments?.getLong("productId") ?: -1L
    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(productId != -1L) }

    LaunchedEffect(productId) {
        if (productId != -1L) {
            product = viewModel.getProductById(productId)
            isLoading = false
        }
    }

    if (!isLoading) {
        ProductFormScreen(
            initialProduct = product,
            onBack = { navController.popBackStack() },
            onSave = {
                viewModel.saveProduct(it)
                navController.popBackStack()
            }
        )
    }
}
