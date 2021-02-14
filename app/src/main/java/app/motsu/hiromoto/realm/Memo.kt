package app.motsu.hiromoto.realm

import io.realm.RealmObject

open class Memo(
    open var titile: String = "",
    open var content: String = ""
): RealmObject()