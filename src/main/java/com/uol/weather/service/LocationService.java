package com.uol.weather.service;


import com.uol.weather.exception.LocationNotFoundException;
import com.uol.weather.externalservice.IpVililanteService;
import com.uol.weather.externalservice.MetaWeatherService;
import com.uol.weather.model.Location;
import com.uol.weather.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MetaWeatherService metaWeatherService;

    @Autowired
    private IpVililanteService ipVililanteService;

    public Location findLocationById(Integer locationId){
        Optional<Location> location = locationRepository.findById(locationId);
        if(!location.isPresent()){
            throw new LocationNotFoundException("Location not found.");
        }
        return location.get();
    }

    public Location createLocation(String ipAddress) throws IOException {

        Location locationFromUser = new Location();

        Location newLocation = ipVililanteService.getIpVigilante(ipAddress);
        locationFromUser.setIp(ipAddress);
        locationFromUser.setLatitude(newLocation.getLatitude());
        locationFromUser.setLongitude(newLocation.getLongitude());

        // woeid = Where On Earth ID
        Location locationWithWoeid = metaWeatherService.getWOEId(locationFromUser.getLatitude(), locationFromUser.getLongitude());
        locationFromUser.setWoeid(locationWithWoeid.getWoeid());
        locationFromUser.setDistance(locationWithWoeid.getDistance());
        locationFromUser.setCity(locationWithWoeid.getCity());
        // Get Weather by Location
        Location locationWithWeather = metaWeatherService.getWeather(locationWithWoeid.getWoeid());
        locationFromUser.setMinTemp(locationWithWeather.getMinTemp());
        locationFromUser.setMaxTemp(locationWithWeather.getMaxTemp());

        return locationRepository.save(locationFromUser);
    }

    public List<Location> findAllLocation(){
        return locationRepository.findAll();
    }

    public Location updateLocation(Location location, Integer id) {
        Location foundUser = findLocationById(id);
        // Desta forma, posso passar apenas um parametro ou todos para serem atualizados.
        if(location.getLatitude() !=null){
            foundUser.setLatitude(location.getLatitude());
        }

        if(location.getLongitude() !=null){
            foundUser.setLongitude(location.getLongitude());
        }

        if(location.getMaxTemp() !=null){
            foundUser.setMaxTemp(location.getMaxTemp());
        }

        if(location.getMinTemp() !=null){
            foundUser.setMinTemp(location.getMinTemp());
        }

        if(location.getWoeid() !=null){
            foundUser.setWoeid(location.getWoeid());
        }

        if(location.getDistance() !=null){
            foundUser.setLatitude(location.getLatitude());
        }

        if(location.getCity() !=null){
            foundUser.setCity(location.getCity());
        }

        if(location.getIp() !=null){
            foundUser.setIp(location.getIp());
        }

        return locationRepository.save(foundUser);
    }

    public void deleteLocation(Integer locationId) {
        findLocationById(locationId);
        locationRepository.deleteById(locationId);
    }

    public Boolean isValid(Location location) {

        if(location.getLatitude() !=null){
            return true;
        }

        if(location.getLongitude() !=null){
            return true;
        }

        if(location.getMaxTemp() !=null){
            return true;
        }

        if(location.getMinTemp() !=null){
            return true;
        }

        if(location.getWoeid() !=null){
            return true;
        }

        if(location.getDistance() !=null){
            return true;
        }

        if(location.getCity() !=null){
            return true;
        }

        if(location.getIp() !=null){
            return true;
        }

        return false;
    }
}