import os
import sys

argl = len(sys.argv)

if (argl >= 1): 
    cmdC = "";
    for comp in sys.argv:
        cmdC.join(comp + " ")
    os.system("mvn " + cmdC + " package")
else: 
    os.system("mvn package")
