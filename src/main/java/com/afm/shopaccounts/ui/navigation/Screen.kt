package com.afm.shopaccounts.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Dashboard : Screen("dashboard")
    data object Suppliers : Screen("suppliers")
    data object AddEditSupplier : Screen("add_edit_supplier?supplierId={supplierId}") {
        fun createRoute(supplierId: Long = -1L) = "add_edit_supplier?supplierId=$supplierId"
    }
    data object SupplierStatement : Screen("supplier_statement/{supplierId}") {
        fun createRoute(supplierId: Long) = "supplier_statement/$supplierId"
    }
    data object PurchaseInvoice : Screen("purchase_invoice?supplierId={supplierId}") {
        fun createRoute(supplierId: Long) = "purchase_invoice?supplierId=$supplierId"
    }
    data object Products : Screen("products")
    data object AddEditProduct : Screen("add_edit_product?productId={productId}") {
        fun createRoute(productId: Long = -1L) = "add_edit_product?productId=$productId"
    }
    data object Sales : Screen("sales")
    data object CreateSaleInvoice : Screen("create_sale_invoice")
    data object SaleInvoiceDetail : Screen("sale_invoice_detail/{invoiceId}") {
        fun createRoute(invoiceId: Long) = "sale_invoice_detail/$invoiceId"
    }
    data object Customers : Screen("customers")
    data object AddEditCustomer : Screen("add_edit_customer?customerId={customerId}") {
        fun createRoute(customerId: Long = -1L) = "add_edit_customer?customerId=$customerId"
    }
    data object CustomerStatement : Screen("customer_statement/{customerId}") {
        fun createRoute(customerId: Long) = "customer_statement/$customerId"
    }
    data object Employees : Screen("employees")
    data object AddEditEmployee : Screen("add_edit_employee?employeeId={employeeId}") {
        fun createRoute(employeeId: Long = -1L) = "add_edit_employee?employeeId=$employeeId"
    }
    data object EmployeeTransactions : Screen("employee_transactions/{employeeId}") {
        fun createRoute(employeeId: Long) = "employee_transactions/$employeeId"
    }
    data object CashRegister : Screen("cash_register")
    data object Expenses : Screen("expenses")
    data object AddEditExpense : Screen("add_edit_expense?expenseId={expenseId}") {
        fun createRoute(expenseId: Long = -1L) = "add_edit_expense?expenseId=$expenseId"
    }
    data object ExpenseCategories : Screen("expense_categories")
    data object Reports : Screen("reports")
    data object Settings : Screen("settings")
    data object Backup : Screen("backup")
}
