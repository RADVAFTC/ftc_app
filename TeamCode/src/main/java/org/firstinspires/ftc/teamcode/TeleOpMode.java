package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ChristopherDeloglos on 12/3/2016.
 */
@TeleOp(name = "TeleOp Mode", group = "Tele Operated")

public class TeleOpMode extends LinearOpMode
{

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

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        //motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorTopSpinner = hardwareMap.dcMotor.get ("motorTopSpinner");
        motorBottomSpinner = hardwareMap.dcMotor.get ("motorBottomSpinner");

        //motorPlexiglass = hardwareMap.dcMotor.get ("motorPlexiglass");


        //motorSpare = hardwareMap.dcMotor.get ("motorSpare")


        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorTopSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        waitForStart();//Stops here after Init, waits for start

        while(opModeIsActive())
        {
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

    };}

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
}
