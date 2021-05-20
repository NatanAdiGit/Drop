package huji.bez.drop

import java.io.Serializable

class UserData( var userName : String?, var dropName : String? ) : Serializable  {

    // todo - dropling
//    public var points : Long = 0
    var hungerLevel : Int = 100
    var energyLevel : Int = 100
    var loveLevel : Int = 100

}