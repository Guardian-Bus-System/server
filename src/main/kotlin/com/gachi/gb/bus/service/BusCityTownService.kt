package com.gachi.gb.bus.service

import com.gachi.gb.bus.dto.BusTownDto

interface BusCityTownService {
  fun getTown(townId: Int): BusTownDto.Get

}