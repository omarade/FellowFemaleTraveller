package nl.fontys.fft.data

import androidx.lifecycle.ViewModel
import nl.fontys.fft.models.User

class UserViewModel : ViewModel() {
    private val users: MutableList<User> = mutableListOf(
        User(0, "Rawan Toze", "Breda", "rawan.toze@live.com", "0643254634"),
        User(1, "Suzan Alex", "Rome", "Suzan.toze@live.com", "0643254634"),
        User(2, "Roze John", "Paris", "rawan.toze@live.com", "0643254634"),
        User(3, "Maria Con", "Barcelona", "rawan.toze@live.com", "0643254634"),
        User(4, "Hailey regen", "Amsterdam", "rawan.toze@live.com", "0643254634"),
        User(5, "Nancy Tome", "Malmo", "rawan.toze@live.com", "0643254634"),
        User(6, "Laila Shek", "Cairo", "rawan.toze@live.com", "0643254634"),
        User(7, "Hanna Mon", "Vienna", "rawan.toze@live.com", "0643254634"),
        User(8, "Valerie Jan", "Berlin", "rawan.toze@live.com", "0643254634"),
        User(9, "Saskia Jong", "Brussels", "rawan.toze@live.com", "0643254634"),
    )

    fun getUsers(): MutableList<User> {
        return users
    }
}