package com.luislucas;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static javafx.application.Application.launch;

public class Main extends Application {
    /* The code needs some context to fully understand everything
    Port 14 will be the right legs motor
    Port 13 will be the left legs motor
    Port 2 will be the right hand
    Port 1 will be the right wrist (to turn door lock)
    */


    //This simply creates public variables which I can use anywhere
    public SerialPort comPort = SerialPort.getCommPorts()[0]; // Gets the port the serial device is plugged into
    public OutputStream outputStream = comPort.getOutputStream(); // Creates an output stream that way I can control the motors and latches.
    public PrintStream writer = new PrintStream(outputStream); // Creates the writer so I can easily print to the devices.

    // Declares the 3 buttons I will be using.
    Button lights;
    Button doors;
    Button all;

    // These are the methods (commands) I am creating to clean up the command and make this is so much easier to read and do.
    public void moveForward(int revolutions) throws InterruptedException { // The numbers for these has to have one negative and one positive since they are reflected
        this.writer.println("14rotate" + revolutions * 360 + "\\r\\n"); // if I wanted to move 1 revolution, the output would be "14rotate360".
        this.writer.println("13rotate" + revolutions * -360 + "\\r\\n"); // The format for the motors is the number the motor is connected to, the command, and lastly, the degrees to spin.
        Thread.sleep(125); // I have to have a 125 ms delay because the usb controller can't take anymore information in that time.
    }

    public void moveBackward(int revolutions) throws InterruptedException { // The numbers for these has to have one negative and one positive since they are reflected
        this.writer.println("14rotate" + revolutions * -360 + "\\r\\n"); // if I wanted to move 1 revolution, the output would be "14rotate360".
        this.writer.println("13rotate" + revolutions * 360 + "\\r\\n"); // The format for the motors is the number the motor is connected to, the command, and lastly, the degrees to spin.
        Thread.sleep(125); // I have to have a 125 ms delay because the usb controller can't take anymore information in that time.
    }

    public void turnRight(double turnPercentage) throws InterruptedException {
        this.writer.println("14rotate" + turnPercentage * 360 + "\\r\\n");
        this.writer.println("13rotate" + turnPercentage * 360 + "\\r\\n");
        Thread.sleep(125);
    }

    public void turnLeft(double turnPercentage) throws InterruptedException {
        this.writer.println("14rotate" + turnPercentage * -360 + "\\r\\n");
        this.writer.println("13rotate" + turnPercentage * -360 + "\\r\\n");
        Thread.sleep(125);
    }

    public void turnOffLight() throws InterruptedException {
        this.writer.println("close2\\r\\n");
        this.writer.println("open2\\r\\n");
        Thread.sleep(125);
    }

    public void lockDoor() throws InterruptedException {
        this.writer.println("close2\\r\\n");
        this.writer.println("1rotate90\\r\\n");
        this.writer.println("open2\\r\\n");
        this.writer.println("1rotate-90\\r\\n");
        Thread.sleep(125);
    }


