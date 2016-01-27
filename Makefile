# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
CP=-cp '$(BIN);$(LIB)'
ODIR=-d $(BIN)

all:
	javac $(CP) $(ODIR) $(SRC)*.java

FindAN:
	javac $(CP) $(ODIR) $(SRC)FindAN.java

testFindAN:
	java $(CP) FindAN
