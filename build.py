import os
import sys

#for comp in sys.argv: 
#	print(comp)

cmdC = " "
for comp in sys.argv:
	if (comp == sys.argv[0]):
		continue
	cmdC = cmdC + comp + " "
c = "mvn" + cmdC + "package"
print("\n[" + c + "]\n")
os.system(c)