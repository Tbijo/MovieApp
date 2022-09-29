package com.example.movieapp.presentation.movie_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.unit.dp
import com.example.movieapp.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    name: String,
    hint: String,
    orderType: OrderType = OrderType.Descending,
    onOrderChange: (OrderType) -> Unit,
    onTextChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    isHintVisible: Boolean
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultTextField(
                text = name,
                hint = hint,
                onValueChange = {
                    onTextChange(it)
                },
                onFocusChange = {
                    onFocusChange(it)
                },
                singleLine = true,
                isHintVisible = isHintVisible
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(OrderType.Ascending)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(OrderType.Descending)
                }
            )
        }
    }
}

