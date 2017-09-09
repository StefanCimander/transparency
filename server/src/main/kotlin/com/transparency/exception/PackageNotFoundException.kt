package com.transparency.exception

class PackageNotFoundException(val id: Long): Throwable("The package with id $id could not be found")
