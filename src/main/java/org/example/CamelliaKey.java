package org.example;

import java.math.BigInteger;

public class CamelliaKey
{
//    BigInteger key;

//    CamelliaKey (BigInteger k)
//    {
//        this.key = k;
//    }

    public long kw1, kw2, kw3, kw4;
    public long k1, k2, k3, k4, k5, k6, k7, k8, k9,
            k10, k11, k12, k13, k14, k15, k16, k17, k18, k19, k20, k21, k22, k23, k24;
    public long ke1, ke2, ke3, ke4, ke5, ke6;

    private static BigInteger[] getKL_KR(BigInteger key)
    {
        BigInteger[] twoPartOfKey = new BigInteger[2];
        if (key.bitLength() == 128)
        {
            twoPartOfKey[0] = key;
            twoPartOfKey[1] = new BigInteger("0");
        }
        else if (key.bitLength() == 192)
        {
            twoPartOfKey[0] = key.shiftRight(64);
            BigInteger temp = key.and(BigInteger.valueOf(Camellia.MASK64));
            twoPartOfKey[1] = ((temp).shiftLeft(64)).or((temp.not()));
        }
        else if (key.bitLength() == 256)
        {
            twoPartOfKey[0] = key.shiftRight(128);
            twoPartOfKey[1] = key.and(BigInteger.valueOf(Camellia.MASK128));
        }
        else
        {
            System.out.println("Incorrect length of key");
        }
        return twoPartOfKey;
    }

    private static BigInteger[] getKA_KB(BigInteger[] KL_KR)
    {
        BigInteger[] KA_KB = new BigInteger[2];
//        long D1, D2;

        BigInteger D1, D2;

        D1 = (KL_KR[0].xor(KL_KR[1])).shiftRight(64);
        D2 = (KL_KR[0].xor(KL_KR[1])).and(BigInteger.valueOf(Camellia.MASK64));
        D2 = D2.xor(BigInteger.valueOf(CamelliaFunction.F(D1, BigInteger.valueOf(Camellia.c[0]))));
        D1 = D1.xor()
//        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[1]);
//        D1 = D1 ^ (KL_KR[0].shiftRight(64).longValue());
//        D2 = D2 ^ (KL_KR[0].and(BigInteger.valueOf(Camellia.MASK64)).longValue());
//        D2 = D2 ^ CamelliaFunction.F(D1, Camellia.c[2]);
//        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[3]);
//        KA_KB[0] = BigInteger.valueOf((D1 << 64) | D2);
//        D1 = (KA_KB[0].or(KL_KR[1])).shiftRight(64).longValue();
//        D2 = (KA_KB[0].xor(KL_KR[1])).and(BigInteger.valueOf(Camellia.MASK64)).longValue();
//        D2 = D2 ^ CamelliaFunction.F(D1, Camellia.c[4]);
//        D1 = D1 ^ CamelliaFunction.F(D2, Camellia.c[5]);
//        KA_KB[1] = BigInteger.valueOf((D1 << 64) | D2);
        return KA_KB;
    }

