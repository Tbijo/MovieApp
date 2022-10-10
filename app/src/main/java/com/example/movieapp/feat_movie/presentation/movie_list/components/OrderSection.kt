package com.example.movieapp.feat_movie.presentation.movie_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.feat_movie.domain.util.OrderType

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
    val mContext = LocalContext.current

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
                isHintVisible = isHintVisible,
                testTag = mContext.resources.getString(R.string.text_field)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = mContext.resources.getString(R.string.Ascending),
                selected = orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(OrderType.Ascending)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = mContext.resources.getString(R.string.Descending),
                selected = orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(OrderType.Descending)
                }
            )
        }
    }
}

