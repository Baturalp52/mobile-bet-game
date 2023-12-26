package com.termproject.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var surname: String,
    var city: String,
    var district: String,
    var team: String,

    ) {
    var credit: Int = 0
    override fun toString(): String {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", team='" + team + '\'' +
                '}'
    }
}