package com.github.tfalc.citiesapi.countries.repository;

import com.github.tfalc.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
