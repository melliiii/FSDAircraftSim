package de.rapshansa.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Melli
 * @version 1.0
 */
public class FlightPlan {

    private String callsign;
    private String flight_type; //[I = IFR], [V = VFR], [D = DVFR], [S = SVFR]
    private String aircraft_type;
    private String tas_cruise;
    private String departure_icao;
    private String departure_time;
    private String departure_time_actual;
    private String cruise_altitude; // in feet
    private String destination_icao;
    private String hours_enroute;
    private String mins_enroute;
    private String fuel_hours;
    private String fuel_mins;
    private String alternate_icao;
    private String remarks;
    private String route; // 300 bytes max

    /**
     * Constructor for hard cloning a flight plan
     * @param callsign Callsign
     * @param flight_type [I = IFR], [V = VFR], [D = DVFR], [S = SVFR]
     * @param aircraft_type Aircraft Icao eg. B738, A321, etc
     * @param tas_cruise True airspeed cruise speed
     * @param departure_icao Departure Airport eg. EDDF, LOWW, KLAX
     * @param departure_time Estimated departure time eg. 0300 = 3:00 AM ZULU
     * @param departure_time_actual Estimated actual departure time eg. 0310 = 3:10 AM ZULU
     * @param cruise_altitude Cruise altitude in feet
     * @param destination_icao Destination Airport eg EDDF, LOWW, KLAX
     * @param hours_enroute Estimated hours enroute
     * @param mins_enroute Estimated minutes enroute
     * @param fuel_hours Fuel on board hours
     * @param fuel_mins Fuel on board minutes
     * @param alternate_icao Altenate Airport eg. EDDF, LOWW, KLAX
     * @param remarks Remarks !!ENDS WITH : !!
     * @param route Route, Maximum 300 Bytes of data!
     */
    public FlightPlan(String callsign, String flight_type, String aircraft_type, String tas_cruise,
                      String departure_icao, String departure_time, String departure_time_actual,
                      String cruise_altitude, String destination_icao, String hours_enroute, String mins_enroute,
                      String fuel_hours, String fuel_mins, String alternate_icao, String remarks, String route) {

        this.callsign = callsign;
        this.flight_type = flight_type;
        this.aircraft_type = aircraft_type;
        this.tas_cruise = tas_cruise;
        this.departure_icao = departure_icao;
        this.departure_time = departure_time;
        this.departure_time_actual = departure_time_actual;
        this.cruise_altitude = cruise_altitude;
        this.destination_icao = destination_icao;
        this.hours_enroute = hours_enroute;
        this.mins_enroute = mins_enroute;
        this.fuel_hours = fuel_hours;
        this.fuel_mins = fuel_mins;
        this.alternate_icao = alternate_icao;
        this.remarks = remarks;
        this.route = route;
    }

    /**
     * Empty Constructor
     * @see FlightPlan generateFromString(String flightplan);
     */
    public FlightPlan(){} // Empty Constructor to allow for regeneration by string

    // ## Functions <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Converts a String to a Flightplan
     * @param flightplan Flightplan as a String
     * @return Flightplan instance
     * @// TODO: 11/8/2021 Generate Flightplan from Simbrief. Maybe load a .xpl FPL and pass its content into this function?
     */
    public static FlightPlan generateFromString ( String flightplan ){ return null; }

    /**
     * Converts Flightplan Object to a String ArrayList
     * @return ArrayList of contents
     * @see ArrayList
     */
    public List<String> toStringList(){
        List<String> stringList = new ArrayList<>();
        stringList.add(callsign);
        stringList.add(flight_type);
        stringList.add(aircraft_type);
        stringList.add(tas_cruise);
        stringList.add(departure_icao);
        stringList.add(departure_time);
        stringList.add(departure_time_actual);
        stringList.add(cruise_altitude);
        stringList.add(destination_icao);
        stringList.add(hours_enroute);
        stringList.add(mins_enroute);
        stringList.add(fuel_hours);
        stringList.add(fuel_mins);
        stringList.add(alternate_icao);
        stringList.add(remarks);
        stringList.add(route);
        return stringList;
    }

    /**
     * Outputs flight plan object as String[]
     * @return flight plan as params[]
     */
    public String[] toStringArray(){
        return toStringList().toArray(new String[0]);
    }

    // ## Getters and Setters <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getFlight_type() {
        return flight_type;
    }

    public void setFlight_type(String flight_type) {
        this.flight_type = flight_type;
    }

    public String getAircraft_type() {
        return aircraft_type;
    }

    public void setAircraft_type(String aircraft_type) {
        this.aircraft_type = aircraft_type;
    }

    public String getTas_cruise() {
        return tas_cruise;
    }

    public void setTas_cruise(String tas_cruise) {
        this.tas_cruise = tas_cruise;
    }

    public String getDeparture_icao() {
        return departure_icao;
    }

    public void setDeparture_icao(String departure_icao) {
        this.departure_icao = departure_icao;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getDeparture_time_actual() {
        return departure_time_actual;
    }

    public void setDeparture_time_actual(String departure_time_actual) {
        this.departure_time_actual = departure_time_actual;
    }

    public String getCruise_altitude() {
        return cruise_altitude;
    }

    public void setCruise_altitude(String cruise_altitude) {
        this.cruise_altitude = cruise_altitude;
    }

    public String getDestination_icao() {
        return destination_icao;
    }

    public void setDestination_icao(String destination_icao) {
        this.destination_icao = destination_icao;
    }

    public String getHours_enroute() {
        return hours_enroute;
    }

    public void setHours_enroute(String hours_enroute) {
        this.hours_enroute = hours_enroute;
    }

    public String getMins_enroute() {
        return mins_enroute;
    }

    public void setMins_enroute(String mins_enroute) {
        this.mins_enroute = mins_enroute;
    }

    public String getFuel_hours() {
        return fuel_hours;
    }

    public void setFuel_hours(String fuel_hours) {
        this.fuel_hours = fuel_hours;
    }

    public String getFuel_mins() {
        return fuel_mins;
    }

    public void setFuel_mins(String fuel_mins) {
        this.fuel_mins = fuel_mins;
    }

    public String getAlternate_icao() {
        return alternate_icao;
    }

    public void setAlternate_icao(String alternate_icao) {
        this.alternate_icao = alternate_icao;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
