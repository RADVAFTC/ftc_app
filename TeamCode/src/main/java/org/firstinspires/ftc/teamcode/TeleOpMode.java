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
 */
@TeleOp(name = "TeleOp Mode", group = "Tele Operated")

public class TeleOpMode extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorBottomSpinner;
    private DcMotor motorPlexiglass;
    private DcMotor motorSpanker;
    private Servo servoBeacon;


    private DcMotorController motorController1;
    private DcMotorController motorController2;
    private DcMotorController motorController3;

    private ServoController servoController1;

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
    private static final double SERVO_MAX_POSITION = 0.6;
    private static final double SERVO_MIN_POSITION = 0.3;

    @Override
    public void runOpMode() throws InterruptedException {
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorBottomSpinner = hardwareMap.dcMotor.get("motorBottomSpinner");
        motorPlexiglass = hardwareMap.dcMotor.get("motorPlexiglass");
        motorSpanker = hardwareMap.dcMotor.get("motorSpanker");

        servoController1 = hardwareMap.servoController.get("Servo Controller 1");

        servoBeacon = hardwareMap.servo.get("Servo 1");


        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorPlexiglass.setDirection(DcMotor.Direction.REVERSE);
        motorBottomSpinner.setDirection(DcMotor.Direction.REVERSE);
        motorSpanker.setDirection(DcMotor.Direction.REVERSE);


        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBottomSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorPlexiglass.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorSpanker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoBeacon.setPosition(0.5);

        waitForStart();//Stops here after Init, waits for start

        while (opModeIsActive()) {
            // CONTROLLER 1
            motorLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);

            if (gamepad1.left_bumper == true) {
                PRESSED_NOW = true;
            }
            else{
                PRESSED_NOW = false;
            }
            if (JUST_PRESSED == true && PRESSED_NOW == false){
                motorSpanker.setPower(MOTOR_STOP);
            }
            else if (JUST_PRESSED == false && PRESSED_NOW == true){
                motorSpanker.setPower(MOTOR_SPANKER_SPEED);
            }
            JUST_PRESSED = PRESSED_NOW;

            if (gamepad1.right_bumper == true) {
                PRESSED_NOW = true;
            }
            else{
                PRESSED_NOW = false;
            }
            if (JUST_PRESSED == true && PRESSED_NOW == false){
                motorSpanker.setPower(MOTOR_STOP);
            }
            else if (JUST_PRESSED == false && PRESSED_NOW == true){
                motorSpanker.setPower(-MOTOR_SPANKER_SPEED);
            }
            JUST_PRESSED = PRESSED_NOW;


            // CONTROLLER 2
            if (gamepad2.right_bumper == true) {
                motorPlexiglass.setPower(MOTOR_LAUNCHER_SPEED);
            } else if (gamepad2.left_bumper == true) {
                motorPlexiglass.setPower(MOTOR_LAUNCHER_SPEED_BACKOFF);
            } else {
                motorPlexiglass.setPower(MOTOR_STOP);
            }

            if (gamepad2.a == true) {
                motorBottomSpinner.setPower(MOTOR_FULL_SPEED);
            }
            if (gamepad2.b == true) {
                motorBottomSpinner.setPower(MOTOR_STOP);
            }

            if(gamepad2.y == true){
                wiggleBeaconButtonBanger();
            }
        }
        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorBottomSpinner.setPower(0);
        motorPlexiglass.setPower(0);
        motorSpanker.setPower(0);
        servoBeacon.setPosition(0.5);

    }


    private double setMotorSpeed(double desiredSpeed) {
        double curSpeed = motorLeft.getPower();

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
}

