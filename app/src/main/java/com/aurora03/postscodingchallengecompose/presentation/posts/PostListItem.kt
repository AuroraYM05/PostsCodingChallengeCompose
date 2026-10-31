package com.aurora03.postscodingchallengecompose.presentation.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora03.postscodingchallengecompose.R
import com.aurora03.postscodingchallengecompose.domain.model.Post
import com.aurora03.postscodingchallengecompose.ui.theme.PostsCodingChallengeComposeTheme

@Composable
fun PostsListItem(
    post: Post
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
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
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline
                )
                //Muestra el ID del usuario que lo envio
                Text(
                    text = "${post.userId}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            //Contenido de la publicación
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (post.tags.isNotEmpty()){

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    post.tags.forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = tag,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Likes: ${post.likes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )

                Text(
                    text = "Dislikes: ${post.dislikes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )

                Text(
                    text = "Vistas: ${post.views}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
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
