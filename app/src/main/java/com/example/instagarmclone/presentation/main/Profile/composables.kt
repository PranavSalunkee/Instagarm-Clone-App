package com.example.instagarmclone.presentation.main.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Shapes
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagarmclone.domain.model.TabModel

@Composable
fun ProfileStats(number: String, text: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ) {
        Text(text = number, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text)
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    imageVector: ImageVector? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
        modifier = Modifier.border(1.dp, shape = RoundedCornerShape(5.dp), color = Color.LightGray)
    ) {
       if(text != null){
           Text(text = text,
               fontWeight = FontWeight.SemiBold,
               fontSize = 15.sp)

       }
        if(imageVector != null){
            Icon(imageVector = imageVector, contentDescription =null,
                tint = Color.Black)
        }
    }

}

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    tabModels:List<TabModel>,
    onTabSelected :(selectedIndex :Int)-> Unit

){
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    TabRow(selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier) {
        
        tabModels.forEachIndexed { index, item ->
            Tab(selected = selectedIndex == index, onClick = {
                selectedIndex = index
                onTabSelected(index)
            },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color(0xFF777777)
            ) {
                Image(painter = item.image, contentDescription = null)
            }
        }

    }

}


@Composable
fun MyProfile(
    name:String,
    bio:String,
    url:String
){
    Column(verticalArrangement = Arrangement.Center){
        Text(text = name)
        Text(text = bio)
        TextButton(onClick = {  }) {
            Text(text = url)
        }
    }
    
}
