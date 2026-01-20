package com.bamane.myfinance.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composables.icons.lucide.ArrowDownLeft
import com.composables.icons.lucide.ArrowUpRight
import com.composables.icons.lucide.Clock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.Scan
import com.composables.icons.lucide.User
import com.composables.icons.lucide.Users
import androidx.compose.runtime.getValue
import com.bamane.myfinance.core.utils.toCompactRupiah
import com.bamane.myfinance.core.utils.toRupiah


@Preview(showBackground = true)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)
) {

    val totalReceivable by viewModel.totalReceivable.collectAsState()
    val totalDebt by viewModel.totalDebt.collectAsState()
    val totalBalance by viewModel.totalBalance.collectAsState()
    val billsHistory by viewModel.billPreviews.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xF5EEF2FF),
                        Color.White
                    )
                )
            )
            .padding(bottom = 96.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF6A1B9A))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {

                    Column {
                        Text(
                            text = "Hello,",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 14.sp,
                        )
                        Text(
                            text = "Bama",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clickable {
                                println("test")
                            }
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Lucide.User,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(16.dp)
                )  {
                    Text(
                        text = "Total Balance",
                        color = Color.Black.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = totalBalance.toRupiah(),
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Lucide.ArrowDownLeft,
                                    contentDescription = null,
                                    tint = Color(0xFF22C55E),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Piutang",
                                    color = Color.Black.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = totalReceivable.toCompactRupiah(),
                                color = Color(0xFF22C55E),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Lucide.ArrowUpRight,
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Hutang",
                                    color = Color.Black.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = totalDebt.toCompactRupiah(),
                                color = Color.Red,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionCard(title = "Tambah", icon = Lucide.Plus, iconColor = Color(color = 0xFF3B82F6) , modifier = Modifier.weight(1f))
            ActionCard(title = "Scan", icon = Lucide.Scan, iconColor = Color(color = 0xFF9333EA) , modifier = Modifier.weight(1f))
            ActionCard(title = "Teman", icon = Lucide.Users, iconColor = Color(color = 0xFFEC4899) , modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text (text = "Transaksi Terakhir",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Text (
                text = "Lihat Semua",
                modifier = Modifier
                    .clickable {
                        println("Text")
                    },
                fontSize = 15.sp ,
                color = Color(0xFF4F46E5),
                fontWeight = FontWeight.SemiBold)

        }
        Spacer(modifier = Modifier.height(20.dp))

        TransactionCard()
    }
}

@Composable
fun ActionCard(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    println("Text diklik!")
                }
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Quick Button",
                    tint = iconColor,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun TransactionCard (

) {
    Card (
        modifier = Modifier
            .clickable{
                println("test")
            }
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box (
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFFF9A825)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Lucide.Clock,
                        contentDescription = "Transaction",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Makan di Bu Tatang",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                    Text(
                        text = "dengan Phoebe",
                        fontSize = 13.sp,
                        color = Color.Black.copy(alpha = 0.6f))
                    Text(
                        text = "2 jam yang lalu",
                        fontSize = 12.sp,
                        color = Color.Black.copy(alpha = 0.4f))
                }
            }

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "-Rp75K",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color(0xFFDC2626)
                    )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFEF9C3))
                        .width(60.dp)
                        .height(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Pending",
                        color = Color(0xFFA16207),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }

}