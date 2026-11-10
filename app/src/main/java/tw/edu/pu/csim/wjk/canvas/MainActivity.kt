package tw.edu.pu.csim.wjk.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import tw.edu.pu.csim.wjk.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {
    var CanvasW by remember { mutableStateOf(0f) }
    var CanvasH by remember { mutableStateOf(0f) }

    val minionsImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.minions)

    var minionX by remember { mutableStateOf(5f) }
    var minionY by remember { mutableStateOf(0f) }

    var gameOver by remember { mutableStateOf(false) }

    LaunchedEffect(gameOver) {
        while (!gameOver) {
            delay(50L)
            minionX += 15f
            minionY += 30f

            if ((CanvasW > 0f && minionX > CanvasW) || (CanvasH > 0f && minionY > CanvasH)) {
                minionX = 0f
                minionY = 0f
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = Color.Magenta,
                radius = 100f,
                center = Offset(x = 100f, y = 100f)
            )

            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 300f, y = 200f),
                size = Size(width = 200f, height = 200f),
                style = Stroke(width = 5f)
            )

            CanvasW = size.width
            CanvasH = size.height

            drawLine(
                color = Color.Red,
                start = Offset(x = 200f, y = 800f),
                end = Offset(x = size.width, y = size.height),
                strokeWidth = 10f
            )

            translate(left = minionX, top = minionY) {
                drawImage(minionsImage)
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "畫布寬度 $CanvasW, 長度 $CanvasH"
            )
            Button(
                onClick = { gameOver = !gameOver }
            ) {
                if (gameOver) {
                    Text(text = "開始")
                } else {
                    Text(text = "暫停")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CanvasScreenPreview() {
    CanvasTheme {
        CanvasScreen()
    }
}