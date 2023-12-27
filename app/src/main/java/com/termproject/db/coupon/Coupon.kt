package com.termproject.db.coupon

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.termproject.utils.CouponStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "coupons")
class Coupon(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val status: CouponStatus,
    var playedAt: LocalDateTime = LocalDateTime.now(),
    var amount: Int = 0
) {

    override fun toString(): String {
        return "Coupon status=$status playedAt=" + playedAt.format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
    }

}