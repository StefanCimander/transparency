package com.transparency.model


enum class DependencyType {

    /** The dependency is explicitly modeled by a feature link. */
    FEATURE_LINK,

    /** The dependency exists due to the exchange of signals in the logical layer. **/
    LOGICAL_DEPENDENCY
}