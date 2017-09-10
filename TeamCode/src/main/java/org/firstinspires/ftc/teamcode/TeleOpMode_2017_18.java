package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;


/**
 * Created by ChristopherDeloglos on 12/3/2016.
 *
 * 2017-2018!!!!!  YEAH!!!
 */
@TeleOp(name = "TeleOp Mode_2017_18", group = "Tele Operated")

public class TeleOpMode_2017_18 extends LinearOpMode {

    private DcMotor motorFrontLeft;
    private DcMotor motorRearLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorRearRight;
    // private DcMotor motor5;
    //private Servo servo1;


    private DcMotorController motorController1;
    private DcMotorController motorController2;
    //private DcMotorController motorController3;

    //private ServoController servoController1;

    private static final double MOTOR_SAFE_SPEED = 0.3;
    private static final double MOTOR_LAUNCHER_SPEED = 0.8;
    private static final double MOTOR_LAUNCHER_SPEED_BACKOFF = -0.3;
    private static final double MOTOR_STOP = 0;
    private static final double MOTOR_FULL_SPEED = 1;
    private static final double MOTOR_SPANKER_SPEED = 0.7;
    private static final double ACCELERATION_RATE = 0.001;
    private static final boolean LEFT = true;
    private static final boolean RIGHT = false;
    private static final double BEACON_SERVO_WIGGLE_INC = 0.03;
    private boolean JUST_PRESSED = false;
    private boolean PRESSED_NOW = false;
    private boolean SERVO_DIRECTION = LEFT;
    private boolean __MOTOR_WAS_JUST_PRESSED = false;
    private boolean __MOTOR_IS_PRESSED_NOW = false;
    private static final double SERVO_MAX_POSITION = 0.6;
    private static final double SERVO_MIN_POSITION = 0.3;

    @Override
    public void runOpMode() throws InterruptedException {
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        //motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorRearLeft = hardwareMap.dcMotor.get("motorRearLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorRearRight = hardwareMap.dcMotor.get("motorRearRight");
        //motor5 = hardwareMap.dcMotor.get("motor5");

        //servoController1 = hardwareMap.servoController.get("Servo Controller 1");

        //servo1 = hardwareMap.servo.get("Servo 1");


        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorRearRight.setDirection(DcMotor.Direction.REVERSE);
        //motor5.setDirection(DcMotor.Direction.REVERSE);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        //motor5.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //servoBeacon.setPosition(0.5);

        waitForStart();//Stops here after Init, waits for start
        //private boolean __MOTOR_WAS_JUST_PRESSED = false;
        //private boolean __MOTOR_IS_PRESSED_NOW = false;
        while (opModeIsActive()) {
            // CONTROLLER 1
            /*
            //Forward and Backward Controls
            motorFrontLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearLeft.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorFrontRight.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
             */
            //Sideways controls
            if (gamepad1.dpad_left == true) {
                motorFrontLeft.setPower(-.2);
                motorRearLeft.setPower(.2);
                motorFrontRight.setPower(-.2);
                motorRearRight.setPower(.2);

            } else if (gamepad1.dpad_right == true) {
                motorFrontLeft.setPower(.2);
                motorRearLeft.setPower(-.2);
                motorFrontRight.setPower(.2);
                motorRearRight.setPower(-.2);


            } else {
                motorFrontLeft.setPower(gamepad1.left_stick_y);
                motorRearLeft.setPower(gamepad1.left_stick_y);
                motorFrontRight.setPower(-gamepad1.right_stick_y);
                motorRearRight.setPower(-gamepad1.right_stick_y);
            }

           /* else if (gamepad1.dpad_right == false) {
                motorFrontLeft.setPower(gamepad1.left_stick_y);
                motorRearLeft.setPower(gamepad1.right_stick_y);
                motorFrontRight.setPower(gamepad1.left_stick_y);
                motorRearRight.setPower(gamepad1.right_stick_y);

            }

            */
/*
            if (gamepad1.left_bumper == true) {
                PRESSED_NOW = true;
            }
            else{
                PRESSED_NOW = false;
            }
            if (JUST_PRESSED == true && PRESSED_NOW == false){
                motor5.setPower(MOTOR_STOP);
            }
            else if (JUST_PRESSED == false && PRESSED_NOW == true){
                motor5.setPower(MOTOR_SPANKER_SPEED);
            }
            JUST_PRESSED = PRESSED_NOW;

            if (gamepad1.right_bumper == true) {
                PRESSED_NOW = true;
            }
            else{
                PRESSED_NOW = false;
            }
            if (JUST_PRESSED == true && PRESSED_NOW == false){
                motor5.setPower(MOTOR_STOP);
            }
            else if (JUST_PRESSED == false && PRESSED_NOW == true){
                motor5.setPower(-MOTOR_SPANKER_SPEED);
            }
            JUST_PRESSED = PRESSED_NOW;


            // CONTROLLER 2


        }
        motorFrontLeft.setPower(0);
        motorRearLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorRearRight.setPower(0);
        //motor5.setPower(0);
        //servo1.setPosition(0.5);

    }


   /* private double setMotorSpeed(double desiredSpeed) {
        double curSpeed = motorFrontLeft.getPower();

        if (desiredSpeed > curSpeed) {
            if ((desiredSpeed - curSpeed) < ACCELERATION_RATE) {
                curSpeed = desiredSpeed;
            } else {
                curSpeed += ACCELERATION_RATE;
            }
        } else if (desiredSpeed < curSpeed)
            if ((curSpeed - desiredSpeed) < ACCELERATION_RATE) {
                curSpeed = desiredSpeed;
            } else {
                curSpeed -= ACCELERATION_RATE;
            }
        return (curSpeed);
    }
/*
    private void wiggleBeaconButtonBanger() {
        telemetry.addData("ServoPosition",servoBeacon.getPosition());
        if (servoBeacon.getPosition() >= SERVO_MAX_POSITION) {
            SERVO_DIRECTION = RIGHT;
        }
        if (servoBeacon.getPosition() <= SERVO_MIN_POSITION){
            SERVO_DIRECTION = LEFT;
        }

        if (SERVO_DIRECTION == RIGHT) {
            servoBeacon.setPosition(servoBeacon.getPosition() - BEACON_SERVO_WIGGLE_INC);
        }
        else {
            servoBeacon.setPosition(servoBeacon.getPosition() + BEACON_SERVO_WIGGLE_INC);
        }
    }
    */
        }

    }}