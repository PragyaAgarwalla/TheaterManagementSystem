package com.virtualpairprogrammers.theater.data

import com.virtualpairprogrammers.theater.domain.Booking
import com.virtualpairprogrammers.theater.domain.Performance
import com.virtualpairprogrammers.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository : JpaRepository<Performance, Long>
interface SeatRepository : JpaRepository<Seat, Long>
interface BookingRepository : JpaRepository<Booking, Long>