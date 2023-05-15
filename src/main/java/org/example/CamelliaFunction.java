package org.example;

import java.math.BigInteger;
import java.text.Bidi;

public class CamelliaFunction
{
    private static final int NUMBER_VALUE_T_AND_Y = 8;
    private static final int NUMBER_BIT_IN_INT = 32;

    public static byte F(BigInteger F_IN, BigInteger KE)
    {
        BigInteger x = F_IN.xor(KE);
        byte[] t = new byte[NUMBER_VALUE_T_AND_Y];
        byte[] y = new byte[NUMBER_VALUE_T_AND_Y];
        BigInteger MASK8 = BigInteger.valueOf(Camellia.MASK8);
        t[0] = (x.shiftRight(56)).byteValue();
        t[1] = (x.shiftRight(48)).and(MASK8).byteValue();
        t[2] = (x.shiftRight(40)).and(MASK8).byteValue();
        t[3] = (x.shiftRight(32)).and(MASK8).byteValue();
        t[4] = (x.shiftRight(24)).and(MASK8).byteValue();
        t[5] = (x.shiftRight(16)).and(MASK8).byteValue();
        t[6] = (x.shiftRight(8)).and(MASK8).byteValue();
        t[7] = x.and(MASK8).byteValue();

        t[0] = CamelliaSBlocks.getSBox1(t[0]);
        t[1] = CamelliaSBlocks.getSBox2(t[1]);
        t[2] = CamelliaSBlocks.getSBox3(t[2]);
        t[3] = CamelliaSBlocks.getSBox4(t[3]);
        t[4] = CamelliaSBlocks.getSBox2(t[4]);
        t[5] = CamelliaSBlocks.getSBox3(t[5]);
        t[6] = CamelliaSBlocks.getSBox4(t[6]);
        t[7] = CamelliaSBlocks.getSBox1(t[7]);

        y[0] = (byte) (t[0] ^ t[2] ^ t[3] ^ t[5] ^ t[6] ^ t[7]);
        y[1] = (byte) (t[0] ^ t[1] ^ t[3] ^ t[4] ^ t[6] ^ t[7]);
        y[2] = (byte) (t[0] ^ t[1] ^ t[2] ^ t[4] ^ t[5] ^ t[7]);
        y[3] = (byte) (t[1] ^ t[2] ^ t[3] ^ t[4] ^ t[5] ^ t[6]);
        y[4] = (byte) (t[0] ^ t[1] ^ t[5] ^ t[6] ^ t[7]);
        y[5] = (byte) (t[1] ^ t[2] ^ t[4] ^ t[6] ^ t[7]);
        y[6] = (byte) (t[2] ^ t[3] ^ t[4] ^ t[5] ^ t[7]);
        y[7] = (byte) (t[0] ^ t[3] ^ t[4] ^ t[5] ^ t[6]);

        return  (y[0] << 56) | (y[1] << 48) | (y[2] << 40) | (y[3] << 32)
                | (y[4] << 24) | (y[5] << 16) | (y[6] <<  8) | y[7];
    }

    public static long F(long F_IN, long KE)
    {
        long x = F_IN ^ KE;
        byte[] t = new byte[NUMBER_VALUE_T_AND_Y];
        byte[] y = new byte[NUMBER_VALUE_T_AND_Y];

        t[0] = (byte) (x >> 56);
        t[1] = (byte) ((x >> 48) & Camellia.MASK8);
        t[2] = (byte) ((x >> 40) & Camellia.MASK8);
        t[3] = (byte) ((x >> 32) & Camellia.MASK8);
        t[4] = (byte) ((x >> 24) & Camellia.MASK8);
        t[5] = (byte) ((x >> 16) & Camellia.MASK8);
        t[6] = (byte) ((x >>  8) & Camellia.MASK8);
        t[7] = (byte) (x         & Camellia.MASK8);

        t[0] = CamelliaSBlocks.getSBox1(t[0]);
        t[1] = CamelliaSBlocks.getSBox2(t[1]);
        t[2] = CamelliaSBlocks.getSBox3(t[2]);
        t[3] = CamelliaSBlocks.getSBox4(t[3]);
        t[4] = CamelliaSBlocks.getSBox2(t[4]);
        t[5] = CamelliaSBlocks.getSBox3(t[5]);
        t[6] = CamelliaSBlocks.getSBox4(t[6]);
        t[7] = CamelliaSBlocks.getSBox1(t[7]);

        y[0] = (byte) (t[0] ^ t[2] ^ t[3] ^ t[5] ^ t[6] ^ t[7]);
        y[1] = (byte) (t[0] ^ t[1] ^ t[3] ^ t[4] ^ t[6] ^ t[7]);
        y[2] = (byte) (t[0] ^ t[1] ^ t[2] ^ t[4] ^ t[5] ^ t[7]);
        y[3] = (byte) (t[1] ^ t[2] ^ t[3] ^ t[4] ^ t[5] ^ t[6]);
        y[4] = (byte) (t[0] ^ t[1] ^ t[5] ^ t[6] ^ t[7]);
        y[5] = (byte) (t[1] ^ t[2] ^ t[4] ^ t[6] ^ t[7]);
        y[6] = (byte) (t[2] ^ t[3] ^ t[4] ^ t[5] ^ t[7]);
        y[7] = (byte) (t[0] ^ t[3] ^ t[4] ^ t[5] ^ t[6]);

        return  (y[0] << 56) | (y[1] << 48) | (y[2] << 40) | (y[3] << 32)
                | (y[4] << 24) | (y[5] << 16) | (y[6] <<  8) | y[7];
    }

    public static long FL(long FL_IN, long KE)
    {
        int x1, x2, k1, k2;
        x1 = (int) (FL_IN >> NUMBER_BIT_IN_INT);
        x2 = (int) (FL_IN & Camellia.MASK32);
        k1 = (int) (KE >> NUMBER_BIT_IN_INT);
        k2 = (int) (KE & Camellia.MASK32);
        x2 = x2 ^ (CamelliaSBlocks.cycleShift(x1 & k1, 1));
        x1 = x1 ^ (x2 | k2);
        return ((long) x1 << NUMBER_BIT_IN_INT) | (long) x2 & 0xFFFFFFFFL;
    }

    public static long FL_INV(long FL_INV, long KE)
    {
        int y1, y2, k1, k2;
        y1 =  (int) (FL_INV >> NUMBER_BIT_IN_INT);
        y2 = (int) (FL_INV & Camellia.MASK32);
        k1 = (int) (KE >> NUMBER_BIT_IN_INT);
        k2 = (int) (KE & Camellia.MASK32);
        y1 = y1 ^ (y2 | k2);
        y2 = y2 ^ (CamelliaSBlocks.cycleShift(y1 & k1, 1));
        return ((long) y1 << NUMBER_BIT_IN_INT) | (long) y2 & 0xFFFFFFFFL;
    }

}
