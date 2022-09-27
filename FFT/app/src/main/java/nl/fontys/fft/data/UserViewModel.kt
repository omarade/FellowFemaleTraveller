package nl.fontys.fft.data

import androidx.lifecycle.ViewModel
import nl.fontys.fft.models.User

class UserViewModel : ViewModel() {
    private var users: MutableList<User> = mutableListOf(
        User(0, "Rasha Reem", "Amsterdam", "rasha.re@live.com", "0643254634", 2, "Donec ut iaculis dui. Nam in semper sapien. Donec a nulla vitae tortor maximus commodo sit amet non lacus. Ut eget tortor lorem. Nam pulvinar, urna vitae rutrum dapibus, augue augue fermentum tortor"),
        User(1, "Suzan Alex", "Rome", "suzan.al@live.com", "0643457564",1, "Curabitur id hendrerit dui. Curabitur ornare tristique lorem non tempor. Maecenas luctus tincidunt blandit. Etiam nunc elit, gravida sit amet efficitur sit amet, dignissim non velit."),
        User(2, "Roze John", "Paris", "roze.jo@live.com", "0625636678", 4, "Duis ac pharetra velit. Sed eleifend nisl sed quam vestibulum consectetur. Aenean a libero ornare, eleifend libero in, ultricies mauris. "),
        User(3, "Maria Con", "Barcelona", "mar.con33@live.com", "0675543684", 2, "Etiam bibendum finibus dui, id tincidunt nibh efficitur nec. Aliquam semper fermentum finibus. Fusce id sagittis lectus, quis ornare mauris. "),
        User(4, "Hailey regen", "Amsterdam", "hailereg11@live.com", "0644573274", 2, "Cras sit amet felis nisi. Vestibulum fringilla maximus nunc, vel elementum ligula lobortis ut. Nunc condimentum, turpis et posuere elementum"),
        User(5, "Nancy Tome", "Malmo", "nancyjar32@live.com", "0634675683", 3, "luctus et ultrices posuere cubilia curae; Pellentesque posuere convallis tortor, ac pretium eros. Quisque at nisl sit amet purus scelerisque faucibus."),
        User(6, "Laila Shek", "Cairo", "laila.shekh@live.com", "0643547382", 1, "Morbi felis magna, gravida eget nunc nec, iaculis pulvinar nisl. Morbi auctor ut libero sit amet consectetur. Mauris sit amet placerat ex. Mauris porta facilisis erat vitae aliquam."),
        User(7, "Hanna Mare", "Vienna", "hana.ma@live.com", "0645437882", 3, "Phasellus tortor urna, convallis ut orci nec, rhoncus aliquet nisi. Morbi mollis feugiat nibh."),
        User(8, "Valerie Jan", "Berlin", "val.ja54@live.com", "0643457389", 1, "Etiam congue nunc ante, congue auctor nisi dictum vel. Proin eu posuere eros, suscipit pellentesque risus. Curabitur lacinia rhoncus nisi sit amet rutrum."),
        User(9, "Saskia Jong", "Brussels", "sas.jong43@live.com", "0664899454",4, "Sed dolor ex, elementum ut dictum et, blandit nec purus. Quisque in accumsan mi, in pharetra risus. Ut venenatis quis sem eget faucibus. "),
    )

    fun getUsers(): MutableList<User> {
        return users
    }

    fun getUsersByCity(city: String): MutableList<User> {
        var usersInCity: MutableList<User> = mutableListOf()

        for (user in users) {
            if (user.city == city) {
                usersInCity.add(user)
            }
        }

        return usersInCity
    }
}