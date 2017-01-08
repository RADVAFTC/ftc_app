package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Caleb Schwartz on 12/18/2016.
 */



@Autonomous(name = "AutoOp Mode_Caleb", group = "Autonomous")

public class AutoOpMode_Caleb extends LinearOpMode{




    private static final double TICKS_PER_REV = 360;
    private static final double REVS_PER_METER = 10;
    private static final double GEAR_RATION = 1;
    private static final double DCMOTOR_SPEED_STOPPED = 0;
    private static final double DCMOTOR_SPEED_FULL = 1;

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorFrontArm;

    //These that follow are the steps for autonomous

    int[] leftstepsticks       = {100, 200, 300};
    int[] rightstepsticks      = {100, 200, 300};
    double[] motorspeedperstep = {1, 1, 1 };

    @Override
    public void runOpMode() throws InterruptedException {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorLeft.setDirection(DcMotor.Direction.FORWARD);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorFrontArm.setDirection(DcMotor.Direction.REVERSE);

        for (int counter = 0; counter <leftstepsticks.length; counter++ ){

        }





    }
}

