package com.transparency.model

import com.transparency.entity.PackageEntity

class Package(val id: Long, val name: String) {

    constructor(entity: PackageEntity): this(entity.id, entity.name)

}