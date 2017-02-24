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
    private static final double TICKS_PER_REV = 1557; // Number of ticks in one motor turn
    private static final double REVS_PER_METER = 3.1246; // Number of times the wheel turns after the robot moves 1 meter
    private static final double GEAR_RATIO = 1; // Number of times the motor rotates for 1 rotation of the wheel
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL_SPEED = 1;

    //Motor Configs//
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorBottomSpinner;
    private DcMotor motorPlexiglass;
    private Servo servoBeacon;
    private DcMotor motorSpanker;


    private DcMotorController motorController1;
    private DcMotorController motorController2;
    private DcMotorController motorController3;

    //Servo Configs//
    private ServoController servoController1;

    double[] motorLeftStepsTicks =  {3, 6, 12};
    double[] motorRightStepsTicks = {3, 6, 12};

    double[] leftmotorStepsSpeed = {1, 1, 1};
    double[] rightmotorStepsSpeed ={1, 1, 1};

    double[] motorLeftStepsTicks2 =  {3, 6, 12};
    double[] motorRightStepsTicks2 = {3, 6, 12};

    double[] leftmotorStepsSpeed2 = {1, 1, 1};
    double[] rightmotorStepsSpeed2 ={1, 1, 1};


    public void runOpMode() throws InterruptedException {// Add this for default exactly as is

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

        motorBottomSpinner.setDirection(DcMotor.Direction.REVERSE);
        motorPlexiglass.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setDirection(DcMotor.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        for( int i = 0 ; i<motorLeftStepsTicks.length && opModeIsActive(); i++ ){
            motorLeft.setTargetPosition(motorLeft.getCurrentPosition() + GetTicksFromInches(motorLeftStepsTicks[i]));
            motorRight.setTargetPosition(motorRight.getCurrentPosition() + GetTicksFromInches(motorRightStepsTicks[i]));
            motorLeft.setPower(leftmotorStepsSpeed[i]);
            motorRight.setPower(rightmotorStepsSpeed[i]);
            while(motorLeft.isBusy() || motorRight.isBusy());
        }




        Thread.sleep(1000);
        motorPlexiglass.setPower(1);
        Thread.sleep(500);
        motorPlexiglass.setPower(0);
        motorBottomSpinner.setPower(1);
        Thread.sleep(4000);
        motorPlexiglass.setPower(-.1);
        Thread.sleep(1000);
        motorBottomSpinner.setPower(0);
        motorPlexiglass.setPower(1);
        Thread.sleep(500);
        motorPlexiglass.setPower(0);


        for( int i = 0 ; i<motorLeftStepsTicks2.length && opModeIsActive(); i++ ){
            motorLeft.setTargetPosition(motorLeft.getCurrentPosition() + GetTicksFromInches(motorLeftStepsTicks2[i]));
            motorRight.setTargetPosition(motorRight.getCurrentPosition() + GetTicksFromInches(motorRightStepsTicks2[i]));
            motorLeft.setPower(leftmotorStepsSpeed2[i]);
            motorRight.setPower(rightmotorStepsSpeed2[i]);
            while(motorLeft.isBusy() || motorRight.isBusy());
        }

        /*
        motorLeft.setPower(1);
        while(opModeIsActive()){
            motorLeft.setTargetPosition(Math.round(1557*10));
            while(motorLeft.isBusy());
        }
        */
        //stop();

        /*
        motorPlexiglass.setTargetPosition(motorPlexiglass.getCurrentPosition() + motorPlexiglassStepsTicks[i] );
        motorPlexiglass.setPower(motorPlexiglassStepsSpeed[i]);
        while(motorPlexiglass.isBusy());


        motorBottomSpinner.setTargetPosition(motorBottomSpinner.getCurrentPosition() + motorBottomSpinnerStepsTicks[i] );
        motorBottomSpinner.setPower(motorBottomSpinnerStepsSpeed[i]);
        while(motorBottomSpinner.isBusy());
        */



        //while(opModeIsActive()){}
        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorBottomSpinner.setPower(0);
        motorPlexiglass.setPower(0);
        motorSpanker.setPower(0);
        servoBeacon.setPosition(0.5);


        /*
        for(int i = 0;i<10;i++){
            TurnDistance(1,GetTicksFromFeet(1),GetTicksFromFeet(1));
        }
        for(int i = 0;i<10;i++){
            motorLeft.setDirection(DcMotor.Direction.REVERSE);
            motorRight.setDirection(DcMotor.Direction.FORWARD);
            TurnDistance(1,GetTicksFromFeet(1),GetTicksFromFeet(1));
        }
        */
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
    public int GetTicksFromFeet(double Feet) {
        return (GetTicksFromMeters(Feet/3.28084));
    }
    public int GetTicksFromInches(double Inches) {
        return (GetTicksFromMeters((Inches/12)/3.28084));
    }

    public double GetFeetFromTicks(int Ticks) {
        return(Ticks*3.28084/(GEAR_RATIO * (TICKS_PER_REV * REVS_PER_METER)));
    }

    public double GetMetersFromTicks(int Ticks){
        return(Ticks/(GEAR_RATIO * (TICKS_PER_REV * REVS_PER_METER)));
    }
}
