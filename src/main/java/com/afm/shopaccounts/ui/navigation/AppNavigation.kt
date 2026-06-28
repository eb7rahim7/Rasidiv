package com.afm.shopaccounts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.afm.shopaccounts.ui.screens.backup.BackupScreen
import com.afm.shopaccounts.ui.screens.cashregister.CashRegisterScreen
import com.afm.shopaccounts.ui.screens.customers.*
import com.afm.shopaccounts.ui.screens.dashboard.DashboardScreen
import com.afm.shopaccounts.ui.screens.employees.*
import com.afm.shopaccounts.ui.screens.expenses.*
import com.afm.shopaccounts.ui.screens.login.LoginScreen
import com.afm.shopaccounts.ui.screens.products.*
import com.afm.shopaccounts.ui.screens.reports.ReportsScreen
import com.afm.shopaccounts.ui.screens.sales.*
import com.afm.shopaccounts.ui.screens.settings.SettingsScreen
import com.afm.shopaccounts.ui.screens.suppliers.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }

        // Suppliers
        composable(Screen.Suppliers.route) {
            SuppliersScreen(navController = navController)
        }
        composable(
            Screen.AddEditSupplier.route,
            arguments = listOf(navArgument("supplierId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            AddEditSupplierScreen(navController = navController)
        }
        composable(
            Screen.SupplierStatement.route,
            arguments = listOf(navArgument("supplierId") { type = NavType.LongType })
        ) {
            SupplierStatementScreen(navController = navController)
        }
        composable(
            Screen.PurchaseInvoice.route,
            arguments = listOf(navArgument("supplierId") { type = NavType.LongType })
        ) {
            PurchaseInvoiceScreen(navController = navController)
        }

        // Products
        composable(Screen.Products.route) {
            ProductsScreen(navController = navController)
        }
        composable(
            Screen.AddEditProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            AddEditProductScreen(navController = navController)
        }

        // Sales
        composable(Screen.Sales.route) {
            SalesScreen(navController = navController)
        }
        composable(Screen.CreateSaleInvoice.route) {
            SaleInvoiceScreen(navController = navController)
        }
        composable(
            Screen.SaleInvoiceDetail.route,
            arguments = listOf(navArgument("invoiceId") { type = NavType.LongType })
        ) {
            SaleInvoiceDetailScreen(navController = navController)
        }

        // Customers
        composable(Screen.Customers.route) {
            CustomersScreen(navController = navController)
        }
        composable(
            Screen.AddEditCustomer.route,
            arguments = listOf(navArgument("customerId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            AddEditCustomerScreen(navController = navController)
        }
        composable(
            Screen.CustomerStatement.route,
            arguments = listOf(navArgument("customerId") { type = NavType.LongType })
        ) {
            CustomerStatementScreen(navController = navController)
        }

        // Employees
        composable(Screen.Employees.route) {
            EmployeesScreen(navController = navController)
        }
        composable(
            Screen.AddEditEmployee.route,
            arguments = listOf(navArgument("employeeId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            AddEditEmployeeScreen(navController = navController)
        }
        composable(
            Screen.EmployeeTransactions.route,
            arguments = listOf(navArgument("employeeId") { type = NavType.LongType })
        ) {
            EmployeeTransactionsScreen(navController = navController)
        }

        // Cash Register
        composable(Screen.CashRegister.route) {
            CashRegisterScreen(navController = navController)
        }

        // Expenses
        composable(Screen.Expenses.route) {
            ExpensesScreen(navController = navController)
        }
        composable(
            Screen.AddEditExpense.route,
            arguments = listOf(navArgument("expenseId") { type = NavType.LongType; defaultValue = -1L })
        ) {
            AddEditExpenseScreen(navController = navController)
        }
        composable(Screen.ExpenseCategories.route) {
            ExpenseCategoriesScreen(navController = navController)
        }

        // Reports
        composable(Screen.Reports.route) {
            ReportsScreen(navController = navController)
        }

        // Settings
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        // Backup
        composable(Screen.Backup.route) {
            BackupScreen(navController = navController)
        }
    }
}
