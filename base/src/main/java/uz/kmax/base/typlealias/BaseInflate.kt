package uz.kmax.base.typlealias

import android.view.LayoutInflater
import android.view.ViewGroup

typealias BaseInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T