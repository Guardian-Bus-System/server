package com.gachi.gb.bus.repository

import com.gachi.gb.bus.domain.BusDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BusDetailsRepository: JpaRepository<BusDetails, String> {

  //  fun findByName(name: String): Optional<BusDetails>
  fun findAllByName(name: String): MutableList<BusDetails>
}