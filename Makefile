# Top Level Makefile
# Put rules for compilation/running here

BIN=./bin/
SRC=./src/
LIB=./lib/*
CP=-cp '$(BIN):$(LIB)'
ODIR=-d $(BIN)

all:
	javac $(CP) $(ODIR) $(SRC)*.java

clean:
	rm -rf $(BIN)*.class

FindAN:
	javac $(CP) $(ODIR) $(SRC)FindAN.java

testFindAN:
	java $(CP) FindAN

test:
	java $(CP) TEST

stest:
	java $(CP) TEST -i ./src/input/in_small.txt -g -p

mtest:
	java $(CP) TEST -m -i ./src/input/min.txt
