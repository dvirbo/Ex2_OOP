package Classestest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.CGeo;

import static org.junit.jupiter.api.Assertions.*;

class CGeoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void x() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(1.0, g1.x());
    }

    @Test
    void y() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(2.0, g1.y());
    }

    @Test
    void z() {
        var g1 = new CGeo(1, 2, 3);
        assertEquals(3.0, g1.z());
    }

    @Test
    void distance() {
        var g1 = new CGeo(1, 2, 3);
        var g2 = new CGeo(1, 2, 3);
        assertEquals(0.0, g1.distance(g2));
    }

    @Test
    void testToString() {
        assertEquals(1.0 + ", " + 2.0 + ", " + 3.0, new CGeo(1, 2, 3).toString());
    }
}