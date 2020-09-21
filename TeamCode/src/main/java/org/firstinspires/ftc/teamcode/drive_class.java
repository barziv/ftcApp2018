package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class drive_class {
    public drive_class()
    {}

    public void drive_manager(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, Gamepad gamepad1)
    {
        if(gamepad1.right_trigger > 0)
        {
            forward(leftFront, leftBack, rightFront, rightBack, gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0)
        {
            backward(leftFront, leftBack, rightFront, rightBack, gamepad1.left_trigger);
        }
        else if(gamepad1.left_stick_x > 0)
        {
            turnRight(leftFront, leftBack, rightFront, rightBack, -gamepad1.left_stick_x);
        }
        else if(gamepad1.left_stick_x < 0)
        {
            turnLeft(leftFront, leftBack, rightFront, rightBack, gamepad1.left_stick_x);
        }
        else if (gamepad1.dpad_right)
        {
            driveRight(leftFront, leftBack, rightFront, rightBack, 0.75);
        }
        else if (gamepad1.dpad_left)
        {
            driveLeft(leftFront, leftBack, rightFront, rightBack, 0.75);
        }
        else
        {
            forward(leftFront, leftBack, rightFront, rightBack, 0);
        }
    }

    private void forward(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        leftBack.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(-power);
    }

    private void backward(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        forward(leftFront, leftBack, rightFront, rightBack, -power);
    }

    private void turnRight(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        leftBack.setPower(power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(power);
    }

    private void turnLeft(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        turnRight(leftFront, leftBack, rightFront, rightBack, -power);
    }

    public void driveRight(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        leftBack.setPower(power);
        leftFront.setPower(-power);
        rightBack.setPower(power);
        rightFront.setPower(-power);
    }

    public void driveLeft(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack, double power)
    {
        driveRight(leftFront, leftBack, rightFront, rightBack, -power);
    }
}
