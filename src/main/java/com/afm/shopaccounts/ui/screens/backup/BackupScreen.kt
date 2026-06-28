package com.afm.shopaccounts.ui.screens.backup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.afm.shopaccounts.ui.components.AppTopBar

@Composable
fun BackupScreen(navController: NavHostController) {
    Scaffold(topBar = { AppTopBar(title = "النسخ الاحتياطي", onBackClick = { navController.popBackStack() }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("إنشاء نسخة احتياطية JSON")
                    Text("استعادة النسخ")
                    Text("تصدير PDF / Excel")
                    Text("استيراد البيانات")
                }
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("إنشاء نسخة احتياطية")
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("تصدير PDF")
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("تصدير Excel")
            }
        }
    }
}