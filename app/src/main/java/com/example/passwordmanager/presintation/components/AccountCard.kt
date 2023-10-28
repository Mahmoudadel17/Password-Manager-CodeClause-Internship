package com.example.passwordmanager.presintation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.passwordmanager.data.local.Account


@Composable
fun AccountCard(account: Account, onCardClick:()->Unit,onIconCopyClick:(Int)->Unit) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp).height(70.dp).clickable {onCardClick()},
        ) {
        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.background(MaterialTheme.colorScheme.tertiary).padding(8.dp)) {
            Spacer(modifier = Modifier.width(10.dp))
            AccountDetails(account = account, modifier = Modifier.weight(0.80f))
            AccountIcon(Icons.Default.ContentCopy,"Copy Icon",Modifier.weight(0.20f)){
                onIconCopyClick(account.id)
            }
        }
    }
}

@Composable
fun AccountDetails(account: Account,modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = account.userName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        CompositionLocalProvider (
            LocalContentAlpha provides ContentAlpha.medium){
            Text(
                text = account.email,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(1f),
                color = MaterialTheme.colorScheme.secondary

            )
        }
    }
}



@Composable
fun AccountIcon(icon: ImageVector, contentDescription:String, modifier: Modifier, onIconClick:()-> Unit = {}) {
    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier.clickable {
            onIconClick()
        },
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),

        )
}