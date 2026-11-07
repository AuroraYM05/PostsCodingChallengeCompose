package com.aurora03.postscodingchallengecompose.presentation.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora03.postscodingchallengecompose.R
import com.aurora03.postscodingchallengecompose.domain.model.Post
import com.aurora03.postscodingchallengecompose.ui.theme.Inter
import com.aurora03.postscodingchallengecompose.ui.theme.PostsCodingChallengeComposeTheme

@Composable
fun PostsListItem(
    post: Post
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface),

        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.ListItem_textPublication),
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                //Muestra el ID del usuario que lo envio
                Text(
                    text = "${post.userId}",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = post.title,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            //Contenido de la publicación
            Text(
                text = post.body,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            if (post.tags.isNotEmpty()){

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    post.tags.forEach { tag ->
                        Surface(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.outline,),
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = tag,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface

                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 55.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.likes),
                        contentDescription = stringResource(R.string.image_like_description),
                        colorFilter = ColorFilter.tint(
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                    Text(
                        text = "${post.likes}",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.dislikes),
                    contentDescription = stringResource(R.string.image_dislike_description),
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.secondary
                    )
                )

                    Text(
                        text = "${post.dislikes}",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.views),
                        contentDescription = stringResource(R.string.image_views_description),
                        colorFilter = ColorFilter.tint(
                            MaterialTheme.colorScheme.secondary
                        )
                    )

                    Text(
                        text = "${post.views}",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostListItemPreview(){
    PostsCodingChallengeComposeTheme() {
        PostsListItem(
            post = Post(
                id = 1,
                title = "His mother had always taught him",
                body = "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
                tags = listOf("history", "american", "crime"),
                likes = 192,
                dislikes = 25,
                views = 305,
                userId = 121
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostListItemDarkPreview(){
    PostsCodingChallengeComposeTheme(darkTheme = true) {
        PostsListItem(
            post = Post(
                id = 2,
                title = "His mother had always taught him",
                body = "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
                tags = listOf("history", "american", "crime"),
                likes = 192,
                dislikes = 25,
                views = 305,
                userId = 121
            )
        )
    }
}
