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
        // Hardware Map for Motor Controllers
        motorController1 = hardwareMap.dcMotorController.get("Motor Controller 1");
        motorController2 = hardwareMap.dcMotorController.get("Motor Controller 2");
        //motorController3 = hardwareMap.dcMotorController.get("Motor Controller 3");

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
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRearLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorRearRight.setDirection(DcMotor.Direction.FORWARD);

        // Sets mode for motors
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Sets zero power behavior
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //servoChoppingBlock.setPosition(.5);

        waitForStart();//Stops here after Init, waits for start
        //private boolean __MOTOR_WAS_JUST_PRESSED = false;
        //private boolean __MOTOR_IS_PRESSED_NOW = false;
        while (opModeIsActive()) {
            // CONTROLLER 1

            //Forward and Backward Controls
           /* motorFrontLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorFrontRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRearRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            */

            // Motion Control
            motorFrontLeft.setPower((1*gamepad1.left_stick_y) + (-1*gamepad1.left_stick_x));
            motorRearLeft.setPower((1*gamepad1.left_stick_y) + (1*gamepad1.left_stick_x));
            motorFrontRight.setPower((1*gamepad1.left_stick_y) + (1*gamepad1.left_stick_x));
            motorRearRight.setPower((1*gamepad1.left_stick_y) + (-1*gamepad1.left_stick_x));

            // Pivot Control
            motorFrontLeft.setPower(1*(gamepad1.right_stick_x));
            motorRearLeft.setPower(-1*(gamepad1.right_stick_x));
            motorFrontRight.setPower(-1*(gamepad1.right_stick_x));
            motorRearRight.setPower(1*(gamepad1.right_stick_x));

            motorFrontLeft.setPower(1*(gamepad1.right_stick_y));
            motorRearLeft.setPower(-1*(gamepad1.right_stick_y));
            motorFrontRight.setPower(-1*(gamepad1.right_stick_y));
            motorRearRight.setPower(1*(gamepad1.right_stick_y));

            // Sideways controls

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
        }

    }