    // This is the "main" code. It reads the launch args and then runs the actual main code,
    public static void main(String[] args) {
        // This creates the GUI window which gets the information from public void start
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        // Setting up the serial device that way I can control it. This declares all the paramaters like the baudrate, parity, flow control, etc.
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setBaudRate(38400);
        comPort.setParity(SerialPort.NO_PARITY);
        comPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        comPort.setNumDataBits(8);

        primaryStage.setTitle("Raindrop"); // Sets the title of the application

        lights = new Button(); // Declares this is a button
        lights.setText("Turn off all lights"); // Setting all paramaters
        lights.setOnAction(e -> { // Creating the actual code for when the button is pressed. The 'e ->' is a lamba expression which just makes the clean cleaner and easier to read. It makes you it so you dont have to say it was a click
            try {
                moveForward(5); // Move forward 5 revolutions. This is about 30 inches since the circumference of the wheel is 6 inches.
                turnLeft(0.5);
                turnOffLight(); // Light A
                turnRight(1);
                moveForward(32);
                turnRight(0.5);
                moveForward(22);
                turnOffLight(); // Light B
                turnRight(0.5);
                moveForward(2);
                turnLeft(0.5);
                moveForward(16);
                turnLeft(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light C
                turnLeft(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(4);
                turnRight(0.5);
                moveForward(4);
                turnRight(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light D
                turnRight(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(2);
                turnRight(0.5);
                moveForward(2);
                turnRight(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light E
                turnRight(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(1);
                turnRight(0.5);
                moveForward(18);
                turnLeft(0.5);
                moveForward(10);
                turnOffLight(); // Light F
                turnLeft(1);
                moveForward(20);
                turnLeft(0.5);
                moveForward(5);
                turnRight(0.5);
                turnOffLight(); // Light G
                turnLeft(0.5);
                moveForward(20);
                turnRight(0.5);
                turnOffLight(); // Light H
                turnRight(1);
                moveForward(10);
                turnRight(0.5);
                moveForward(20);
                turnOffLight(); // Light I
                turnRight(1);
                moveForward(24);
                turnRight(0.5);
                moveForward(25);
                turnRight(0.5);
                turnOffLight(); // Light J
                turnRight(1);
                moveForward(6);
                turnRight(0.5);
                moveForward(4);
                turnLeft(0.5);
                moveForward(3);
                turnLeft(0.5);
                turnOffLight(); // Light K
                turnLeft(1);
                moveForward(24);
                turnRight(0.5);
                moveForward(21);
                turnRight(0.5);
                turnOffLight(); // Light L
                turnRight(1);
                moveForward(22);
                turnOffLight(); // Light M
                System.out.println("Turned off all Lights. Returning home.");

                // Now it's time to return home. Yay!
                turnRight(1);
                moveForward(60);
                turnRight(0.5);
                moveForward(20);
                turnRight(0.5);
                moveForward(20);
                turnLeft(0.5);
                moveForward(18);
                turnRight(0.5);
                System.out.println("Returned home.");

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        doors = new Button(); // Declares this is a button
        doors.setText("Close all doors"); // Setting all paramaters
        doors.setOnAction(e -> { // Creating the actual code for when the button is pressed. The 'e ->' is a lamba expression which just makes the clean cleaner and easier to read. It makes you it so you dont have to say it was a click
            try {
                moveForward(5);
                turnLeft(0.5);
                lockDoor(); // Door 1
                turnRight(1);
                moveForward(20);
                turnRight(0.5);
                moveForward(35);
                turnLeft(0.5);
                moveForward(10);
                turnRight(0.5);
                moveForward(15);
                turnRight(0.5);
                moveForward(15);
                turnLeft(0.5);
                moveForward(20);
                turnRight(0.5);
                lockDoor(); // Door 2
                turnRight(1);
                moveForward(20);
                turnRight(0.5);
                moveForward(10);
                turnLeft(0.5);
                moveForward(15);
                turnRight(0.5);
                lockDoor(); // Door 3. Door 4 is connected to door 4 so it doesn't lock.
                turnLeft(0.5);
                moveForward(20);
                lockDoor(); // Door 5
                System.out.println("Locked all doors. Returning home.");

                // Now it's time to return home. Yay!
                turnRight(1);
                moveForward(60);
                turnRight(0.5);
                moveForward(20);
                turnRight(0.5);
                moveForward(20);
                turnLeft(0.5);
                moveForward(18);
                turnRight(0.5);
                System.out.println("Returned home.");

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        all = new Button(); // Declares this is a button
        all.setText("Turn off all lights and close all doors"); // Setting all paramaters
        all.setOnAction(e -> { // Creating the actual code for when the button is pressed. The 'e ->' is a lamba expression which just makes the clean cleaner and easier to read. It makes you it so you dont have to say it was a click
            try {
                moveForward(5); // Move forward 5 revolutions. This is about 30 inches since the circumference of the wheel is 6 inches.
                turnLeft(0.5);
                turnOffLight(); // Light A
                lockDoor(); // Door 1
                turnRight(1);
                moveForward(32);
                turnRight(0.5);
                moveForward(22);
                turnOffLight(); // Light B
                turnRight(0.5);
                moveForward(2);
                turnLeft(0.5);
                moveForward(16);
                turnLeft(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light C
                turnLeft(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(4);
                turnRight(0.5);
                moveForward(4);
                turnRight(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light D
                turnRight(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(2);
                turnRight(0.5);
                moveForward(2);
                turnRight(0.5);
                moveForward(1);
                turnRight(0.5);
                turnOffLight(); // Light E
                turnRight(1);
                moveForward(1);
                turnLeft(0.5);
                moveForward(1);
                turnRight(0.5);
                moveForward(18);
                turnLeft(0.5);
                moveForward(10);
                turnOffLight(); // Light F
                turnLeft(1);
                moveForward(20);
                turnLeft(0.5);
                moveForward(5);
                turnRight(0.5);
                turnOffLight(); // Light G
                lockDoor(); // Door 2
                turnLeft(0.5);
                moveForward(20);
                turnRight(0.5);
                turnOffLight(); // Light H
                turnRight(1);
                moveForward(10);
                turnRight(0.5);
                moveForward(20);
                turnOffLight(); // Light I
                turnRight(1);
                moveForward(24);
                turnRight(0.5);
                moveForward(25);
                turnRight(0.5);
                turnOffLight(); // Light J
                lockDoor(); // Door 3. Door 4 doesn't lock.
                turnRight(1);
                moveForward(6);
                turnRight(0.5);
                moveForward(4);
                turnLeft(0.5);
                moveForward(3);
                turnLeft(0.5);
                turnOffLight(); // Light K
                turnLeft(1);
                moveForward(24);
                turnRight(0.5);
                moveForward(21);
                turnRight(0.5);
                turnOffLight(); // Light L
                turnRight(1);
                moveForward(22);
                turnOffLight(); // Light M
                lockDoor(); // Door 5
                System.out.println("Turned off all Lights and locked all doors. Returning home.");

                // Now it's time to return home. Yay!
                turnRight(1);
                moveForward(60);
                turnRight(0.5);
                moveForward(20);
                turnRight(0.5);
                moveForward(20);
                turnLeft(0.5);
                moveForward(18);
                turnRight(0.5);
                System.out.println("Returned home.");

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // This actually organizes and creates the GUI itself
        VBox layout = new VBox(5); // Creates a new layout (vertical box) which has a 10 pixel spacing, meaning that the distance between the lights and door button is 10 pixels.
        layout.getChildren().add(lights); // Adding the button to the VBox
        layout.getChildren().add(doors); // Adding the button to the VBox
        layout.getChildren().add(all); // Adding the button to the VBox
        Scene scene = new Scene(layout); // Creating the scene itself. The whole GUI is the scene. The layout is the box inside of it.
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png"))); // Creates the icon for the application
        primaryStage.setScene(scene); // Sets the scene to the scene made in the previous line
        primaryStage.show(); // Shows the scene
    }
}
