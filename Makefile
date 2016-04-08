# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
TEST=./test/
GP=./gnuplot/
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)
OSNAME = $(OS)
XLINT = -Xlint:unchecked

# Determine OS

ifeq '$(OS)' 'Windows_NT'
	# set variables for windows
	OSNAME=$(OS)
	CP=-cp '$(BIN);$(LIB)'
else
	# get OS name
	OSNAME=$(shell uname)
	# set variables according to OS

	# Mac OS X
	ifeq '$(OSNAME)' 'Darwin'
		CP=-cp '$(BIN):$(LIB)'
	endif

	# Linux
	ifeq '$(OSNAME)' 'Linux'
		CP=-cp '$(BIN):$(LIB)'
	endif
endif


#Make Rules

all:
	javac $(CP) $(ODIR) $(SRC)*.java

clean:
	rm -rf $(BIN)*.class

GlobalConstants: $(SRC)GlobalConstants.java
	javac $(CP) $(ODIR) $(SRC)GlobalConstants.java

HCS: Coordinate GlobalConstants
	javac $(CP) $(ODIR) $(SRC)HCS.java

testHCS:
	java $(CP) HCS

Coordinate: $(SRC)Coordinate.java
	javac $(CP) $(ODIR) $(SRC)Coordinate.java

testCoord:
	java $(CP) Coordinate

ConnectOut: $(SRC)ConnectOut.java
	javac $(CP) $(ODIR) $(SRC)ConnectOut.java

Connect: ConnectOut HCS GlobalConstants Coordinate
	javac $(CP) $(ODIR) $(SRC)Connect.java

#testConnect:
#	java $(CP) Connect

FindANOut: $(SRC)FindANOut.java
	javac $(CP) $(ODIR) $(SRC)FindANOut.java

FindAN: Coordinate FindANOut
	javac $(CP) $(ODIR) $(SRC)FindAN.java

#testFindAN:
#	java $(CP) FindAN

PrimMST: $(SRC)PrimMST.java
	javac $(CP) $(ODIR) $(SRC)PrimMST.java

List_ops:
	javac $(CP) $(ODIR) $(SRC)List_ops.java

InitMaxOut:
	javac $(CP) $(ODIR) $(SRC)InitMaxOut.java

InitMax: Coordinate GlobalConstants HCS PrimMST
	javac $(CP) $(ODIR) $(SRC)InitMax.java

#testInitMax:
#	java $(CP) InitMax

LocateM2:
	javac $(CP) $(ODIR) $(SRC)LocateM2.java

Main: $(SRC)MainBody.java
	javac $(CP) $(ODIR) $(SRC)MainBody.java

run:
	java $(CP) MainGUI

testMain:
	java $(CP) MainBody2 -m -k -i ./input/min.txt

testMain2:
	java $(CP) MainBody2 -g -k -i ./input/in.txt

graphInit:
	gnuplot -persist $(GP)initial.gnu

graphFinal:
	gnuplot -persist $(GP)graph.gnu

graphFinalPNG:
	gnuplot -persist $(GP)graphpng.gnu

echo_osname:
	echo $(OSNAME)

echo_cp:
	echo $(CP)

testJunit: $(TEST)TestJunit.java $(TEST)TestRunner.java
	javac $(CP) $(ODIR) $(TEST)TestJunit.java $(TEST)TestRunner.java

runTestJunit:
	java $(CP) TestRunner

testCoordinate: Coordinate $(TEST)TestCoordinate.java $(TEST)TestCoordinateRunner.java
	javac $(CP) $(ODIR) $(TEST)TestCoordinate.java $(TEST)TestCoordinateRunner.java

runTestCoordinate:
	java $(CP) TestCoordinateRunner

testConnect: Connect $(TEST)TestConnect.java $(TEST)TestConnectRunner.java
	javac $(CP) $(ODIR) $(TEST)TestConnect.java $(TEST)TestConnectRunner.java

runTestConnect:
	java $(CP) TestConnectRunner

testFindAN: FindAN $(TEST)TestFindAN.java $(TEST)TestFindANRunner.java
	javac $(CP) $(ODIR) $(TEST)TestFindAN.java $(TEST)TestFindANRunner.java $(XLINT)

runTestFindAN:
	java $(CP) TestFindANRunner

testInitMax:
	javac $(CP) $(ODIR) $(TEST)TestInitMax.java $(TEST)TestInitMaxRunner.java $(XLINT)

runTestInitMax:
	java $(CP) TestInitMaxRunner
