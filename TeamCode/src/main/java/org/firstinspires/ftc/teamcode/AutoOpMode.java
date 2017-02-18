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
@Autonomous(name = "AutoOp Mode", group = "Autonomous") // Add this for default (Change name)

public class AutoOpMode extends LinearOpMode // Add this for default (Change AutoOpMode to match filename/classname)
{
    private static final double TICKS_PER_REV = 360; // Number of ticks in one motor turn
    private static final double REVS_PER_METER = 10; // Number of times the wheel turns after the robot moves 1 meter
    private static final double GEAR_RATIO = 1; // Number of times the motor rotates for 1 rotation of the wheel
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL_SPEED = 1;

    //Motor Configs//
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorTopSpinner;
    private DcMotor motorBottomSpinner;

    private DcMotorController motorController1;
    private DcMotorController motorController2;
    
    //Servo Configs//
    private ServoController servoController1;

    private Servo servoBeacon;
    private Servo servoSpanker;

    int[] motorLeftStepsTicks = {100, 100, 100, 100, 200, -200, 100, 100, 100, 100};
    int[] motorRightStepsTicks = {100, 0, 100, 0, 200, -200, 100, 0, 100, 0};
    double[] motorStepsSpeed = {1, 1, 1, 1, 1, 1, 1, 1, .5, 1};

    public void runOpMode() throws InterruptedException {// Add this for default exactly as is

        //int stuffs = thisismyint[0];
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorTopSpinner = hardwareMap.dcMotor.get("motorTopSpinner");
        motorBottomSpinner = hardwareMap.dcMotor.get("motorBottomSpinner");
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");

        servoController1 = hardwareMap.servoController.get ("Servo Controller 1");
        servoBeacon = hardwareMap.servo.get ("Servo Beacon");
        servoSpanker = hardwareMap.servo.get("Servo Spanker");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();


        for(int i = 0;i<10;i++){
            TurnDistance(1,5000,5000);
            wait(1000);
        }

        //while(opModeIsActive()){}

        /*
        for (int counter = 0; counter < motorLeftStepsTicks.length; counter++) {
            TurnDistance(motorStepsSpeed[counter], motorLeftStepsTicks[counter], motorRightStepsTicks[counter]);
        }
        */


    }

    public void DriveForwardDistance(double power, int ticks) {
        ResetEncodersforRunToPosition(ticks, ticks);
        DriveFoward(power);

        while (motorLeft.isBusy() || motorRight.isBusy()) {
            if (!motorLeft.isBusy()) {
                motorLeft.setPower(DCMOTOR_SPEED_STOPPED);
            }
            if (!motorRight.isBusy()) {
                motorRight.setPower(DCMOTOR_SPEED_STOPPED);
            }
        }
        StopMotors();
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void TurnDistance(double power, int ticksLeft, int ticksRight) {
        ResetEncodersforRunToPosition(ticksLeft, ticksRight);
        Turn(power, power);

        while (motorLeft.isBusy() && motorRight.isBusy()) {
            if (!motorLeft.isBusy()) {
                motorLeft.setPower(DCMOTOR_SPEED_STOPPED);
            }
            if (!motorRight.isBusy()) {
                motorRight.setPower(DCMOTOR_SPEED_STOPPED);
            }
        }
        StopMotors();
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void ResetEncodersforRunToPosition(int leftTicks, int rightTicks) {
        // Reset encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        motorLeft.setTargetPosition(leftTicks);
        motorRight.setTargetPosition(rightTicks);

        // Set to RUN_TO_POSITION mode
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void DriveFoward(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    private void Turn(double leftPower, double rightPower) {
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
    }

    public void StopMotors() {
        motorLeft.setPower(DCMOTOR_SPEED_STOPPED);
        motorRight.setPower(DCMOTOR_SPEED_STOPPED);
    }

    public int GetTicksFromMeters(double Meters) {
        Double ticks = GEAR_RATIO * (TICKS_PER_REV * REVS_PER_METER * Meters);
        return (ticks.intValue());
    }
}
