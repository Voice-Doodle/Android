package main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tes.presentation.model.Location
import com.tes.presentation.model.Vodle
import com.tes.presentation.theme.main_coral_bright
import com.tes.presentation.theme.main_coral_darken
import com.tes.presentation.theme.vodleTypoGraphy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VodleListDialogComponent(
    vodleList: List<Vodle>,
    onClick: () -> Unit,
) {
    var index = 0

    //////////////////////////////////////////////////////
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope
    //////////////////////////////////////////////////////

    LaunchedEffect(index) {

    }

    Box(
        modifier = Modifier.then(
            Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .background(color = Color.White)
        )
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = main_coral_darken,
                containerColor = Color.White,
                disabledContentColor = main_coral_darken,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "backButton",
                modifier = Modifier.fillMaxWidth()
            )
        }
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = main_coral_darken,
                containerColor = Color.White,
                disabledContentColor = main_coral_darken,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "forwardButton",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center)
                .padding(start = 20.dp, top = 10.dp, end = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(
                    text = vodleList.get(index % vodleList.size).writer,
                    textAlign = TextAlign.Start,
                    style = vodleTypoGraphy.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = vodleList.get(index % vodleList.size).address,
                    textAlign = TextAlign.Start,
                    style = vodleTypoGraphy.titleSmall,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(
                    text = vodleList.get(index % vodleList.size).date,
                    textAlign = TextAlign.Start,
                    style = vodleTypoGraphy.bodySmall,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = vodleList.get(index % vodleList.size).category,
                    textAlign = TextAlign.Start,
                    style = vodleTypoGraphy.titleMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                IconButton(
                    onClick = {
                        loading = true
                        scope.launch {
                            loadProgress { progress ->
                                currentProgress = progress
                            }
                            loading = false
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = main_coral_darken,
                        containerColor = Color.White,
                        disabledContentColor = main_coral_darken,
                        disabledContainerColor = Color.White
                    ),
                ) {
                    if(loading){
                        Icon(
                            imageVector = Icons.Outlined.Pause,
                            contentDescription = "playButton",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    else{
                        Icon(
                            imageVector = Icons.Outlined.PlayArrow,
                            contentDescription = "playButton",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    trackColor = main_coral_bright,
                    color = main_coral_darken
                )
            }
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..16) {
        updateProgress(i.toFloat() / 16)
        delay(1000)
    }
}

@Preview
@Composable
fun PreviewListDialogComponent() {
    for (i in 0..20) {
        var location1: Location =
            Location(36.1081844 + (Math.random() % 100), 128.4139686 + (Math.random() % 100))

        VodleListDialogComponent(
            mutableListOf(Vodle(1, "2024-03-25", "주소란", "테스터1", "음식", location1)),
            {}
        )
    }
}
