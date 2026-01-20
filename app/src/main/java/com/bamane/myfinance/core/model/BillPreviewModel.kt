package com.bamane.myfinance.core.model

data class BillPreviewModel(
    val billId: Long,
    val title: String,
    val date: Long,
    val totalAmount: Double,
    val participantNames: String?,
    val participantCount: Int
)