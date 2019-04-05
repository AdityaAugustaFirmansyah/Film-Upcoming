package com.example.aditya.filmupcoming.utils

import io.reactivex.Scheduler

interface SchedullerProvider {
    fun computation():Scheduler
    fun trampolin():Scheduler
    fun newThread():Scheduler
    fun ui():Scheduler
    fun io():Scheduler
}