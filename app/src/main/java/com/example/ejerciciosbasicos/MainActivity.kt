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
                onNavigateToNotas = { navController.navigate("notas") },
                onNavigateToIva = { navController.navigate("iva")},
                onNavigateToInss = { navController.navigate("inss")}
            )
        }
        composable("notas") {
            Notass(modifier = modifier)
        }
        composable("iva") { Iva(modifier = modifier) }
        composable("inss") { Inss(modifier = modifier) }
    }
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    onNavigateToNotas: () -> Unit,
    onNavigateToIva: () -> Unit,
    onNavigateToInss: () -> Unit
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
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNavigateToNotas
        ) {
            Text(text = "Calcular Promedio de Notas")
        }

        Button(
            onClick = onNavigateToIva
        ) {
            Text(text = "Calcular IVA de Producto")
        }

        Button(
            onClick = onNavigateToInss
        ) {
            Text(text = "Calcular deduccion INSS")
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
        Text(text = "Calcular nota final de estudiante: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

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
            Text(text = "por favor, Ingrese las 3 notas")
        }
    }
}



//SEGUNDO EJERCICIO TERMINADO
@Composable
fun Iva(modifier: Modifier = Modifier) {
    var producto by remember { mutableStateOf("") }
    //var productoiva by remember { mutableStateOf("") }
    var ivaa by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calcular IVA de un producto: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)


        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ingrese el precio del producto: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = producto,
            onValueChange = { producto = it },
            label = { Text("Precio del producto") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { ivaa = true }
        ) {
            Text(text = "Calcular IVA")
        }

        if (producto.isNotEmpty() && ivaa) {
            Spacer(modifier = Modifier.height(8.dp))
            var ivaa = producto.toDouble() * 0.15
            var productoiva = producto.toFloat() + ivaa.toFloat()
            Text(text = "El IVA del producto es: $ivaa")
            Text(text = "El Valor total con IVA incluido es: $productoiva")
        } else {
            Text(text = "por favor, Ingrese el precio de un producto")
        }
    }
}



//TERCER EJERCICIO TERMINADO
@Composable
fun Inss(modifier: Modifier = Modifier) {
    var salario by remember { mutableStateOf("") }
    var ins by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calcular INSS de un salario: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)


        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ingrese el salario a deducir: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = salario,
            onValueChange = { salario = it },
            label = { Text("salario sin deducir") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { ins = true }
        ) {
            Text(text = "Calcular INSS")
        }

        if (salario.isNotEmpty() && ins) {
            Spacer(modifier = Modifier.height(8.dp))
            var ins = salario.toDouble() * 0.07
            var deduccion = salario.toFloat() - ins.toFloat()
            Text(text = "El INSS es: $ins")
            Text(text = "El salario con deduccion inss es: $deduccion")
        } else {
            Text(text = "por favor, Ingrese el salario a deducir")
        }
    }
}