package huji.bez.drop

import java.io.Serializable

class UserData() : Serializable  {

    public var userName : String? = null
    public var dropName : String? = null
    public var points : Long = 0
    public var hungerLevel : Int = 0
    public var energyLevel : Int = 0
    public var loveLevel : Int = 100

}