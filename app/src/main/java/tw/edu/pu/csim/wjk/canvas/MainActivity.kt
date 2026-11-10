package tw.edu.pu.csim.wjk.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import tw.edu.pu.csim.wjk.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {
    var CanvasW by remember { mutableStateOf(0f) }
    var CanvasH by remember { mutableStateOf(0f) }

    // 載入圖片資源 (小小兵)
    val minionsImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.minions)

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. 繪製洋紅色的圓
            drawCircle(
                color = Color.Magenta,
                radius = 100f,
                center = Offset(x = 100f, y = 100f)
            )

            // 2. 繪製藍色空心矩形
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 300f, y = 200f),
                size = Size(width = 200f, height = 200f),
                style = Stroke(width = 5f)
            )

            // 記錄畫布寬高
            CanvasW = size.width
            CanvasH = size.height

            // 3. 繪製紅色的線
            drawLine(
                color = Color.Red,
                start = Offset(x = 200f, y = 800f),
                end = Offset(x = size.width, y = size.height),
                strokeWidth = 10f
            )

            // 4. 將小小兵圖片移至左上角 (0f, 0f)
            translate(left = 0f, top = 0f) {
                drawImage(minionsImage)
            }
        }

        // 顯示畫布寬度與長度
        Text(
            text = "畫布寬度 $CanvasW, 長度 $CanvasH",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CanvasScreenPreview() {
    CanvasTheme {
        CanvasScreen()
    }
}