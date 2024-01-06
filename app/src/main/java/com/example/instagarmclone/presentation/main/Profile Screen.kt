package com.example.instagarmclone.presentation.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.instagarmclone.R
import com.example.instagarmclone.domain.model.TabModel
import com.example.instagarmclone.domain.model.User
import com.example.instagarmclone.presentation.main.Profile.ActionButton
import com.example.instagarmclone.presentation.main.Profile.MyProfile
import com.example.instagarmclone.presentation.main.Profile.ProfileStats
import com.example.instagarmclone.presentation.main.Profile.TabView

import com.example.instagarmclone.ui.theme.InstagarmCloneTheme

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier,  user: User) {
    var selectedIndex: Int by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        TopAppBar(
            title = {
                Text(
                    text = user.userName,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 25.sp
                )
            },
            actions = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus), contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            backgroundColor = Color.White,
            elevation = 10.dp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 20.dp, bottom = 10.dp, top = 10.dp)
        ) {
           Image(
                 painter = painterResource(id = R.drawable.profile_pic),contentDescription = null,

                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .weight(2.5f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.weight(6.5f)
            ){
                
                ProfileStats(number = "43" , text = "Posts" )
                ProfileStats(number = user.followers, text ="Followers" )
                ProfileStats(number = user.following, text = "Following")
            }
        }
        MyProfile(name = user.name, bio = user.bio, url = user.url)
        Spacer(modifier = Modifier.height(20.dp))
        ActionButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .weight(0.45f)
                .height(35.dp)
                .clickable { }

        )
        Spacer(modifier = Modifier.height(15.dp))
        TabView(tabModels = listOf(
            TabModel(image = painterResource(id = R.drawable.ic_greed),text = "greed"),
            TabModel(image = painterResource(id = R.drawable.ic_reel),text = "reel"),
            TabModel(image = painterResource(id = R.drawable.ic_igtv),text = "igtv")
        ),
            onTabSelected = {
                selectedIndex = it
            },
            modifier = Modifier.height(30.dp).padding(start = 4.dp,end = 4.dp, bottom = 4.dp))
        when(selectedIndex){
            0 -> PostSection(
                posts = listOf(
                   painterResource(id = R.drawable.img_1),
                    painterResource(id = R.drawable.img_2),
                    painterResource(id = R.drawable.img_3),
                    painterResource(id = R.drawable.img_4),
                    painterResource(id = R.drawable.img_5),
                    painterResource(id = R.drawable.img_6),
                    painterResource(id = R.drawable.img_7),
                ),
                modifier = Modifier.padding(5.dp)
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    InstagarmCloneTheme {
        ProfileScreenContent(user = User(
            userName =" pranav_salunke",
            name  ="Pranav Salunke",
        bio  ="Hi this is Pranav. I an android Developer.",
         userId  ="",
        imageUrl ="",
        following = "3",
        followers = "546",
        url  ="www.xyz.com",
        email  ="",
        password  =""

        ))
    }
}




