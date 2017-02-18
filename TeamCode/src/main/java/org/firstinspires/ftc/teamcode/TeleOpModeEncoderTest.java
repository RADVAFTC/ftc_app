package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ChristopherDeloglos on 1/25/2017.
 */

@TeleOp(name = "TeleOp Encoder Test", group = "Tele Operated")
public class TeleOpModeEncoderTest extends LinearOpMode {
    private static final double TICKS_PER_REV = 360; // Number of ticks in one motor turn
    private static final double REVS_PER_METER = 10; // Number of times the wheel turns after the robot moves 1 meter
    private static final double GEAR_RATIO = 1; // Number of times the motor rotates for 1 rotation of the wheel
    //private static final int OPMODE_NUMBER_PATH_STEPS = 10;
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL_SPEED = 1;

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorTopSpinner;
    private DcMotor motorBottomSpinner;
    //private DcMotor motorPlexiglass;
    //private DcMotor motorSpare;
    private DcMotorController motorController1;
    private DcMotorController motorController2;
    //private DcMotorController motorController3;

    private static final double MOTOR_SAFE_SPEED = 0.3;
    private static final double MOTOR_STOP = 0;
    private static final double MOTOR_FULL_SPEED = 1;
    private static final double ACCELERATION_RATE = 0.001;
    int[] motorLeftStepsTicks = {100, 100, 100, 100, 200, -200, 100, 100, 100, 100};
    int[] motorRightStepsTicks = {100, 0, 100, 0, 200, -200, 100, 0, 100, 0};
    double[] motorStepsSpeed = {1, 1, 1, 1, 1, 1, 1, 1, .5, 1};

    @Override
    public void runOpMode() throws InterruptedException {

        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        //motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorTopSpinner = hardwareMap.dcMotor.get("motorTopSpinner");
        motorBottomSpinner = hardwareMap.dcMotor.get("motorBottomSpinner");

        //motorPlexiglass = hardwareMap.dcMotor.get ("motorPlexiglass");


        //motorSpare = hardwareMap.dcMotor.get ("motorSpare")


        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();//Stops here after Init, waits for start

        while (opModeIsActive()) {
            /*
            motorLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            //ws2xemotorTopSpinner.setPower (gamepad2.left_stick_y + gamepad2.right_trigger - gamepad2.left_trigger);
            //motorPlexiglass.setPower (gamepad2.right_stick_y + gamepad2.right_trigger - gamepad2.left_trigger);
            //motorPlexiglass.setPower(setMotorSpeed(gamepad2.right_stick_y + gamepad2.right_trigger - gamepad2.left_trigger));

            //==,!=,<,>,<=,>=
            if (gamepad1.a==true) {
                motorBottomSpinner.setPower(MOTOR_FULL_SPEED);
                motorTopSpinner.setPower(MOTOR_FULL_SPEED);
            }
            if(gamepad1.b==true) {
                motorBottomSpinner.setPower(MOTOR_STOP);
                motorTopSpinner.setPower(MOTOR_STOP);

                //idle();
            }
            */
            for (int counter = 0; counter < motorLeftStepsTicks.length; counter++) {
                TurnDistance(motorStepsSpeed[counter], motorLeftStepsTicks[counter], motorRightStepsTicks[counter]);
            }
        }
        ;
    }



    private double setMotorSpeed(double desiredSpeed){
        double curSpeed = motorLeft.getPower();

        if(desiredSpeed>curSpeed){
            if( (desiredSpeed-curSpeed) < ACCELERATION_RATE){
                curSpeed = desiredSpeed;
            }
            else{
                curSpeed+=ACCELERATION_RATE;
            }
        }
        else if(desiredSpeed< curSpeed)
            if( (curSpeed-desiredSpeed) < ACCELERATION_RATE) {
                curSpeed = desiredSpeed;
            }
            else {
                curSpeed-=ACCELERATION_RATE;
            }
        return(curSpeed);
    }



    public void DriveForwardDistance(double power, int ticks){
        ResetEncodersforRunToPosition(ticks, ticks);
        DriveFoward(power);

        while(motorLeft.isBusy() || motorRight.isBusy()){
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

    public void TurnDistance(double power, int ticksLeft,int ticksRight){
        ResetEncodersforRunToPosition(ticksLeft,ticksRight);
        Turn(power,power);

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

    private void ResetEncodersforRunToPosition(int leftTicks,int rightTicks){
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

    private void Turn(double leftPower, double rightPower){
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
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

