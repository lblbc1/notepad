package cn.hsp.demo.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.hsp.demo.ui.theme.MainBgColor
import cn.hsp.demo.ui.theme.TextColor

@Composable
fun TitleBar(
    title: String,
    onBack: (() -> Unit)? = null,
    onRightClick: (() -> Unit)? = null,
    imageVector: ImageVector? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MainBgColor)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            if (onBack != null) {
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .padding(start=12.dp),
                    tint = TextColor
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = TextColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp)
                        .clickable {
                            onRightClick?.invoke()
                        })
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp),
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )

    }
}