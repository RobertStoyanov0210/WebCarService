//package com.example.carservice.data.repository;
//
//import com.example.carservice.data.entity.CarService;
//import com.example.carservice.data.entity.Reservation;
//import com.example.carservice.data.entity.ReservationKey;
//import com.example.carservice.data.entity.Vehicle;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.data.jpa.repository.Query;
//
//import java.time.LocalDate;
//import java.util.Set;
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@DataJpaTest
//public class ReservationRepositoryTest {
//    @Autowired
//    private TestEntityManager testEntityManager;
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//
//    //TODO find out how to test it(composite key = problem)
////    @Query("select r from Reservation r where r.carService.id = ?1 and r.isComplete = false")
//    @Test
//    void findCurrentReservationsByCarService(){
//        CarService carService = new CarService();
//        carService.setName("cs");
//        carService.setMaxRepairingCars(3);
//        testEntityManager.persistAndFlush(carService);
//        Reservation reservation1 = new Reservation();
//        ReservationKey reservationKey1 = new ReservationKey();
//        reservationKey1.setCarServiceId(carService.getId());
//        reservationKey1.setVehicleId(new Vehicle().getId());
//        reservationKey1.setDate(LocalDate.of(2012,12,12));
//        reservation1.setId(reservationKey1);
//        Reservation reservation2 = new Reservation();
//        ReservationKey reservationKey2 = new ReservationKey();
//        reservationKey2.setCarServiceId(carService.getId());
//        reservationKey2.setVehicleId(new Vehicle().getId());
//        reservationKey2.setDate(LocalDate.of(2011,11,11));
//        reservation2.setId(reservationKey1);
//        reservation2.setComplete(true);
//        testEntityManager.persistAndFlush(reservation1);
//        testEntityManager.persistAndFlush(reservation2);
//        testEntityManager.persistAndFlush(carService);
//
//        assertThat(reservationRepository.findCurrentReservationsByCarService(carService.getId()).size()).isEqualTo(2);
//    }
//
////    @Query("select r from Reservation r where r.id.carServiceId = ?1 and r.id.vehicleId = ?2 and r.id.date = ?3")
////    Reservation findSpecificReservation(long carServiceId, long vehicleId, LocalDate date);
//}
