package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DataWriter;

class DataWriterTest {

	@Test
	void testWriteUsers() {
		DataWriter.writeUsers();
	}

	@Test
	void testWriteShows() {
		DataWriter.writeShows();
	}

	@Test
	void testWriteVenues() {
		DataWriter.writeVenues();
	}
}
