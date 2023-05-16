package org.example;
import java.math.BigInteger;

public class CamelliaKey {
    public long kw1, kw2, kw3, kw4;
    public long k1, k2, k3, k4, k5, k6, k7, k8, k9,
            k10, k11, k12, k13, k14, k15, k16, k17, k18, k19, k20, k21, k22, k23, k24;
    public long ke1, ke2, ke3, ke4, ke5, ke6;

    private static long[] getKL_KR(String strKey)
    {
        long[] twoPartOfKey = new long[4];
        byte[] byteKey = strKey.getBytes();
        int c = -1;

        for (int i = 0; i < (byteKey.length > 32 ? 32 : byteKey.length); i++)
        {
            if (i % 8 == 0)
            {
                c++;
            }
            twoPartOfKey[c] <<= 8;
            twoPartOfKey[c] += byteKey[i] & Camellia.MASK8;
        }
        if (byteKey.length <= 24 && byteKey.length > 16)
        {
            System.out.println("LOG: getKL_KR if (byteKey.length <= 24 && byteKey.length > 16)");
            twoPartOfKey[3] = ~twoPartOfKey[2];
        }
        return twoPartOfKey;
    }

    private static long[] getKA_KB(long[] KL_KR)
    {
        long[] KA_KB = new long[4];
        long D1, D2;
        D1 = KL_KR[0] ^ KL_KR[2];
        D2 = KL_KR[1] ^ KL_KR[3];
        D2 = D2 ^ CamelliaFunction.F(D1, Camellia.c[0]);
        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[1]);
        D1 = D1 ^ KL_KR[0];
        D2 = D2 ^ KL_KR[1];
        D2 = D2 ^ CamelliaFunction.F(D1, Camellia.c[2]);
        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[3]);
        KA_KB[0] = D1;
        KA_KB[1] = D2;
        D1 = KA_KB[0] ^ KL_KR[2];
        D2 = KA_KB[1] ^ KL_KR[3];
        D2 = D2 ^ CamelliaFunction.F(D1, Camellia.c[4]);
        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[5]);
        KA_KB[2] = D1;
        KA_KB[3] = D2;
        return KA_KB;
    }
    private static long[] cycleShiftForPair(long[] values, int shift)
    {
        long[] temp = new long[2];
        long[] newValues = new long[2];

        if (shift <= 64)
        {
            temp[1] = (values[0] >>> (64 - shift));
            newValues[0] = (values[0] << shift) + (values[1] >>> (64 - shift));
            newValues[1] = (values[1] << shift) + temp[1];
        }
        else
        {
            temp[0] = values[0] >>> 64 - (shift - 64);
            temp[1] = (values[0] << (shift-64)) + (values[1] >>> 64 - (shift - 64));
            newValues[0] = (values[1] << 64 - (128 - shift)) + temp[0];
            newValues[1] = temp[1];
        }
        return newValues;
    }

    private void getSubKeys128(long[] KL_KR, long[] KA_KB)
    {
        long[] KL = {KL_KR[0], KL_KR[1]}, KA = {KA_KB[0], KA_KB[1]};
        kw1 = KL_KR[0];
        kw2 = KL_KR[1];
        k1 = KA_KB[0];
        k2 = KA_KB[1];
        k3  = cycleShiftForPair(KL, 15)[0];
        k4  = cycleShiftForPair(KL, 15)[1];
        k5  = cycleShiftForPair(KA, 15)[0];
        k6  = cycleShiftForPair(KA, 15)[1];
        ke1 = cycleShiftForPair(KA, 30)[0];
        ke2 = cycleShiftForPair(KA, 30)[1];
        k7  = cycleShiftForPair(KL, 45)[0];
        k8  = cycleShiftForPair(KL, 45)[1];
        k9  = cycleShiftForPair(KA, 45)[0];
        k10 = cycleShiftForPair(KL, 60)[1];
        k11 = cycleShiftForPair(KA, 60)[0];
        k12 = cycleShiftForPair(KA, 60)[1];
        ke3 = cycleShiftForPair(KL, 77)[0];
        ke4 = cycleShiftForPair(KL, 77)[1];
        k13 = cycleShiftForPair(KL, 94)[0];
        k14 = cycleShiftForPair(KL, 94)[1];
        k15 = cycleShiftForPair(KA, 94)[0];
        k16 = cycleShiftForPair(KA, 94)[1];
        k17 = cycleShiftForPair(KL, 111)[0];
        k18 = cycleShiftForPair(KL, 111)[1];
        kw3 = cycleShiftForPair(KA, 111)[0];
        kw4 = cycleShiftForPair(KA, 111)[1];
    }

    public void generateKeys(String keyInit)
    {
        long[] KL_KR = getKL_KR(keyInit);
        long[] KA_KB = getKA_KB(KL_KR);
        getSubKeys128(KL_KR, KA_KB);
    }
}
