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
@Autonomous(name = "AutoOp Mode 2017-18", group = "Autonomous") // Add this for default (Change name)

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
    private Servo servoChoppingBlock;


    private DcMotorController motorController1;
    private DcMotorController motorController2;

    //Servo Configs//
    private ServoController servoController1;

    double[] motorLeftStepsTicks =  {1680};
    double[] motorRightStepsTicks = {1680};

    double[] leftmotorStepsSpeed = {.3};
    double[] rightmotorStepsSpeed ={.3};

    double[] motorLeftStepsTicks2 =  {1680};
    double[] motorRightStepsTicks2 = {1680};

    double[] leftmotorStepsSpeed2 = {.5};
    double[] rightmotorStepsSpeed2 ={.5};


    public void runOpMode() throws InterruptedException {// Add this for default exactly as is

        // Hardware Map for Motor Controllers
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");


        // Hardware Map for Motors
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorRearLeft = hardwareMap.dcMotor.get("motorRearLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorRearRight = hardwareMap.dcMotor.get("motorRearRight");

        // Hardware Map for Servo Controllers
        servoController1 = hardwareMap.servoController.get("Servo Controller 1");

        // Hardware Map for Servos
        servoChoppingBlock = hardwareMap.servo.get("Chopping Block");

        // Sets default direction for motors
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorRearRight.setDirection(DcMotor.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);

        // Sets mode for motors
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


        }

    }






       // waitForStart();

        //This is the command for the first steps to be completed (last year)

        /*
        for( int i = 0 ; i<motorLeftStepsTicks.length && opModeIsActive(); i++ ){


            motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + GetTicksFromInches(motorLeftStepsTicks[i]));
            motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + GetTicksFromInches(motorLeftStepsTicks[i]));
            motorFrontRight.setTargetPosition(motorFrontRight.getCurrentPosition() + GetTicksFromInches(motorRightStepsTicks[i]));
            motorRearRight.setTargetPosition(motorRearRight.getCurrentPosition() + GetTicksFromInches(motorRightStepsTicks[i]));




            motorFrontLeft.setPower(leftmotorStepsSpeed[i]);
            motorRearLeft.setPower(leftmotorStepsSpeed[i]);
            motorFrontRight.setPower(rightmotorStepsSpeed[i]);
            motorRearRight.setPower(rightmotorStepsSpeed[i]);


            while(motorFrontLeft.isBusy() || motorRearLeft.isBusy() || motorFrontRight.isBusy() || motorRearRight.isBusy());
        }

        */






































        /*
        while(motorFrontLeft.getCurrentPosition() != 0 || motorFrontRight.getCurrentPosition() != 0) { //Ensures encoders are zero
                    motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    waitOneFullHardwareCycle(); //Needed within all loops
                }
                motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODERS); //Sets mode to use encoders
                motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODERS); //setMode() is used instead of setChannelMode(), which is now deprecated
        waitForStart();


        motorFrontLeft.setTargetPosition(10); //Sets motor to move 1440 ticks (1440 is one rotation for Tetrix motors)
        motorFrontRight.setTargetPosition(10);
        motorFrontLeft.setPower(1);
        motorFrontRight.setPower(1);

        Thread.sleep(1000);

        motorFrontLeft.setTargetPosition(1680); //Sets motor to move 1440 ticks (1440 is one rotation for Tetrix motors)
        motorFrontRight.setTargetPosition(1680);
        motorFrontLeft.setPower(.5);
        motorFrontRight.setPower(.5);

        while(motorFrontLeft.getCurrentPosition() < motorFrontLeft.getTargetPosition() || motorFrontLeft.getCurrentPosition() < motorFrontRight.getTargetPosition()) { //While target has not been reached
            waitOneFullHardwareCycle(); //Needed within all loops
        }
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);

        */














        //This is the command for the SECOND steps to be completed (last year)


        /*
        for( int i = 0 ; i<motorLeftStepsTicks2.length && opModeIsActive(); i++ ){
            motorFrontLeft.setTargetPosition(motorFrontLeft.getCurrentPosition() + GetTicksFromInches(motorLeftStepsTicks2[i]));
            motorRearLeft.setTargetPosition(motorRearLeft.getCurrentPosition() + GetTicksFromInches(motorRightStepsTicks2[i]));
            motorFrontLeft.setPower(leftmotorStepsSpeed2[i]);
            motorRearLeft.setPower(rightmotorStepsSpeed2[i]);
            while(motorFrontLeft.isBusy() || motorRearLeft.isBusy());
        }
        */
















   /* public int GetTicksFromMeters(double Meters) {
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

     */