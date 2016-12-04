package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    private DcMotor motorLauncher;

    private static final double MOTOR_SAFE_SPEED = 0.3;
    private static final double MOTOR_FULL_SPEED = 1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLauncher = hardwareMap.dcMotor.get("motorLauncher");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();//Stops here after Init, waits for start

        while(opModeIsActive())
        {
            motorLeft.setPower(gamepad1.left_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorRight.setPower(gamepad1.right_stick_y + gamepad1.right_trigger - gamepad1.left_trigger);
            motorLauncher.setPower(gamepad2.left_stick_y);
            idle();
        }
    }

}
