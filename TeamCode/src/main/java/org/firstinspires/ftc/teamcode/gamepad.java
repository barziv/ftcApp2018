/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="gamepad", group="Linear Opmode")
//@Disabled
public class gamepad extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private Servo upLeft = null;
    private Servo downLeft = null;
    private Servo upRight = null;
    private Servo downRight = null;

    private Servo grow1 = null;
    private Servo grow2 = null;

    private DcMotor eleMotor = null;
    private DcMotor relicMotor1 = null;
    private DcMotor relicMotor2 = null;
    private DcMotor hand = null;
    private Servo relicCatcher = null;

    @Override
    public void runOpMode() {

        elevetor elevetor_func = new elevetor();
        drive_class drive = new drive_class();
        slowMod slowDrive = new slowMod();
        relic_class relic = new relic_class();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        leftFront = hardwareMap.dcMotor.get("left_front");
        leftBack = hardwareMap.dcMotor.get("left_back");
        rightBack = hardwareMap.dcMotor.get("right_back");
        rightFront = hardwareMap.dcMotor.get("right_front");
        upLeft = hardwareMap.servo.get("upLeft");
        downLeft = hardwareMap.servo.get("downLeft");
        upRight = hardwareMap.servo.get("upRight");
        downRight = hardwareMap.servo.get("downRight");
        eleMotor =hardwareMap.dcMotor.get("eleMotor");
        grow1 = hardwareMap.servo.get("grow1");
        grow2 = hardwareMap.servo.get("grow2");

        relicMotor1 = hardwareMap.dcMotor.get("relicMotor1");
        relicMotor2 = hardwareMap.dcMotor.get("relicMotor2");
        hand = hardwareMap.dcMotor.get("hand");
        relicCatcher = hardwareMap.servo.get("relicCatcher");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        grow1.setPosition(0);
        grow2.setPosition(1);
        boolean driveSlow = true;
        // run until the end of the match (driver presses STOP)

        //gamepad1 - relic, elevetor
        //gamepad2 - drive, slow drive

        while (opModeIsActive()) {

            elevetor_func.getAndLeave(upLeft, downLeft, upRight, downRight, gamepad1);
            elevetor_func.checkDirection(eleMotor, gamepad1);
            relic.relicManager(relicMotor1, relicMotor2, hand, relicCatcher, gamepad1);

            if(driveSlow)
            {
                drive.drive_manager(leftFront, leftBack, rightFront, rightBack, gamepad2);
            }
            else
            {
                slowDrive.drive_manager(leftFront, leftBack, rightFront, rightBack, gamepad2);
            }
            if(gamepad2.start)
            {
                driveSlow = !driveSlow;
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
