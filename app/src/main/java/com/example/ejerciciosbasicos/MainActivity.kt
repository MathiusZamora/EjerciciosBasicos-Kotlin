package com.example.ejerciciosbasicos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciosbasicos.ui.theme.EjerciciosbasicosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosbasicosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}



@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") {
            MainMenu(
                modifier = modifier,
                onNavigateToNotas = { navController.navigate("notas") }
            )
        }
        composable("notas") {
            Notass(modifier = modifier)
        }
    }
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    onNavigateToNotas: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Menú Principal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNavigateToNotas
        ) {
            Text(text = "Calcular Promedio de Notas")
        }

    }
}


//PRIMER EJERCICIO TERMINADO
@Composable
fun Notass(modifier: Modifier = Modifier) {
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var notaf by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calcular nota final de estudiante: ")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "NOTA 1: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nota1,
            onValueChange = { nota1 = it },
            label = { Text("Ingresa nota 1") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "NOTA 2: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nota2,
            onValueChange = { nota2 = it },
            label = { Text("Ingresa nota 2") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "NOTA 3: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nota3,
            onValueChange = { nota3 = it },
            label = { Text("Ingresa nota 3") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { notaf = true }
        ) {
            Text(text = "Nota Final")
        }

        if (nota1.isNotEmpty() && nota2.isNotEmpty() && nota3.isNotEmpty() && notaf) {
            Spacer(modifier = Modifier.height(8.dp))
            var notaf = nota1.toDouble() + nota2.toDouble() + nota3.toDouble() / 3
            Text(text = "Su nota final es: $notaf")
        } else {
            Text(text = "Ingrese las 3 notas")
        }
    }
}



