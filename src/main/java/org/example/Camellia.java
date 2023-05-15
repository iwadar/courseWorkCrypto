package org.example;

import java.math.BigInteger;

public class Camellia
{
    public static final long MASK8 = 0xffL;
    public static final long MASK32 = 0xffffL;
    public static final long MASK64 = 0xffffffL;
    public static final long MASK128 = 0xffffffffL;

    public CamelliaKey key;

    public static final long[] c = {0xA09E667F3BCC908BL, 0xB67AE8584CAA73B2L, 0xC6EF372FE94F82BEL,
            0x54FF53A5F1D36F1CL, 0x10E527FADE682D1DL, 0xB05688C2B3E6C1FDL};


    Camellia (CamelliaKey k)
    {
        this.key = k;
    }

    public BigInteger encrypt(byte[] textByte)
    {
        BigInteger M = new BigInteger(textByte);
        long D1, D2;

        D1 = M.shiftRight(64).longValue();
        D2 = M.and(BigInteger.valueOf(MASK64)).longValue();
        D1 = D1 ^ key.kw1;           // Предварительное забеливание
        D2 = D2 ^ key.kw2;
        D2 = D2 ^ CamelliaFunction.F(D1, key.k1);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k2)    ;
        D2 = D2 ^ CamelliaFunction.F(D1, key.k3);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k4);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k5);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k6);
        D1 = CamelliaFunction.FL(D1, key.ke1);         // FL
        D2 = CamelliaFunction.FL_INV(D2, key.ke2);     // FL_INV
        D2 = D2 ^ CamelliaFunction.F(D1, key.k7);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k8);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k9);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k10);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k11);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k12);
        D1 = CamelliaFunction.FL(D1, key.ke3);         // FL
        D2 = CamelliaFunction.FL_INV(D2, key.ke4);     // FL_INV
        D2 = D2 ^ CamelliaFunction.F(D1, key.k13);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k14);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k15);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k16);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k17);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k18);
        D2 = D2 ^ key.kw3;           // Финальное забеливание
        D1 = D1 ^ key.kw4;
        BigInteger D2Big = BigInteger.valueOf(D2);
        D2Big = D2Big.shiftLeft(64);
        BigInteger C = D2Big.or(BigInteger.valueOf(D1));
        //BigInteger.valueOf((D2 << 64) | D1);
        return  C;
    }

    public BigInteger decrypt(BigInteger C)
    {
//        BigInteger M = new BigInteger(textByte);
        long D1, D2;

        D1 = C.shiftRight(64).longValue();
        D2 = C.and(BigInteger.valueOf(MASK64)).longValue();
        D1 = D1 ^ key.kw3;           // Предварительное забеливание
        D2 = D2 ^ key.kw4;
        D2 = D2 ^ CamelliaFunction.F(D1, key.k18);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k17);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k16);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k15);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k14);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k13);
        D1 = CamelliaFunction.FL(D1, key.ke4);         // FL
        D2 = CamelliaFunction.FL_INV(D2, key.ke3);     // FL_INV
        D2 = D2 ^ CamelliaFunction.F(D1, key.k12);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k11);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k10);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k9);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k8);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k7);
        D1 = CamelliaFunction.FL(D1, key.ke2);         // FL
        D2 = CamelliaFunction.FL_INV(D2, key.ke1);     // FL_INV
        D2 = D2 ^ CamelliaFunction.F(D1, key.k6);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k5);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k4);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k3);
        D2 = D2 ^ CamelliaFunction.F(D1, key.k2);
        D1 = D1 ^ CamelliaFunction.F(D2, key.k1);
        D2 = D2 ^ key.kw1;           // Финальное забеливание
        D1 = D1 ^ key.kw2;
        BigInteger D2Big = BigInteger.valueOf(D2);
        D2Big = D2Big.shiftLeft(64);


        BigInteger M = D2Big.or(BigInteger.valueOf(D1));
//        BigInteger C = BigInteger.valueOf((D2 << 64) | D1);
//        return  BigInteger.valueOf((D2 << 64) | D1);
        return M;
    }


}
