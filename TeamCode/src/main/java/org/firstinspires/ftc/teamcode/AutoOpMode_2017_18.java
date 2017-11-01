package org.firstinspires.ftc.teamcode; // Add this for default

import com.qualcomm.robotcore.eventloop.opmode.Autonomous; // Add this for default if AutoOpMode
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Add this for default
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;


/**
 * Created by ChristopherDeloglos on 12/3/2016.
 * Purpose: Educational model for robot programming
 */
@Autonomous(name = "AutoOp Mode 2017-18", group = "Autonomous")
// Add this for default (Change name)

public class AutoOpMode_2017_18 extends LinearOpMode // Add this for default (Change AutoOpMode to match filename/classname)
{
    private static final double TICKS_PER_REV = 1680; // Number of ticks in one motor turn
    private static final double REVS_PER_METER = 2.15; // Number of times the wheel turns after the robot moves 1 meter
    private static final double GEAR_RATIO = 1; // Number of times the motor rotates for 1 rotation of the wheel
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL_SPEED = 1;

    //Motor Configs//
    private DcMotor motorFrontLeft;
    private DcMotor motorRearLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorRearRight;
    private DcMotor motorLift;

    //Motor Controller Configs//
    private DcMotorController motorController1;
    private DcMotorController motorController2;
    private DcMotorController motorController3;

    //Servo Configs//
    private Servo servoChoppingBlock;
    private Servo servoLeftArm;
    private Servo servoRightArm;

    //Servo Controller Config//
    private ServoController servoController1;



    double[] motorLeftStepsTicks = {1680};
    double[] motorRightStepsTicks = {1680};

    double[] leftmotorStepsSpeed = {.3};
    double[] rightmotorStepsSpeed = {.3};

    double[] motorLeftStepsTicks2 = {1680};
    double[] motorRightStepsTicks2 = {1680};

    double[] leftmotorStepsSpeed2 = {.5};
    double[] rightmotorStepsSpeed2 = {.5};


    public void runOpMode() throws InterruptedException {// Add this for default exactly as is

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

        // Sets default direction for motors
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorRearRight.setDirection(DcMotor.Direction.FORWARD);
        motorLift.setDirection(DcMotor.Direction.REVERSE);


        // Sets mode for motors
        /*
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        int delay = 3000;
        waitForStart();
        moveForward(2000,.1);
        Thread.sleep(delay);
        moveRotate(2000,.1);
        Thread.sleep(delay);
        moveSideways(2000,.1);
        Thread.sleep(delay);

        /*

        while (gamepad1.a == false) ;


        motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() - 1000);
        motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() - 1000);
        motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() - 1000);
        motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() - 1000);
        while (motorFrontLeft.getCurrentPosition() != motorFrontLeft.getTargetPosition()) ;
        while (gamepad1.a == false) ;


        motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + 3000);
        motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + 3000);
        motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() + 3000);
        motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() + 3000);
        while (motorFrontLeft.getCurrentPosition() != motorFrontLeft.getTargetPosition()) ;
        while (gamepad1.a == false) ;
        */

    }

    private void moveForward(int motorsTicks, double motorsPower)
    {
        motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + motorsTicks);
        motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + motorsTicks);
        motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() + motorsTicks);
        motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() + motorsTicks);

        motorFrontLeft.setPower(motorsPower);
        motorRearLeft.setPower(motorsPower);
        motorFrontRight.setPower(motorsPower);
        motorRearRight.setPower(motorsPower);
        while (motorFrontLeft.getCurrentPosition() != motorFrontLeft.getTargetPosition()) ;


    }
    private void moveRotate(int motorsTicks, double motorsPower)
    {
        motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + -motorsTicks);
        motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + -motorsTicks);
        motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() + motorsTicks);
        motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() + motorsTicks);

        motorFrontLeft.setPower(motorsPower);
        motorRearLeft.setPower(motorsPower);
        motorFrontRight.setPower(motorsPower);
        motorRearRight.setPower(motorsPower);
        while (motorFrontLeft.getCurrentPosition() != motorFrontLeft.getTargetPosition());


    }
    private void moveSideways(int motorsTicks, double motorsPower){

        motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + motorsTicks);
        motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + -motorsTicks);
        motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() + -motorsTicks);
        motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() + motorsTicks);

        motorFrontLeft.setPower(motorsPower);
        motorRearLeft.setPower(motorsPower);
        motorFrontRight.setPower(motorsPower);
        motorRearRight.setPower(motorsPower);
        while (motorFrontLeft.getCurrentPosition() != motorFrontLeft.getTargetPosition()) ;

    }
}