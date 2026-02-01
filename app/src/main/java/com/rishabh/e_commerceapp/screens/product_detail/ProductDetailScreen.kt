package com.rishabh.e_commerceapp.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rishabh.e_commerceapp.common.Resource
import com.rishabh.e_commerceapp.domain.model.Product
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextDecoration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    vm: ProductDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onCartClick: () -> Unit
) {

    val product by vm.product.collectAsState()
    val state by vm.state.collectAsState()

    Box(Modifier.fillMaxSize().padding(top = 32.dp, bottom = 45.dp)) {

        Column(Modifier.fillMaxSize()) {

            // 🔒 NON-SCROLLABLE TOP BAR
            ProductTopBar(onBack, onCartClick)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
                    .verticalScroll(rememberScrollState())
            ) {


                product?.let { item ->

                    ProductImageSection(item.image)

                    ProductInfoSection(item)
                }



                when (state) {
                    is Resource.Loading -> Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }

                    is Resource.Error -> Text("Error loading products")

                    is Resource.Success -> {
                        RecommendedSection(state.data ?: emptyList())
                    }
                }
            }
        }

        AddToBagButton(
            product = product,
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { vm.toggleCart(product?:Product(0,"",0.0,"","","")) }
        )
    }
}

@Composable
fun ProductTopBar(onBack: () -> Unit, onCartClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
        }

        IconButton(onClick = onCartClick) {
            Icon(
                Icons.Outlined.ShoppingCart,
                contentDescription = null,
                tint = Color(0xFFCB2356)

            )
        }
    }
}


@Composable
fun ProductImageSection(imageUrl: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .width(if (index == 0) 24.dp else 8.dp)
                        .background(
                            if (index == 0) Color(0xFFCB2356)
                            else Color.LightGray,
                            RoundedCornerShape(50)
                        )
                )
            }
        }
    }
}


@Composable
fun ProductInfoSection(product: Product) {
    Column(Modifier.padding(12.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.category,
                color = Color(0xFF6C63FF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Icon(Icons.Outlined.FavoriteBorder, null)
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = product.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(5) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(Modifier.width(6.dp))
            Text("(${product.rating})", fontSize = 12.sp)
        }

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "20% off",
                color = Color(0xFF2ECC71),
                fontSize = 14.sp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "₹${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "₹${product.price}",
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}


@Composable
fun RecommendedSection(products: List<Product>) {
    Column(Modifier.padding(start = 12.dp)) {

        Text(
            text = "Recommended for you",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products) {
                RecommendedCard(it)
            }
        }
    }
}


@Composable
fun RecommendedCard(product: Product) {
    Card(
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = product.category,
                    fontSize = 12.sp,
                    color = Color(0xFF6C63FF)
                )
                Text(
                    text = product.title,
                    fontSize = 13.sp,
                    maxLines = 1
                )
                Text(
                    text = "₹${product.price}  ₹${product.price}",
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
fun AddToBagButton(
    product: Product?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFCB2356)
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Icon(Icons.Default.ShoppingCart, null)
        Spacer(Modifier.width(8.dp))
        Text(
            if (product?.isInCart == true) "Remove from Bag"
            else "Add to Bag"
        )

    }
}