    private void getSubKeys128(BigInteger[] KL_KR, BigInteger[] KA_KB)
    {
        BigInteger MASK64 = BigInteger.valueOf(Camellia.MASK64);
        kw1 = (KL_KR[0].shiftLeft(0)).shiftRight(64).longValue();
        kw2 = (KL_KR[0].shiftLeft(0)).and(MASK64).longValue();
        k1  = (KA_KB[0].shiftLeft(0)).shiftRight(64).longValue();
        k2  = (KA_KB[0].shiftLeft(0)).and(MASK64).longValue();
        k3  = (KL_KR[0].shiftLeft(15)).shiftRight(64).longValue();
        k4  = (KL_KR[0].shiftLeft(15)).and(MASK64).longValue();
        k5  = (KA_KB[0].shiftLeft(15)).shiftRight(64).longValue();
        k6  = (KA_KB[0].shiftLeft(15)).and(MASK64).longValue();
        ke1 = (KA_KB[0].shiftLeft(30)).shiftRight(64).longValue();
        ke2 = (KA_KB[0].shiftLeft(30)).and(MASK64).longValue();
        k7  = (KL_KR[0].shiftLeft(45)).shiftRight(64).longValue();
        k8  = (KL_KR[0].shiftLeft(45)).and(MASK64).longValue();
        k9  = (KA_KB[0].shiftLeft(45)).shiftRight(64).longValue();
        k10 = (KL_KR[0].shiftLeft(60)).and(MASK64).longValue();
        k11 = (KA_KB[0].shiftLeft(60)).shiftRight(64).longValue();
        k12 = (KA_KB[0].shiftLeft(60)).and(MASK64).longValue();
        ke3 = (KL_KR[0].shiftLeft(77)).shiftRight(64).longValue();
        ke4 = (KL_KR[0].shiftLeft(77)).and(MASK64).longValue();
        k13 = (KL_KR[0].shiftLeft(94)).shiftRight(64).longValue();
        k14 = (KL_KR[0].shiftLeft(94)).and(MASK64).longValue();
        k15 = (KA_KB[0].shiftLeft(94)).shiftRight(64).longValue();
        k16 = (KA_KB[0].shiftLeft(94)).and(MASK64).longValue();
        k17 = (KL_KR[0].shiftLeft(111)).shiftRight(64).longValue();
        k18 = (KL_KR[0].shiftLeft(111)).and(MASK64).longValue();
        kw3 = (KA_KB[0].shiftLeft(111)).shiftRight(64).longValue();
        kw4 = (KA_KB[0].shiftLeft(111)).and(MASK64).longValue();
    }

    public void generateKeys(BigInteger keyInit)
    {
        BigInteger[] KL_KR = getKL_KR(keyInit);
        BigInteger[] KA_KB = getKA_KB(KL_KR);
        getSubKeys128(KL_KR, KA_KB);
    }
//    public BigInteger[] getSubKeys192or256(BigInteger[] KL_KR, BigInteger[] KA_KB)
//    {
//        BigInteger MASK64 = BigInteger.valueOf(Camellia.MASK64);
//        kw1 = (KL_KR[0].shiftLeft(0)).shiftRight(64);
//        kw2 = (KL <<<   0) & MASK64;
//        k1  = (KB <<<   0) >> 64;
//        k2  = (KB <<<   0) & MASK64;
//        k3  = (KR <<<  15) >> 64;
//        k4  = (KR <<<  15) & MASK64;
//        k5  = (KA <<<  15) >> 64;
//        k6  = (KA <<<  15) & MASK64;
//        ke1 = (KR <<<  30) >> 64;
//        ke2 = (KR <<<  30) & MASK64;
//        k7  = (KB <<<  30) >> 64;
//        k8  = (KB <<<  30) & MASK64;
//        k9  = (KL <<<  45) >> 64;
//        k10 = (KL <<<  45) & MASK64;
//        k11 = (KA <<<  45) >> 64;
//        k12 = (KA <<<  45) & MASK64;
//        ke3 = (KL <<<  60) >> 64;
//        ke4 = (KL <<<  60) & MASK64;
//        k13 = (KR <<<  60) >> 64;
//        k14 = (KR <<<  60) & MASK64;
//        k15 = (KB <<<  60) >> 64;
//        k16 = (KB <<<  60) & MASK64;
//        k17 = (KL <<<  77) >> 64;
//        k18 = (KL <<<  77) & MASK64;
//        ke5 = (KA <<<  77) >> 64;
//        ke6 = (KA <<<  77) & MASK64;
//        k19 = (KR <<<  94) >> 64;
//        k20 = (KR <<<  94) & MASK64;
//        k21 = (KA <<<  94) >> 64;
//        k22 = (KA <<<  94) & MASK64;
//        k23 = (KL <<< 111) >> 64;
//        k24 = (KL <<< 111) & MASK64;
//        kw3 = (KB <<< 111) >> 64;
//        kw4 = (KB <<< 111) & MASK64;
//    }


}
