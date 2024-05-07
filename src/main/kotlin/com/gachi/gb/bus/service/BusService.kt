package com.gachi.gb.bus.service

import com.gachi.gb.bus.domain.Bus

interface BusService {

  fun getBus(busId: Int): Bus

  fun getBuses(): List<Bus>

}