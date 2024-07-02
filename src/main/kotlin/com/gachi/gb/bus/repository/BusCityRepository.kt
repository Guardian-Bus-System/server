package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.BusCity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BusCityRepository: JpaRepository<BusCity, String> {

  fun findById(cityId: Int): Optional<BusCity>
//  fun findByName(name: String): Optional<BusCity>

  fun existsByCityName(name: String): Boolean
}