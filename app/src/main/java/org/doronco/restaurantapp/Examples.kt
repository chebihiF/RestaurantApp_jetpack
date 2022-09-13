package org.doronco.restaurantapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun DisplayUI() {
    val text_title = remember {
        mutableStateOf("Example")
    }
    val fontSizeValue = remember {
        mutableStateOf(24)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.size(500.dp, 100.dp)
    ) {
        Text(
            text = text_title.value,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.purple_500),
            fontSize = fontSizeValue.value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
        DisplayUI2(){
            text_title.value = "Hello"
            fontSizeValue.value = 50
        }
    }

}

@Composable
fun DisplayUI2(onclick: () -> Unit) {
    Button(
        onClick = {
           onclick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.purple_200),
            contentColor = colorResource(id = R.color.white)
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(text = "Press Me")
    }
}