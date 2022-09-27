package nl.fontys.fft.models

class User (var id: Int, var name: String, var city: String, var email: String, var phone: String, var nrOfGuests: Int, var desc: String) {

    override fun toString(): String {
        return "$name \n$email\n \n$desc"
    }
}