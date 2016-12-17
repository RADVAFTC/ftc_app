package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by ChristopherDeloglos on 12/3/2016.
 * Purpose: Educational model for robot programming
 */
@Autonomous(name = "AutoOp Mode", group = "Autonomous")

public class AutoOpMode extends LinearOpMode
{
    private static final double TICKS_PER_REV = 1440; // Number of ticks in one motor turn
    private static final double REVS_PER_METER = 10; // Number of times the wheel turns after the robot moves 1 meter
    private static final double GEAR_RATIO = 1; // Number of times the motor rotates for 1 rotation of the wheel
    private static final int OPMODE_NUMBER_PATH_STEPS = 10;
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL_SPEED = 1;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    int[] motorLeftStepsTicks = {100, 100, 100, 100, 200, -200, 100, 100, 100, 100};
    int[] motorRightStepsTicks = {100, 0, 100, 0, 200, -200, 100, 0, 100, 0};
    double[] motorLeftStepsSpeed = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    double[] motorRightStepsSpeed = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        DriveForwardDistance(1,GetTicksFromMeters(1.0));
        DriveForwardDistance(1,GetTicksFromMeters(1.0));
        //while(opModeIsActive()){}

    }

    public void DriveForwardDistance(double power, int distance){
        ResetEncodersforRunToPosition(distance);
        DriveFoward(power);

        while(motorLeft.isBusy() && motorRight.isBusy()){
            if(!motorLeft.isBusy()) {
                motorLeft.setPower(DCMOTOR_SPEED_STOPPED);
            }
            if(!motorRight.isBusy()) {
                motorRight.setPower(DCMOTOR_SPEED_STOPPED);
            }
        }
        StopMotors();
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void ResetEncodersforRunToPosition(int distance){
        // Reset encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        motorLeft.setTargetPosition(distance);
        motorRight.setTargetPosition(distance);

        // Set to RUN_TO_POSITION mode
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public void DriveFoward(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }
    public void StopMotors(){
        motorLeft.setPower(DCMOTOR_SPEED_STOPPED);
        motorRight.setPower(DCMOTOR_SPEED_STOPPED);
    }

    public int GetTicksFromMeters(double Meters){
        Double ticks = GEAR_RATIO * (TICKS_PER_REV * REVS_PER_METER * Meters);
        return(ticks.intValue());
    }
}
