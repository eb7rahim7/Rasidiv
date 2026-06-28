package com.afm.shopaccounts.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.afm.shopaccounts.data.entity.StoreInfo
import com.afm.shopaccounts.ui.components.AppTopBar
import com.afm.shopaccounts.ui.navigation.Screen
import com.afm.shopaccounts.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val storeInfo by viewModel.storeInfo.collectAsStateWithLifecycle()
    var storeName by remember(storeInfo) { mutableStateOf(storeInfo?.storeName ?: "") }
    var phone by remember(storeInfo) { mutableStateOf(storeInfo?.phone ?: "") }
    var address by remember(storeInfo) { mutableStateOf(storeInfo?.address ?: "") }
    var taxNumber by remember(storeInfo) { mutableStateOf(storeInfo?.taxNumber ?: "") }

    Scaffold(topBar = { AppTopBar(title = "الإعدادات", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("معلومات المتجر")
                    OutlinedTextField(storeName, { storeName = it }, Modifier.fillMaxWidth(), label = { Text("اسم المتجر") })
                    OutlinedTextField(phone, { phone = it }, Modifier.fillMaxWidth(), label = { Text("الهاتف") })
                    OutlinedTextField(address, { address = it }, Modifier.fillMaxWidth(), label = { Text("العنوان") })
                    OutlinedTextField(taxNumber, { taxNumber = it }, Modifier.fillMaxWidth(), label = { Text("الرقم الضريبي") })
                    Button(
                        onClick = {
                            viewModel.updateStoreInfo(
                                StoreInfo(
                                    storeName = storeName,
                                    phone = phone,
                                    address = address,
                                    taxNumber = taxNumber
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("حفظ المعلومات")
                    }
                }
            }
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("حول النظام")
                    Text("التطبيق عبارة عن تطبيق حسابات مصغر لإدارة المحل.")
                    Text("المطور: AFM")
                    Text("للتواصل: afmfad@hotmail.com")
                }
            }
            Button(onClick = { navController.navigate(Screen.Backup.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("النسخ الاحتياطي والتصدير")
            }
            Button(onClick = {
                navController.navigate("login") {
                    popUpTo(0)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("تسجيل الخروج")
            }
        }
    }
}