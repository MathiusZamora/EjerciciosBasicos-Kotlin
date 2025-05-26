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
                onNavigateToInss = { navController.navigate("inss")},
                onNavigateToLetters = { navController.navigate("lett")},
                onNavigateToCalCap = { navController.navigate("calcap")}
            )
        }
        composable("notas") {
            Notass(modifier = modifier)
        }
        composable("iva") { Iva(modifier = modifier) }
        composable("inss") { Inss(modifier = modifier) }
        composable("lett") { letters(modifier = modifier) }
        composable("calcap") { CalcularCapitalContable(modifier = modifier) }

    }
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    onNavigateToNotas: () -> Unit,
    onNavigateToIva: () -> Unit,
    onNavigateToInss: () -> Unit,
    onNavigateToLetters: () -> Unit,
    onNavigateToCalCap: () -> Unit
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

        Button(
            onClick = onNavigateToLetters
        ) {
            Text(text = "Calcular letras en una frase")
        }

        Button(
            onClick = onNavigateToCalCap
        ) {
            Text(text = "Calcular Capital Contable")
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
            val promedio = try {
                (nota1.toDouble() + nota2.toDouble() + nota3.toDouble()) / 3.0
            } catch (e: NumberFormatException) {
                -1.0 // Indicador de error
            }
            if (promedio >= 0) {
                Text(text = "Su nota final es: %.2f".format(promedio))
            } else {
                Text(text = "Por favor, ingrese solo números")
            }
        } else {
            Text(text = "Por favor, ingrese las 3 notas")
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
            val iva = try {
                producto.toDouble() * 0.15
            } catch (e: NumberFormatException) {
                -1.0 // Indicador de error
            }
            if (iva >= 0) {
                val totalConIva = producto.toDouble() + iva
                Text(text = "El IVA del producto es: %.2f".format(iva))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "El valor total con IVA incluido es: %.2f".format(totalConIva))
            } else {
                Text(text = "Por favor, ingrese solo números")
            }
        } else {
            Text(text = "Por favor, ingrese el precio de un producto")
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

//CUARTO EJERCICIO TERMINADO
@Composable
fun letters(modifier: Modifier = Modifier) {
    var frase by remember { mutableStateOf("") }
    var mostrarResultado by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contar letras de una frase: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Text(text = "Escribe una frase o palabra: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = frase,
            onValueChange = { frase = it },
            label = { Text("Ingresa una frase") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { mostrarResultado = true }
        ) {
            Text(text = "Contar Letras")
        }

        if (frase.isNotEmpty() && mostrarResultado) {
            Spacer(modifier = Modifier.height(8.dp))
            val conteoLetras = frase.count { it.isLetter() }
            Text(text = "La frase tiene $conteoLetras letras")
        } else {
            Text(text = "No has escrito nada")
        }
    }
}


//QUINTO EJERCICIO TERMINADO
@Composable
fun CalcularCapitalContable(modifier: Modifier = Modifier) {
    var activos by remember { mutableStateOf("") }
    var pasivos by remember { mutableStateOf("") }
    var mostrarResultado by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calcular capital contable: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "ACTIVOS: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = activos,
            onValueChange = { activos = it },
            label = { Text("Ingresa los activos") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "PASIVOS: ")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = pasivos,
            onValueChange = { pasivos = it },
            label = { Text("Ingresa los pasivos") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { mostrarResultado = true }
        ) {
            Text(text = "Calcular Capital")
        }

        if (activos.isNotEmpty() && pasivos.isNotEmpty() && mostrarResultado) {
            Spacer(modifier = Modifier.height(8.dp))
            val capital = try {
                activos.toDouble() - pasivos.toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
            Text(text = "El capital contable es: %.2f".format(capital))
        } else {
            Text(text = "No has escrito nada")
        }
    }
}