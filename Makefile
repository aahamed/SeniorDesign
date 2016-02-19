# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)
OSNAME = $(OS)
XLINT = -Xlint:unchecked

# Determine OS
ifeq '$(OS)' 'Windows_N'
	# set variables for windows
	OSNAME=$(OS)
	CP=-cp '$(BIN);$(LIB)'
else
	# get OS name
	OSNAME=$(shell uname)
	# set variables according to OS

	#Mac OSX - change as needed
	ifeq '$(OSNAME)' 'Darwin'
		CP=-cp '$(BIN):$(LIB)'
	endif
endif


#Make Rules

all:
	javac $(CP) $(ODIR) $(SRC)*.java

clean:
	rm -rf $(BIN)*.class

FindAN:
	javac $(CP) $(ODIR) $(SRC)FindAN.java

testFindAN:
	java $(CP) FindAN

HCS:
	javac $(CP) $(ODIR) $(SRC)HCS.java

testHCS:
	java $(CP) HCS

Connect:
	javac $(CP) $(ODIR) $(SRC)Connect.java

testConnect:
	java $(CP) Connect

Coord:
	javac $(CP) $(ODIR) $(SRC)Coordinate.java

testCoord:
	java $(CP) Coordinate

InitMax:
	javac $(CP) $(ODIR) $(SRC)InitMax.java

testInitMax:
	java $(CP) InitMax

echo_osname:
	echo $(OSNAME)

echo_cp:
	echo $(CP)

test:
	java $(CP) PCG

stest:
	java $(CP) PCG -i ./src/input/in_small.txt -g -p

mtest:
	java $(CP) PCG -m -i ./src/input/min.txt
