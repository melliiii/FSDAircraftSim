package de.rapshansa;

import de.rapshansa.helpers.FlightPlan;
import de.rapshansa.packets.FSDAddPilotPacket;
import de.rapshansa.packets.FSDSendFlightPlan;

import java.io.IOException;
import java.net.InetSocketAddress;

public class FSDAircraftSim {
    public static void main(String[] args) throws IOException, InterruptedException {

        FSDClient fsdClient = new FSDClient("111111", "password", new InetSocketAddress("157.90.27.182", 6809));
        fsdClient.connect();
        String[] params = {"RAH69M", "SERVER", "111111", "password", "1", "9", "2", "Melli"};
        fsdClient.sendPacket(new FSDAddPilotPacket(params));

        FlightPlan fpl = new FlightPlan();
        fpl.setCallsign("RAH69M");
        fpl.setFlight_type("I");
        fpl.setAircraft_type("A388");
        fpl.setTas_cruise("480");
        fpl.setDeparture_icao("LOWW");
        fpl.setDeparture_time("2300");
        fpl.setDeparture_time_actual("2305");
        fpl.setCruise_altitude("38000");
        fpl.setDestination_icao("EDDF");
        fpl.setHours_enroute("00");
        fpl.setMins_enroute("59");
        fpl.setFuel_hours("02");
        fpl.setFuel_mins("14");
        fpl.setAlternate_icao("LSZH");
        fpl.setRemarks("PBN/A1B1C1D1O1S1 DOF/211108 REG/CGTLU EET/LOVV0002 EDMM0007 SEL/FMLP OPR/RAH PER/C CS/RAPSHANSA RMK/CALLSIGN RAPSHANSA");
        fpl.setRoute("LUGEM1C LUGEM DCT SUBEN T161 ASPAT/N0473F240 T161 FAWUR FAWUR2C");

        fsdClient.sendPacket(new FSDSendFlightPlan(fpl.toStringArray()));
        //fsdClient.shutdown("RAH69M");

        /*
        // Aircraft Variables

        Point2D aircraft_position = new Point2D.Double(48.109685, 16.573699);
        Point2D direct_to = new Point2D.Double(48.120432, 16.540708);
        double altitude = 4000;
        double velocity = 30;
        double target_velocity = 128.611;
        double accelleration = 0;
        double indicated_airspeed = velocity * 1.944;
        double ground_speed = indicated_airspeed + (altitude/1000*7);
        double heading = 270;
        double target_heading = Math.toDegrees(Math.atan2(direct_to.getY() - aircraft_position.getY(), direct_to.getX() - aircraft_position.getX()));
        heading = heading + Math.ceil(-heading / 360) * 360;

        double totalTime = 0;
        double lastTime = System.nanoTime() * 1E-9;

        double time, dt;
        while (totalTime < 30){
            double x,y;
            x = aircraft_position.getX();
            y = aircraft_position.getY();

            accelleration = (target_velocity - velocity) * 0.5;
            if(accelleration > 4) accelleration = 4;
            time = System.nanoTime() * 1E-9;
            dt = time - lastTime;
            lastTime = time;

            totalTime += dt;

            velocity += accelleration * dt;
            x += Math.cos(heading) * velocity * dt * 0.0001;
            y += Math.sin(heading) * velocity * dt * 0.0001;

            aircraft_position = new Point2D.Double(x,y);
            //System.out.println("traveled " + position + "m over " + totalTime + "s; velocity = " + velocity + "m/s; IAS = " + velocity * 1.944 + "; GS = " + (velocity * 1.944 + (altitude/1000*7)));
            //System.out.println("IAS = " + velocity * 1.944);
            System.out.println("Lat: " + aircraft_position.getX() + "; Lon: " + aircraft_position.getY());

            Thread.sleep(100);
        }

*/



    }
}
