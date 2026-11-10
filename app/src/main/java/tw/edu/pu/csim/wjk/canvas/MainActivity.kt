package tw.edu.pu.csim.wjk.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.wjk.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestCanvas(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TestCanvas(modifier: Modifier = Modifier) {
    // 載入圖片資源 (小小兵)
    val minionsImage: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.minions)

    // 建立一個新的路徑物件
    val path = Path().apply {
        moveTo(50f, 100f)   // 移動到起始點 (50f, 100f)
        lineTo(200f, 500f)  // 畫一條線到 (200f, 500f)
        lineTo(450f, 400f)  // 畫一條線到 (450f, 400f)
        close()             // 閉合路徑
    }

    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. 繪製洋紅色的圓, 半徑 100, 圓心 (100,100)
        drawCircle(
            color = Color.Magenta,
            radius = 100f,
            center = Offset(100f, 100f)
        )

        // 2. 繪製藍色空心矩形, 左上角 (300,200), 寬度與高度 200
        drawRect(
            color = Color.Blue,
            topLeft = Offset(300f, 200f),
            size = Size(200f, 200f),
            style = Stroke(width = 5f)
        )

        // 3. 繪製紅色的線，從 (200,800) 畫到右下角，線條粗細 10
        drawLine(
            color = Color.Red,
            start = Offset(200f, 800f),
            end = Offset(size.width, size.height),
            strokeWidth = 10f
        )

        // 4. 計算小小兵圖片置中位置並繪製
        val minionsWidth = minionsImage.width
        val minionsHeight = minionsImage.height
        val offsetX = (size.width - minionsWidth) / 2f
        val offsetY = (size.height - minionsHeight) / 2f

        translate(offsetX, offsetY) {
            drawImage(minionsImage)
        }

        // 5. 繪製綠色路徑，透明度 1 (不透明)，線條粗細 10
        drawPath(
            path = path,
            color = Color.Green,
            alpha = 1f,
            style = Stroke(width = 10f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestCanvasPreview() {
    CanvasTheme {
        TestCanvas()
    }
}