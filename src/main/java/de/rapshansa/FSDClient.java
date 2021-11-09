package de.rapshansa;

import de.rapshansa.helpers.ClientState;
import de.rapshansa.packets.FSDPacket;
import de.rapshansa.packets.FSDPositionUpdatePacket;
import de.rapshansa.packets.FSDRemovePilot;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FSDClient {

    // User Defined Variables
    private final InetSocketAddress fsd_host;
    private final String vid;
    private final String password;

    // Aircraft
    Point2D aircraft_position = new Point2D.Double(48.109685, 16.573699);
    Point2D direct_to = new Point2D.Double(48.538611, 12.947778);
    double altitude = 4000;
    double velocity = 160;
    double target_velocity = 128.611;
    double accelleration = 0;

    double totalTime = 0;
    double lastTime = System.nanoTime() * 1E-9;

    double time, dt;


    // Program Variables
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ClientState state = ClientState.NONE;
    private Thread listenerThread;

    private boolean running = true;


    // ## Constructor <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * Creates Instance of FSDClient
     * @param vid VID/Username
     * @param password Password
     * @param fsd_host Host to connect to
     */
    public FSDClient(String vid, String password, InetSocketAddress fsd_host){
        this.vid = vid;
        this.password = password;
        this.fsd_host = fsd_host;
    }

    // ## Functions <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * Connect Socket to FSD Server
     * @return Instance of FSDClient
     * @throws IOException Socket IOException
     */
    public FSDClient connect() throws IOException {
        socket = new Socket(fsd_host.getAddress(), fsd_host.getPort());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        state = ClientState.CONNECTED;

        listenerThread = new Thread(() -> {
            try {
                while(!Thread.currentThread().isInterrupted()){
                    String line = "";
                    if(socket == null || reader == null) {
                        throw new NullPointerException("Socket or Reader in listener thread is null");
                    }
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);

                        // TODO Refactor, dont hardcode this
                        if(line.startsWith("#SB") && line.contains("RAH69M") && !line.startsWith("#SBRAH69M") && line.contains("PIR")){
                            String requester = line.split(":")[0].substring(3);

                            System.out.println(requester + " requested aircraft type");

                            sendLine("#SBRAH69M:" + requester + ":PI:1:A388");
                        }
                        if(line.startsWith("$CQ") && line.contains("RAH69M") && !line.startsWith("$CQRAH69M") && line.contains("CAPS")){
                            String requester = line.split(":")[0].substring(3);

                            System.out.println(requester + " requested additional info");

                            sendLine("$CRRAH69M:SERVER:CAPS:ATCINFO=1:SECPOS=1:MODELDESC=1:ONGOINGCOORD=1");
                        }
                    }
                }
                running = false;
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        listenerThread.start();

        Runnable position_update = new Runnable() {
            @Override
            public void run() {
                aircraft_position = calcPos();
                DecimalFormat df = new DecimalFormat("#.#####");
                df.setRoundingMode(RoundingMode.CEILING);
                sendPacket(new FSDPositionUpdatePacket(new String[]{"Y", "RAH69M", "1000", "3",
                        "+" + df.format(aircraft_position.getX()),
                        "+" + df.format(aircraft_position.getY()), "15000", "324", "0", "0"}));
            }
        };

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(position_update, 0, 1, TimeUnit.SECONDS);

        return this;
    }

    public Point2D.Double calcPos(){
        double indicated_airspeed = velocity * 1.944;
        double ground_speed = indicated_airspeed + (altitude/1000*7);
        double heading = 270;
        double target_heading = Math.toDegrees(Math.atan2(direct_to.getY() - aircraft_position.getY(), direct_to.getX() - aircraft_position.getX()));
        heading = target_heading + Math.ceil(-target_heading / 360) * 360;

        double x,y;
        x = aircraft_position.getX();
        y = aircraft_position.getY();

        accelleration = (target_velocity - velocity) * 0.1;
        if(accelleration > 4) accelleration = 4;
        time = System.nanoTime() * 1E-9;
        dt = time - lastTime;
        lastTime = time;

        totalTime += dt;

        velocity += accelleration * dt;
        x += Math.cos(Math.toRadians(heading)) * velocity * dt * 0.00001 ;
        y += Math.sin(Math.toRadians(heading)) * velocity * dt * 0.00001 ;

        //System.out.println("traveled " + position + "m over " + totalTime + "s; velocity = " + velocity + "m/s; IAS = " + velocity * 1.944 + "; GS = " + (velocity * 1.944 + (altitude/1000*7)));
        //System.out.println("IAS = " + velocity * 1.944);
        //System.out.println("Lat: " + aircraft_position.getX() + "; Lon: " + aircraft_position.getY());
        System.out.println("Distance to WP: " + aircraft_position.distance(direct_to));
        return new Point2D.Double(x,y);
    }

    /**
     * Sends Packet to the Server
     * @param packet Packet to be sent to server
     */
    public void sendPacket(FSDPacket packet){
        System.out.println("Sending packet: " + packet.asString());
        sendLine(packet.asString());
    }

    /**
     * Sends Packet to the Server
     * @param line Packet to be sent to server as String
     * @throws NullPointerException Writer and Socket have to be connected
     */
    public void sendLine(String line){
        if(socket == null || writer == null)
            throw new NullPointerException("Writer or Socket cant be null");

        writer.write(line);
        writer.flush();
    }

    public void shutdown(String callsign) throws IOException, InterruptedException {
        sendPacket(new FSDRemovePilot(new String[]{callsign}));
        listenerThread.interrupt();
        listenerThread.join();
        reader.close();
        writer.close();
        socket.close();
    }

    // ## Getters <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * @return FSD Host Url
     */
    public InetSocketAddress getFsd_host() {
        return fsd_host;
    }
    /**
     * @return User VID
     */
    public String getVid() {
        return vid;
    }
    /**
     * @return User Password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @return Current State of the Client
     */
    public ClientState getState() {
        return state;
    }
}
