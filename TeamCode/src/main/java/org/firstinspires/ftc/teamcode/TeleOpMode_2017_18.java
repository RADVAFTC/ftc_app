package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.vuforia.ar.pl.SensorController;

import java.lang.Math.*;

/**
 * Created by ChristopherDeloglos on 12/3/2016.
 * <p>
 * 2017-2018!!!!!  YEAH!!!
 */
@TeleOp(name = "TeleOp Mode_2017_18", group = "Tele Operated")

public class TeleOpMode_2017_18 extends LinearOpMode {

    private DcMotor motorFrontLeft;
    private DcMotor motorRearLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorRearRight;
    private DcMotor motorLift;
    private Servo servoLeftArm;
    private Servo servoRightArm;
    //private TouchSensor button;
    private ColorSensor Color;

    private DcMotorController motorController1;
    private DcMotorController motorController2;
    private DcMotorController motorController3;

    private ServoController servoController1;

    private SensorController sensorController1;

    private static final double MOTOR_SAFE_SPEED = 0.005;
    private static final double JOYSTICK_SCALING_POWER_FACTOR = 3;

    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware Map for Motor Controllers
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

        // Hardware Map for Motors
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorRearLeft = hardwareMap.dcMotor.get("motorRearLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorRearRight = hardwareMap.dcMotor.get("motorRearRight");
        motorLift = hardwareMap.dcMotor.get("motorLift");

        // Hardware Map for Servo Controllers
        servoController1 = hardwareMap.servoController.get("Servo Controller 1");

        // Hardware Map for Servos
        servoLeftArm = hardwareMap.servo.get("Left Arm");
        servoRightArm = hardwareMap.servo.get("Right Arm");

        // Hardware Map for Color Sensor

        Color = hardwareMap.colorSensor.get("ColorSensor");

        // Servo init values

        // Sets default direction for motors
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorRearRight.setDirection(DcMotor.Direction.FORWARD);
        motorLift.setDirection((DcMotor.Direction.REVERSE));

        // Sets mode for motors
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        /*
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */

        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Sets zero power behavior
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //servoChoppingBlock.setPosition(.5);

        waitForStart();//Stops here after Init, waits for start
        //private boolean __MOTOR_WAS_JUST_PRESSED = false;
        //private boolean __MOTOR_IS_PRESSED_NOW = false;
        while (opModeIsActive()) {
            // CONTROLLER 1
            double Lpadxroot = normalizeVector(gamepad1.left_stick_x);
            double Lpadyroot = normalizeVector(gamepad1.left_stick_y);
            double Rpadxroot = normalizeVector(gamepad1.right_stick_x);
            double Rpadyroot = normalizeVector(gamepad1.right_stick_y);

            // Motion Control
           setMotors(Lpadxroot,Lpadyroot,Rpadxroot,Rpadyroot);

            //Servo Code
            if (gamepad1.a) {
                servoLeftArm.setPosition(gamepad1.left_trigger);
                servoRightArm.setPosition(1 - gamepad1.right_trigger);
            } else if (gamepad1.a == false) {

                servoLeftArm.setPosition(.5);
                servoRightArm.setPosition(.5);
            }

            //Lift Code
            if (gamepad1.right_bumper)
                motorLift.setPower(.3);
            else if (gamepad1.left_bumper)
                motorLift.setPower(-.3);
            else
                motorLift.setPower(0);

                /*
                Color.enableLed(true);  // Turn the LED on
                Thread.sleep(1000);
                Color.enableLed(false); // Turn the LED off
                */

        }
    }
    // Algorithm for setting the power of the motors to handle multidirectional motion
    private void setMotors (double left_x_val, double left_y_val, double right_x_val, double right_y_val)
    {
        motorFrontLeft.setPower((1 * left_y_val) + (1 * left_x_val) + -1 * (right_x_val));
        motorRearLeft.setPower((1 * left_y_val) + (-1 * left_x_val) + -1 * (right_x_val));
        motorFrontRight.setPower((1 * left_y_val) + (-1 * left_x_val) + 1 * (right_x_val));
        motorRearRight.setPower((1 * left_y_val) + (1 * left_x_val) + 1 * (right_x_val));

    }

    // Normalizes the values of the joystick controls
    private double normalizeVector (double vector)
    {
        double normalizedVector;
        if (vector < 0)
            normalizedVector = -Math.sqrt(Math.abs(vector));
        else
            normalizedVector = Math.sqrt(vector);
        normalizedVector = Math.pow(normalizedVector, 3);
        return (normalizedVector);
    }


}
