# controlstraining

my current code is for 1 ball. The talon motor moves it to the top (sensor 3) area and it will launch if there is a ball and the button is pressed.

I was trying to make it work for multiple balls by letting the first ball store in the middle (sensor 2 area) instead of the top like I originally had. Then if another ball comes then the talon will spin and they will both be moved up until the original ball reaches sensor 3 area (will probably be changed with testing). 
This was the code I was trying to do

```
if (s1) {
      talon.set(speed);
      if (!s2 && sensor2.get() >= ballDetected) { //first ball
        s1 = false;
        s2 = true;
        talon.set(0);
      } else if (s2) { //already a ball inside
        if (sensor3.get() >= ballDetected) { 
          // moves first ball to sensor 3 and new ball into chute
          //irl with testing probably should not be first ball to sensor 3 bc that is too high
          s1 = false;
          s2 = true;
          s3 = true;
          talon.set(0);
        }
      }
    }
    else if (sensor1.get() >= ballDetected) {
      s1 = true;
    }
```
 
 and i was trying to make it so that the talon/flywheel would turn for different time lengths considering if there is a ball at the top or only the middle.
