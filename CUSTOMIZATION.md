# Customization

There are a couple of changes teams must make before they implement this template on their robot

## Steps
* Change CAN ID(s) to match controller CAN ID(s)
* Input proper track width and wheel base
* Input drive pinion teeth constant
* Tune PID/FF gains

## Alternative Hardware

### Vortex Motors
This template assumes a drive base running REV NEOs on the drive motors. If teams wish to use NEO Vortex motors, change the code in ```Constants.java``` to reflect this change:

```
public static final double DRIVE_MOTOR_FREE_SPEED_RPS =
    MotorConstants.NEO_FREE_SPEED_RPM / 60.0;
```
becomes
```
public static final double DRIVE_MOTOR_FREE_SPEED_RPS =
    MotorConstants.VORTEX_FREE_SPEED_RPM / 60;
```

### Gyroscopes
This template assumes a drive base running a NavX-MXP gyroscope. If teams wish to use other gyroscopes, such as the Pigeon2 from CTRE or the Analog Devices gyroscope from the KOP, some code changes will be required.