package com.afm.shopaccounts.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.ui.components.DashboardCard
import com.afm.shopaccounts.ui.components.GradientCard
import com.afm.shopaccounts.ui.components.SectionTitle
import com.afm.shopaccounts.ui.components.formatCurrency
import com.afm.shopaccounts.ui.navigation.Screen
import com.afm.shopaccounts.ui.theme.GradientAmberEnd
import com.afm.shopaccounts.ui.theme.GradientAmberStart
import com.afm.shopaccounts.ui.theme.GradientBlueEnd
import com.afm.shopaccounts.ui.theme.GradientBlueStart
import com.afm.shopaccounts.ui.theme.GradientCoralEnd
import com.afm.shopaccounts.ui.theme.GradientCoralStart
import com.afm.shopaccounts.ui.theme.GradientGreenEnd
import com.afm.shopaccounts.ui.theme.GradientGreenStart
import com.afm.shopaccounts.ui.theme.GradientIndigoEnd
import com.afm.shopaccounts.ui.theme.GradientIndigoStart
import com.afm.shopaccounts.ui.theme.GradientPurpleEnd
import com.afm.shopaccounts.ui.theme.GradientPurpleStart
import com.afm.shopaccounts.ui.theme.GradientRedEnd
import com.afm.shopaccounts.ui.theme.GradientRedStart
import com.afm.shopaccounts.viewmodel.DashboardViewModel

private data class DashboardMetric(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val colors: List<androidx.compose.ui.graphics.Color>
)

@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val metrics = listOf(
        DashboardMetric("إجمالي السيولة", formatCurrency(state.cashBalance + state.cardBalance), Icons.Default.Money, listOf(GradientPurpleStart, GradientPurpleEnd)),
        DashboardMetric("قيمة المخزون", formatCurrency(state.stockValue), Icons.Default.Inventory2, listOf(GradientBlueStart, GradientBlueEnd)),
        DashboardMetric("ديون الموردين", formatCurrency(state.supplierDebt), Icons.Default.Payments, listOf(GradientRedStart, GradientRedEnd)),
        DashboardMetric("إجمالي المبيعات", formatCurrency(state.totalSales), Icons.Default.PointOfSale, listOf(GradientGreenStart, GradientGreenEnd)),
        DashboardMetric("إجمالي المشتريات", formatCurrency(state.totalPurchases), Icons.Default.ShoppingCart, listOf(GradientCoralStart, GradientCoralEnd)),
        DashboardMetric("إجمالي المصروفات", formatCurrency(state.totalExpenses), Icons.Default.ReceiptLong, listOf(GradientAmberStart, GradientAmberEnd)),
        DashboardMetric("إجمالي الأرباح", formatCurrency(state.totalProfit), Icons.Default.Assessment, listOf(GradientIndigoStart, GradientIndigoEnd)),
        DashboardMetric("صافي الوضع المالي", formatCurrency(state.totalSales - state.totalPurchases - state.totalExpenses + state.cashBalance + state.cardBalance), Icons.Default.Money, listOf(GradientPurpleStart, GradientCoralEnd))
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true, onClick = { navController.navigate(Screen.Dashboard.route) }, icon = { Icon(Icons.Default.Money, null) }, label = { Text("الرئيسية") })
                NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.Sales.route) }, icon = { Icon(Icons.Default.PointOfSale, null) }, label = { Text("المبيعات") })
                NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.Products.route) }, icon = { Icon(Icons.Default.Inventory2, null) }, label = { Text("المخزون") })
                NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.Reports.route) }, icon = { Icon(Icons.Default.Assessment, null) }, label = { Text("التقارير") })
                NavigationBarItem(selected = false, onClick = { navController.navigate(Screen.Settings.route) }, icon = { Icon(Icons.Default.Settings, null) }, label = { Text("الإعدادات") })
            }
        }
    ) { padding ->

LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    item {
        GradientCard(
            modifier = Modifier.fillMaxWidth(),
            gradientColors = listOf(GradientPurpleStart, GradientCoralEnd)
        ) {
            Text(text = state.storeInfo?.storeName ?: "متجرك", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            Text(text = "أهلاً وسهلاً بك", style = MaterialTheme.typography.titleMedium, color = Color.White.copy(alpha = 0.9f))
        }
    }
    
    item { SectionTitle("ملخص الأداء") }

    // بدلاً من الـ Grid، سنستخدم عرضاً بسيطاً أو مصفوفة
    // بما أنك تريد Grid، الأفضل هو عمل صفوف (Rows) داخل الـ Column
    items(metrics.chunked(2)) { rowMetrics ->
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            for (metric in rowMetrics) {
                DashboardCard(
                    modifier = Modifier.weight(1f),
                    title = metric.title,
                    value = metric.value,
                    icon = metric.icon,
                    gradientColors = metric.colors
                )
            }
        }
    }
}
