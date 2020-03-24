JFLAGS = -g

JARFLAGS = -cvmf
JARFLAGS += MANIFEST.MF

JC = javac
JAR = jar

CLASSES = \
        Driver.java \
				Adult.java \
				Age.java \
				BookedShow.java \
				Child.java \
				Admin.java \
				Genre.java \
				Guest.java \
				Movie.java \
				Payment.java \
				Review.java \
				Seat.java \
				Show.java \
				ShowDatabase.java \
				ShowUI.java \
				Staff.java \
				Theater.java \
				User.java \
				UserManager.java \
				Venue.java \
				VenueDatabase.java \

default:	compile

setup:
	mkdir ../bin

compile:
	$(JC) $(JFLAGS) $(CLASSES)
	cp *.class ../bin/
	$(JAR) $(JARFLAGS) ../Show.jar *.class
	rm -f *.class

clean:
	rm -f ../bin/*.class
	rm ../Show.jar
