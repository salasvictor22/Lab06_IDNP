package com.example.tarjetascampus_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarjetascampus_compose.ui.theme.TarjetasCampus_ComposeTheme
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TarjetasCampus_ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AplicacionTarjetas()
                }
            }
        }
    }
}

@Composable
fun TarjetaLugar(
    nombre: String,
    descripcion: String,
    imagen: Painter,
    colorFondo: Color,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = colorFondo,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = imagen,
                contentDescription = nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = descripcion,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
fun ListaTarjetasLugar(
    onBibliotecaClick: () -> Unit = {},
    onComedorClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TarjetaLugar(
            nombre = "Biblioteca Central",
            descripcion = "Zona de estudio y préstamo de libros",
            imagen = painterResource(id = R.drawable.biblioteca),
            colorFondo = Color(0xFFE3F2FD),
            onClick = onBibliotecaClick
        )

        TarjetaLugar(
            nombre = "Comedor Universitario",
            descripcion = "Servicio de alimentación para estudiantes",
            imagen = painterResource(id = R.drawable.comedor),
            colorFondo = Color(0xFFFFF3E0),
            onClick = onComedorClick
        )
    }
}

@Composable
fun TarjetaCompacta(
    nombre: String,
    imagen: Painter,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = imagen,
            contentDescription = nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.35f))
        )

        Text(
            text = nombre,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}


@Composable
fun TarjetaDestacada(
    nombre: String,
    descripcion: String,
    imagen: Painter,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
    ) {

        Image(
            painter = imagen,
            contentDescription = nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.40f))
        )

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFFFC107),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Text(
                text = "DESTACADO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                )
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = nombre,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = descripcion,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun PantallaTarjetas(
    onBibliotecaClick: () -> Unit = {},
    onComedorClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ListaTarjetasLugar(
            onBibliotecaClick = onBibliotecaClick,
            onComedorClick = onComedorClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        TarjetaCompacta(
            nombre = "Biblioteca Central",
            imagen = painterResource(id = R.drawable.biblioteca),
            onClick = onBibliotecaClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        TarjetaCompacta(
            nombre = "Comedor Universitario",
            imagen = painterResource(id = R.drawable.comedor),
            onClick = onComedorClick
        )
    }
}


@Composable
fun EdificiosScreen(
    onBibliotecaClick: () -> Unit = {},
    onComedorClick: () -> Unit = {},
    onSistemasClick: () -> Unit = {},
    onIngenieriasClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Edificios del campus",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        TarjetaLugar(
            nombre = "Biblioteca Central",
            descripcion = "Zona de estudio y préstamo de libros",
            imagen = painterResource(id = R.drawable.biblioteca),
            colorFondo = Color(0xFFE3F2FD),
            onClick = onBibliotecaClick
        )
        TarjetaLugar(
            nombre = "Comedor Universitario",
            descripcion = "Servicio de alimentación para estudiantes",
            imagen = painterResource(id = R.drawable.comedor),
            colorFondo = Color(0xFFFFF3E0),
            onClick = onComedorClick
        )
        TarjetaLugar(
            nombre = "Escuela de Ingeniería de Sistemas",
            descripcion = "Ambientes académicos para estudiantes de Sistemas",
            imagen = painterResource(id = R.drawable.biblioteca),
            colorFondo = Color(0xFFE8F5E9),
            onClick = onSistemasClick
        )
        TarjetaLugar(
            nombre = "Pabellón de Ingenierías",
            descripcion = "Ambientes de formación para carreras de ingeniería",
            imagen = painterResource(id = R.drawable.comedor),
            colorFondo = Color(0xFFF3E5F5),
            onClick = onIngenieriasClick
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Lugar destacado",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        TarjetaDestacada(
            nombre = "Biblioteca Central",
            descripcion = "Zona de estudio y préstamo de libros",
            imagen = painterResource(id = R.drawable.biblioteca),
            onClick = onBibliotecaClick
        )
    }
}


@Composable
fun DetalleLugarScreen(
    nombre: String,
    descripcion: String,
    imagen: Painter
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Detalle del lugar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        TarjetaLugar(
            nombre = nombre,
            descripcion = descripcion,
            imagen = imagen,
            colorFondo = Color(0xFFE3F2FD)
        )
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFF5F5F5),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = imagen,
                    contentDescription = nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Text(
                    text = nombre,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = descripcion,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
fun AplicacionTarjetas() {

    var lugarSeleccionado by remember {
        mutableStateOf<String?>(null)
    }
    when (lugarSeleccionado) {

        "biblioteca" -> {
            DetalleLugarScreen(
                nombre = "Biblioteca Central",
                descripcion = "Zona de estudio y préstamo de libros",
                imagen = painterResource(id = R.drawable.biblioteca)
            )
        }
        "comedor" -> {
            DetalleLugarScreen(
                nombre = "Comedor Universitario",
                descripcion = "Servicio de alimentación para estudiantes",
                imagen = painterResource(id = R.drawable.comedor)
            )
        }
        "sistemas" -> {
            DetalleLugarScreen(
                nombre = "Escuela de Ingeniería de Sistemas",
                descripcion = "Ambientes académicos para estudiantes de Sistemas",
                imagen = painterResource(id = R.drawable.biblioteca)
            )
        }
        "ingenierias" -> {
            DetalleLugarScreen(
                nombre = "Pabellón de Ingenierías",
                descripcion = "Ambientes de formación para carreras de ingeniería",
                imagen = painterResource(id = R.drawable.comedor)
            )
        }

        else -> {
            EdificiosScreen(
                onBibliotecaClick = {
                    lugarSeleccionado = "biblioteca"
                },
                onComedorClick = {
                    lugarSeleccionado = "comedor"
                },
                onSistemasClick = {
                    lugarSeleccionado = "sistemas"
                },
                onIngenieriasClick = {
                    lugarSeleccionado = "ingenierias"
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PantallaTarjetasPreview() {
    TarjetasCampus_ComposeTheme {
        PantallaTarjetas()
    }
}



@Preview(showBackground = true)
@Composable
fun DetalleLugarScreenPreview() {
    TarjetasCampus_ComposeTheme {
        DetalleLugarScreen(
            nombre = "Biblioteca Central",
            descripcion = "Zona de estudio y préstamo de libros",
            imagen = painterResource(id = R.drawable.biblioteca)
        )
    }
}