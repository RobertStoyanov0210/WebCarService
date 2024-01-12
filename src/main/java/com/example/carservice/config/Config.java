package com.example.carservice.config;

import com.example.carservice.data.entity.Reservation;
import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.dto.reservation.ReservationDTO;
import com.example.carservice.dto.reservation.UpdateReservationDTO;
import com.example.carservice.web.view.model.reservation.ReservationViewModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Reservation.class, ReservationDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getId().getDate(),
                    ReservationDTO::setDate);
        });

        modelMapper.addMappings(new PropertyMap<ReservationDTO, Reservation>() {
            @Override
            protected void configure() {
                map().getId().setDate(source.getDate());
            }
        });
        modelMapper.addMappings(new PropertyMap<UpdateReservationDTO, Reservation>() {
            @Override
            protected void configure() {
                map().getId().setDate(source.getDate());
            }
        });
        modelMapper.addMappings(new PropertyMap<CreateReservationDTO, Reservation>() {
            @Override
            protected void configure() {
                map().getId().setDate(source.getDate());
            }
        });
        return modelMapper;
    }

    @Bean
    public FormattingConversionService conversionService() {

        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        // Ensure @NumberFormat is still supported
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        // Register date conversion with a specific global format
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyyMMdd"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}
