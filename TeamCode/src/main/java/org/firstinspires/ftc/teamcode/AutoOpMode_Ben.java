package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Add this for default

/**
 * Created by ChristopherDeloglos on 12/18/2016.
 */
@Autonomous (name = "AutoOpMode_Ben", group = "Autonomous")

public class AutoOpMode_Ben  extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;



    public void runOpMode() throws InterruptedException
    {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);



    }
}