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
    private Servo servoChoppingBlock;


    private DcMotorController motorController1;
    private DcMotorController motorController2;
    //private DcMotorController motorController3;

    private ServoController servoController1;

    private static final double MOTOR_SAFE_SPEED = 0.005;











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

        servoController1 = hardwareMap.servoController.get("Servo Controller 1");

        servoChoppingBlock = hardwareMap.servo.get("Chopping Block");


        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorRearRight.setDirection(DcMotor.Direction.FORWARD);
        //motor5.setDirection(DcMotor.Direction.REVERSE);


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
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //motor5.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //servoChoppingBlock.setPosition(.5);

        waitForStart();//Stops here after Init, waits for start
        //private boolean __MOTOR_WAS_JUST_PRESSED = false;
        //private boolean __MOTOR_IS_PRESSED_NOW = false;
        while (opModeIsActive()) {
            // CONTROLLER 1

            //Forward and Backward Controls
            motorFrontLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorFrontRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);

            //Sideways controls

           /* motorFrontRight.setPower(0);

            if (gamepad2.a == true){

                servoChoppingBlock.setPosition(0);
            }
            else if (gamepad2.a == false){

                servoChoppingBlock.setPosition(.8);
            }
            */

            /*if (gamepad1.dpad_left == true) {
                motorFrontLeft.setPower(MOTOR_SAFE_SPEED);
                motorRearLeft.setPower(MOTOR_SAFE_SPEED);
                motorFrontRight.setPower(-MOTOR_SAFE_SPEED);
                motorRearRight.setPower(-MOTOR_SAFE_SPEED);

            } else if (gamepad1.dpad_right == true) {
                motorFrontLeft.setPower(1);
                motorRearLeft.setPower(1);
                motorFrontRight.setPower(1);
                motorRearRight.setPower(1);


            }else {
                motorFrontLeft.setPower(0);
                motorRearLeft.setPower(0);
                motorFrontRight.setPower(-0);
                motorRearRight.setPower(-0);

                /*else {
                motorFrontLeft.setPower(gamepad1.left_stick_y);
                motorRearLeft.setPower(gamepad1.left_stick_y);
                motorFrontRight.setPower(-gamepad1.right_stick_y);
                motorRearRight.setPower(-gamepad1.right_stick_y);
            */

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

    }