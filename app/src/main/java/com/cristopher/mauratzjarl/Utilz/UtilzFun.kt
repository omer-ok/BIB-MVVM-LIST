package com.cristopher.mauratzjarl.Utilz

inline fun <reified T> genericCastOrNull(anything: Any?):T? {
    return anything as? T
}