package com.jre.hireout.utils.log

import org.slf4j.LoggerFactory

inline fun<reified T:Any> logger() = LoggerFactory.getLogger(T::class.java)