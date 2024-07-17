package com.gachi.gb.rez.repository

import com.gachi.gb.rez.domain.BusReservationHistory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BusReservationHistoryRepository: JpaRepository<BusReservationHistory, UUID> {
}